package com.nsoft.gkzp.system.sysuser.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nsoft.gkzp.plan.service.HrRecruitNoticeService;
import com.nsoft.gkzp.syscore.web.ControllerException;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.system.sysuser.entity.SysUser;
import com.nsoft.gkzp.system.sysuser.service.SysUserService;
import com.nsoft.gkzp.util.CheckDataType;
import com.nsoft.gkzp.util.CommonCrypto;
import com.nsoft.gkzp.util.ResultMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class SysUserController {

    final protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CommonCrypto commonCrypto;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HrRecruitNoticeService hrRecruitNoticeService;

    @Autowired
    private ResultMsg resultMsg;



   // private String MESSAGE      = "msg";//异常信息
   // private String ERRORPAGE    = "redirect:/jsp/login.jsp"; //登录失败返回页面
   // private String SUCCESSRPAGE = "redirect:/jsp/index.jsp"; //登录成功页面（redirect:重定向，防止页面刷新重新提交请求）
   // private String REGISTER_ERRORPAGE    = "/jsp/register.jsp"; //注册失败返回页面
   // private String REGISTER_SUCCESSRPAGE = "redirect:/jsp/login.jsp"; //注册成功，返回登录页面（redirect:重定向，防止页面刷新重新提交请求）

    /**
     * 登录
     * @param loginName
     * @param password
     * @param arg0
     * @param arg1
     * @param model
     * @return
     * @throws ControllerException
     */

    //@RequestMapping("/user/login")
    //@GetMapping（"/user/login"）
    @PostMapping("/user/login") // 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
    public ResultMsg login(String loginName, String password,String checkCode, HttpServletRequest arg0, HttpServletResponse arg1, Model model) throws ControllerException {
        try {

            UserContext userContext = new UserContext(); //session
            String SHApassword = "";//加密后密码

            logger.info("开始用户登录校验");

            //验证码校验
            if(StringUtils.isEmpty(checkCode)  ){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"登录失败，验证码不能为空,请检查!");
                return resultMsg;
            }else{
                String checkCodeByRedis = stringRedisTemplate.opsForValue().get("loginUser:checkCode-"+arg0.getSession().getId());
                logger.info("session中的验证码="+checkCodeByRedis);
                if(StringUtils.isEmpty(checkCodeByRedis)){
                    resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码已失效，请重新填写!");
                    return resultMsg;
                }else if(!checkCode.toLowerCase().equals(checkCodeByRedis.toLowerCase())){
                    resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码不正确，请重新填写!");
                    return resultMsg;
                }
            }

            if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
                //model.addAttribute(MESSAGE, "登录失败，用户名或密码不能为空");
                //return ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"登录失败，用户名或密码不能为空,请检查!");
                return resultMsg;
            }
            SHApassword =  commonCrypto.encryptSHAEncoder(password);//密码加密
            SysUser sysUser = sysUserService.login(loginName, SHApassword);//账户+密码 校验
            if (sysUser != null && sysUser.getId()>0) {
                    userContext.setLoginName(sysUser.getLoginName());
                    userContext.setLoginUserId(sysUser.getId());
                    userContext.setLoginDate(new Date(System.currentTimeMillis()));
                    WebUtils.setSessionAttribute(arg0, "userContext", userContext);//生成session信息userContext
                    stringRedisTemplate.opsForValue().set("loginUser:" +sysUser.getId(), arg0.getSession().getId(),1,TimeUnit.HOURS);//向redis里存储 用户ID-sessionID对,失效时间为1h，用于拦截器判断是否重复登录
//                    //model.addAttribute("sysUser", sysUser);
//                    logger.info(sysUser);
//                    return SUCCESSRPAGE;
                logger.info( "aaaaaaa="+WebUtils.getSessionAttribute(arg0,"userContext"));
                resultMsg.setResultMsg(ResultMsg.MsgType.NONE,"登录成功");
                return resultMsg;
            } else {
//                model.addAttribute("loginName", loginName);
//                model.addAttribute(MESSAGE, "登录失败，用户名或密码错误");
//                return ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"登录失败，用户名或密码错误,请检查!");
                return resultMsg;
            }

        }catch (Exception e){
            e.printStackTrace();
            //model.addAttribute(MESSAGE, "登录产生异常!");
            //return ERRORPAGE;
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"登录产生异常! 请重新登录");
            return resultMsg;
        }

    }

    /**
     * 注册
     * @param loginName 用户名
     * @param password  密码
     * @param rePassword 确认密码
     * @param checkCode 验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/register")
    public ResultMsg register(String loginName,String password,String rePassword,String checkCode, HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = -1;

        try {
            logger.info("开始用户注册信息校验");

            //验证码校验
            if(StringUtils.isEmpty(checkCode)  ){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，验证码不能为空,请检查!");
                return resultMsg;
            }else{
                String checkCodeByRedis = stringRedisTemplate.opsForValue().get("loginUser:checkCode-"+request.getSession().getId());
                logger.info("session中的验证码="+checkCodeByRedis);
                if(StringUtils.isEmpty(checkCodeByRedis)){
                    resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码已失效，请重新填写!");
                    return resultMsg;
                }else if(!checkCode.toLowerCase().equals(checkCodeByRedis.toLowerCase())){
                    resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"验证码不正确，请重新填写!");
                    return resultMsg;
                }
            }

            if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
                //model.addAttribute(MESSAGE, "注册失败，用户名或密码不能为空,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，用户名或密码不能为空,请检查!");
                return resultMsg;
            }

            if(!password.equals(rePassword)){
                //model.addAttribute(MESSAGE, "注册失败，密码和确认密码不一样,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，密码和确认密码不一致,请检查!");
                return resultMsg;
            }
            if (!CheckDataType.checkName(loginName)) {
                //model.addAttribute(MESSAGE, "注册失败，用户名必须是4-10位字母或数字或下划线组成，且不能以数字开头,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，用户名必须是4-10位字母或数字或下划线组成，且不能以数字开头,请检查!");
                return resultMsg;
            }

            if (password.length() < 8 || password.length() > 16) {
                //model.addAttribute(MESSAGE, "注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查!");
                return resultMsg;
            }
            if (CheckDataType.isChinese(password)) {
                //model.addAttribute(MESSAGE, "注册失败，密码不能包含汉子,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，密码不能包含汉子,请检查!");
                return resultMsg;
            }

            int i = CheckDataType.matcheData(password, 1) ? 1 : 0;
            int j = CheckDataType.matcheData(password, 2) ? 1 : 0;
            int k = CheckDataType.matcheData(password, 3) ? 1 : 0;
            if (i + j + k < 2) {
                //model.addAttribute(MESSAGE, "注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查!");
                return resultMsg;
            }

            if (password.contains(loginName)) {
                //model.addAttribute(MESSAGE, "注册失败，密码不能包含账户信息,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，密码不能包含账户信息,请检查!");
                return resultMsg;
            }

            id = sysUserService.findIdByColumn("loginname", loginName);
            if (id > 0) {
                //model.addAttribute(MESSAGE, "注册失败，此账户信息已被注册过,请检查");
                //return REGISTER_ERRORPAGE;
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册失败，此账户信息已被注册过,请检查!");
                return resultMsg;
            }
            logger.info("注册信息校验成功");

            String  SHApassword =  commonCrypto.encryptSHAEncoder(password);//密码加密
            sysUserService.saveRegister(loginName,SHApassword);

        }catch (Exception e){
            e.printStackTrace();
           //model.addAttribute(MESSAGE, "注册产生异常!");
            //return REGISTER_ERRORPAGE;
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注册产生异常! 请联系管理员");
            return resultMsg;
        }

        //model.addAttribute(MESSAGE, "注册成功，请登录!");
        //return REGISTER_SUCCESSRPAGE;
        resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"注册成功，请登录!");
        return resultMsg;

    }

    /**
     * 修改密码功能
     * @param oldPassword 旧密码
     * @param password    新密码
     * @param rePassword  确认密码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/intercept/user/changePWD")
    public ResultMsg changePWD(String oldPassword,String password,String rePassword, HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = -1;

        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request, "userContext");//生成session信息userContext

            logger.info("开始用户修改密码信息校验");

            if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(password) || StringUtils.isEmpty(rePassword) ) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，旧密码、新密码不能为空,请检查!");
                return resultMsg;
            }

            if(!password.equals(rePassword)){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，新密码和确认密码不一致,请检查!");
                return resultMsg;
            }

            if(password.equals(oldPassword)){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，旧密码和新密码相同,请检查!");
                return resultMsg;
            }

            if (password.length() < 8 || password.length() > 16) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查!");
                return resultMsg;
            }
            if (CheckDataType.isChinese(password)) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，密码不能包含汉子,请检查!");
                return resultMsg;
            }

            int i = CheckDataType.matcheData(password, 1) ? 1 : 0;
            int j = CheckDataType.matcheData(password, 2) ? 1 : 0;
            int k = CheckDataType.matcheData(password, 3) ? 1 : 0;
            if (i + j + k < 2) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，密码必须是8-16位且至少包含字母、数字、特殊字符中的两种,请检查!");
                return resultMsg;
            }

            if (password.contains(userContext.getLoginName())) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，新密码不能包含账户信息,请检查!");
                return resultMsg;
            }

            SysUser sysUser = sysUserService.login(userContext.getLoginName(), commonCrypto.encryptSHAEncoder(oldPassword));
            if(sysUser == null || sysUser.getId() != userContext.getLoginUserId()){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码失败，旧密码填写错误,请检查!");
                return resultMsg;
            }

            logger.info("修改密码信息校验成功");

            String  SHApassword =  commonCrypto.encryptSHAEncoder(password);//密码加密
            sysUserService.changePWD(userContext.getLoginUserId(),SHApassword);

        }catch (Exception e){
            e.printStackTrace();
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"修改密码产生异常! 请联系管理员");
            return resultMsg;
        }
        resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"修改密码成功");
        return resultMsg;

    }

    /**
     * 从session中获得登录用户名
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/user/getLoginName")
    public ResultMsg getLoginName( HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            UserContext user =  (UserContext)WebUtils.getSessionAttribute(request,"userContext");
            if(!StringUtils.isEmpty(user)){
                resultMsg.setResultMsg(ResultMsg.MsgType.INFO,(user).getLoginName());

                //获取未读消息数
                int noticeInt = hrRecruitNoticeService.noticeInt(user);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("noticeInt",noticeInt);
                resultMsg.setData(hashMap);
            }else{
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"用户未登陆!");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"出现异常！");
        }

        return resultMsg;
    }

    /**
     * 注销操作
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/user/logout")
    public void logout( HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            Object user = WebUtils.getSessionAttribute(request, "userContext");
            WebUtils.setSessionAttribute(request, "userContext", null);
            // resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"注销成功");
        }catch (Exception e){
            e.printStackTrace();
            //  resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"注销失败");
        }
        //  return resultMsg;
    }


    /**
     * 获取用户信息
     * @param pageNum  当前页数
     * @param pageSize 当前页最多显示多少行
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/getUserInfos")
    public String getUserInfos( Integer pageNum, Integer pageSize) throws Exception{

        JSONObject result = new JSONObject();
        try{
            PageHelper.startPage(pageNum,pageSize);
            Page<HashMap> data = sysUserService.getUserInfos( );

            result.put("data",data);//
            //获取页面总数
            result.put("limit",data.getPages());
            //获取数据总数
            result.put("total",data.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            //   throw new ControllerException("分页查询失败",e,userContext);
        }

        return result !=null?result.toString():null;
    }

    /**
     *密码初始化
     * @param id 用户登录ID
     * @param loginName 用户登录账户
     * @throws Exception
     */
    @RequestMapping("/user/initializePWD")
    public ResultMsg initializePWD( int id,String loginName) throws Exception{
        boolean oee = false;
        try{
            if(loginName !=null){
                int temp =  sysUserService.findIdByColumn("loginName",loginName);
                if(id  == temp){
                    String  SHApassword =  commonCrypto.encryptSHAEncoder("zz123456");//密码加密
                    sysUserService.changePWD(id,SHApassword);
                   oee = true;
                }
            }

            if(oee){
                resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"密码重置完成");
            }else{
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"用户信息校验不通过，请检查！");
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("密码初始化错误id="+id+"loginName="+loginName+";\n "+e.getMessage());
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"系统出现错误，请联系管理员");
        }
        return resultMsg;
    }



    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/user/imgCode")
    public void imgCode( HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = -1;

        try {

            response.setContentType("image/jpeg");

            int width=60;
            int height=20;

            //设置浏览器不要缓存此图片
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            //创建内存图像并获得图形上下文
//            BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
//            Graphics g=image.getGraphics();

            /*
//             * 产生随机验证码
//             * 定义验证码的字符表
//             */
//            String chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//            char[] rands=new char[4];
//            for(int i=0;i<4;i++){
//                int rand=(int) (Math.random() *36);
//                rands[i]=chars.charAt(rand);
//            }
//
//            /*
//             * 产生图像
//             * 画背景
//             */
//            g.setColor(new Color(0xDCDCDC));
//            g.fillRect(0, 0, width, height);
//
//            /*
//             * 随机产生120个干扰点
//             */
//
//            for(int i=0;i<480;i++){
//                int x=(int)(Math.random()*width);
//                int y=(int)(Math.random()*height);
//                int red=(int)(Math.random()*255);
//                int green=(int)(Math.random()*255);
//                int blue=(int)(Math.random()*255);
//                g.setColor(new Color(red,green,blue));
//                g.drawOval(x, y, 1, 0);
//            }
//            g.setColor(Color.BLACK);
//            g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));
//
//            //在不同高度输出验证码的不同字符
//            g.drawString(""+rands[0], 1, 17);
//            g.drawString(""+rands[1], 16, 15);
//            g.drawString(""+rands[2], 31, 18);
//            g.drawString(""+rands[3], 46, 16);
//            g.dispose();
//            HttpSession session=request.getSession();
//            logger.info("id1="+request.getSession().getId());
//            logger.info("id2="+session.getId());
//            stringRedisTemplate.opsForValue().set("loginUser:" +"checkcode"+request.getSession().getId(),new String(rands) ,1,TimeUnit.MINUTES);



            String code=(System.currentTimeMillis()+"").replaceAll(".*(.{2})$", "$1");
            char c1=(char)(65+Math.random()*100%26);
            char c2=(char)(65+Math.random()*100%26);
            code+=c1+""+c2;
            //将生成的验证码存储到redis数据库中
            stringRedisTemplate.opsForValue().set("loginUser:checkCode-"+request.getSession().getId(),code ,1,TimeUnit.MINUTES);

            int verifySize = code.length();
            int w=80,h=30;
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Random rand = new Random();
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            Color[] colors = new Color[5];
            Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                    Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                    Color.PINK, Color.YELLOW };
            float[] fractions = new float[colors.length];
            for(int i = 0; i < colors.length; i++){
                colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
                fractions[i] = rand.nextFloat();
            }
            Arrays.sort(fractions);

            g2.setColor(Color.GRAY);// 设置边框色
            g2.fillRect(0, 0, w, h);

            Color c = sysUserService.getRandColor(200, 250);
            g2.setColor(c);// 设置背景色
            g2.fillRect(0, 2, w, h-4);

    //绘制干扰线
            Random random = new Random();
            g2.setColor(sysUserService.getRandColor(160, 200));// 设置线条的颜色
            for (int i = 0; i < 20; i++) {
                int x = random.nextInt(w - 1);
                int y = random.nextInt(h - 1);
                int xl = random.nextInt(6) + 1;
                int yl = random.nextInt(12) + 1;
                g2.drawLine(x, y, x + xl + 40, y + yl + 20);
            }
    // 添加噪点
//            float yawpRate = 0.05f;// 噪声率
//            int area = (int) (yawpRate * w * h);
//            for (int i = 0; i < area; i++) {
//                int x = random.nextInt(w);
//                int y = random.nextInt(h);
//                int rgb = sysUserService.getRandomIntColor();
//                image.setRGB(x, y, rgb);
//            }
            sysUserService.shear(g2, w, h, c);// 使图片扭曲

            g2.setColor(sysUserService.getRandColor(100, 160));
            int fontSize = h-4;
            Font font = new Font("Algerian", Font.ITALIC, fontSize);
            g2.setFont(font);
            char[] chars = code.toCharArray();
            for(int i = 0; i < verifySize; i++){
                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);
                g2.setTransform(affine);
                g2.drawChars(chars, i, 1, ((w-10) / verifySize) * i + 5, h/2 + fontSize/2 - 5);
            }
            g2.dispose();
            ImageIO.write(image, "JPEG", response.getOutputStream());
            response.flushBuffer();

        //将图像传到客户端
            ServletOutputStream sos=response.getOutputStream();
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", baos);
            byte[] buffer=baos.toByteArray();
            response.setContentLength(buffer.length);
            sos.write(buffer);
            baos.close();
            sos.close();
        }catch (Exception e) {
            e.printStackTrace();

        }
    }


}

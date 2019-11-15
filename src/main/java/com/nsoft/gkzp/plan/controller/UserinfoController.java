package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.common.FileLoad;
import com.nsoft.gkzp.common.entity.FileVo;
import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.DataFormat;
import com.nsoft.gkzp.util.PageVo;
import com.nsoft.gkzp.util.ResultMsg;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserinfoController extends AbstractController {
    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    //计划
    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    //体检
    @Autowired
    HrRecruitHealthchkService hrRecruitHealthchkService;

    //面试
    @Autowired
    HrRecuritInterviewService hrRecuritInterviewService;

    //笔试
    @Autowired
    HrRecuritWriteService hrRecuritWriteService;

    //资格审核
    @Autowired
    HrRecruitReviewRecordService hrRecruitReviewRecordService;

    //消息
    @Autowired
    HrRecruitNoticeService hrRecruitNoticeService;

    //岗位
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    //数据转换
    @Autowired
    DataFormat dataFormat;

    //文件管理
    @Autowired
    FileLoad fileLoad;

    //配置参数
    @Autowired
    private MyDefinedUtil myDefinedUtil;

    /**
     * 根据登录用户查询信息
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getInfoByUser")
    public HrRecruitEntryinfo getInfoByUser(HttpServletRequest request){
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            return hrRecruitEntryinfoBaseService.getInfoByUser(userContext);
        } catch (Exception e) {
            logger.error("获取用户信息错误："+e.getMessage(),e);
        }
        return null;
    }


    /**
     * 基础信息新增
     * @param data
     * @return
     */
    @RequestMapping(value="intercept/plan/userInfo/add",method= RequestMethod.POST)
    public ResultMsg add(String data, HttpServletRequest request){
        ResultMsg resultMsg = new ResultMsg();
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            logger.info(userContext);
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);

            hrRecruitEntryinfoBaseService.add(jsonObject,userContext);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"");
            return resultMsg;
        } catch (UnsupportedEncodingException e) {
            logger.error("基础信息新增报错"+e.getMessage(),e);
        }
        //错误信息
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"");
        return resultMsg;
    }

    /**
     * 修改
     * @param data
     * @param request
     * @return
     */
    @RequestMapping(value="intercept/plan/userInfo/edit",method= RequestMethod.POST)
    public ResultMsg edit(String data, HttpServletRequest request){
        ResultMsg resultMsg = new ResultMsg();
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);

            resultMsg = hrRecruitEntryinfoBaseService.edit(jsonObject, userContext);

            return resultMsg;
        } catch (Exception e) {
            logger.error("信息修改报错"+e.getMessage(),e);

        }
        //错误信息
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"");
        return resultMsg;
    }

    /**
     * 考核管理
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/review")
    public HashMap<String, Object> review(HttpServletRequest request) {
        try {
            HashMap<String, Object> result = new HashMap<>();
            //设置默认值为null
            result.put("HrRecuritWrite",null);
            result.put("HrRecuritWriteMsg",null);
            result.put("HrRecuritInterview",null);
            result.put("HrRecuritInterviewMsg",null);
            result.put("HrRecruitHealthchk",null);
            result.put("HrRecruitHealthchkMsg",null);
            result.put("HrRecruitReviewRecord",null);
            result.put("HrRecruitReviewRecordMsg",null);

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);

            if(baseId == 0)return null;

            //岗位信息
            HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoByUser(userContext);

            if(hrRecuritPlanNeedsVoByUser == null) return null;
            result.put("HrRecuritPlanNeeds",hrRecuritPlanNeedsVoByUser.getHrRecuritPlanNeedsDo());

            //获取通知消息
            HrRecruitNotice hrRecruitNoticeSelect = new HrRecruitNotice();
            hrRecruitNoticeSelect.setBaseid(baseId);
            hrRecruitNoticeSelect.setStatus(1);
            List<HrRecruitNotice> hrRecruitNotices = hrRecruitNoticeService.list(hrRecruitNoticeSelect, null, null);

            //遍历消息读取类型
            for (HrRecruitNotice hrRecruitNotice:hrRecruitNotices) {
                int type = hrRecruitNotice.getType();
                if(type == 1){ //笔试
                    HrRecuritWrite hrRecuritWrite = hrRecuritWriteService.getHrRecuritWriteByBaseId(baseId);
                    result.put("HrRecuritWrite",hrRecuritWrite);
                    result.put("HrRecuritWriteMsg",hrRecruitNotice);
                } else if(type == 2){ //面试
                    HrRecuritInterview hrRecuritInterview = hrRecuritInterviewService.getHrRecuritInterviewByBaseId(baseId);
                    result.put("HrRecuritInterview",hrRecuritInterview);
                    result.put("HrRecuritInterviewMsg",hrRecruitNotice);
                } else if(type == 3){ //体检
                    HrRecruitHealthchk hrRecruitHealthchk = hrRecruitHealthchkService.getHrRecruitHealthchkByBaseId(baseId);
                    result.put("HrRecruitHealthchk",hrRecruitHealthchk);
                    result.put("HrRecruitHealthchkMsg",hrRecruitNotice);
                } else if(type == 4){ //审核结果
                    HrRecruitReviewRecord hrRecruitReviewRecord = hrRecruitReviewRecordService.getHrRecruitReviewRecordByBaseId(baseId);
                    result.put("HrRecruitReviewRecord",hrRecruitReviewRecord);

                    String affix = hrRecruitNotice.getAffix();
                    //文件不为空
                    if(affix != null && affix != ""){
                        //查询文件
                        Integer[] affixArr = dataFormat.stringArrToIntArr(affix.split(","));
                        List<FileVo> fileList = fileLoad.getFileListByIds(affixArr);
                        hrRecruitNotice.setAffixList(fileList);
                    }
                    result.put("HrRecruitReviewRecordMsg",hrRecruitNotice);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("考核管理错误："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 使用进行体检
     * @param request
     * @param isjoin
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/healthchkIsJoin")
    public ResultMsg healthchkIsJoin(HttpServletRequest request,int isjoin){
        ResultMsg resultMsg = new ResultMsg();
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            HrRecruitHealthchk hrRecruitHealthchkByUser = hrRecruitHealthchkService.getHrRecruitHealthchkByUser(userContext);

            if(hrRecruitHealthchkByUser.getResult() != null){
                //判断是否出结果
                Integer result = hrRecruitHealthchkByUser.getResult();
                if(result == 1 || result == 2){
                    resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"体检结果已出，不能进行修改！");
                    return resultMsg;
                }
            }

            hrRecruitHealthchkByUser.setIsjoin(isjoin);
            hrRecruitHealthchkByUser.setSyncisjoin(1);
            hrRecruitHealthchkService.edit(hrRecruitHealthchkByUser);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"体检提交成功");
            return resultMsg;
        } catch (Exception e) {
            logger.error("体检错误:"+e.getMessage(),e);
        }

        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"体检提交失败");
        return resultMsg;
    }

    /**
     * 通过用户获取体检数据
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecruitHealthchkByUser")
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(HttpServletRequest request) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            return hrRecruitHealthchkService.getHrRecruitHealthchkByUser(userContext);
        } catch (Exception e) {
            logger.error("获取体检数据出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 通过用户获取面试数据  打印准考证
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecuritInterviewPrint")
    public HashMap<String, Object> getHrRecuritInterviewByUser(HttpServletRequest request) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            HashMap<String, Object> map = new HashMap<>();
            //基础信息
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = hrRecruitEntryinfoBaseService.getBaseByUser(userContext);
            //岗位
            HrRecuritPlanNeedsDo hrRecuritPlanNeedsDo = hrRecuritPlanNeedsService.findById(hrRecruitEntryinfoBase.getPostid());
            //计划
            HrRecuritPlan hrRecuritPlan = hrRecuritPlanService.getHrRecuritPlanById(hrRecruitEntryinfoBase.getPlanid());
            //面试信息
            HrRecuritInterview hrRecuritInterview = hrRecuritInterviewService.getHrRecuritInterviewByUser(userContext);

            map.put("hrRecruitEntryinfoBase",hrRecruitEntryinfoBase);
            map.put("hrRecuritPlanNeedsDo",hrRecuritPlanNeedsDo);
            map.put("hrRecuritInterview",hrRecuritInterview);
            map.put("hrRecuritPlan",hrRecuritPlan);
            return map;
        } catch (Exception e) {
            logger.error("面试打印准考证报错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 通过用户获取笔试数据  打印准考证
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecuritWritePrint")
    public HashMap<String, Object> getHrRecuritWriteByUser(HttpServletRequest request) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            HashMap<String, Object> map = new HashMap<>();
            //基础信息
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = hrRecruitEntryinfoBaseService.getBaseByUser(userContext);

            //岗位
            HrRecuritPlanNeedsDo hrRecuritPlanNeedsDo = hrRecuritPlanNeedsService.findById(hrRecruitEntryinfoBase.getPostid());

            //计划
            HrRecuritPlan hrRecuritPlan = hrRecuritPlanService.getHrRecuritPlanById(hrRecruitEntryinfoBase.getPlanid());

            //笔试信息
            HrRecuritWrite hrRecuritWrite = hrRecuritWriteService.getHrRecuritWriteByUser(userContext);

            map.put("hrRecruitEntryinfoBase",hrRecruitEntryinfoBase);
            map.put("hrRecuritPlanNeedsDo",hrRecuritPlanNeedsDo);
            map.put("hrRecuritWrite",hrRecuritWrite);
            map.put("hrRecuritPlan",hrRecuritPlan);
            return map;
        } catch (Exception e) {
            logger.error("笔试打印准考证报错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 通过用户获取资格审核
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecruitReviewRecordVoByUser")
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByUser(HttpServletRequest request) {
        try {
            HrRecruitReviewRecordVo result = new HrRecruitReviewRecordVo();

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            HrRecruitReviewRecordVo hrRecruitReviewRecordVo = hrRecruitReviewRecordService.getHrRecruitReviewRecordVoByUser(userContext);

            result.setHrRecruitReviewRecord(hrRecruitReviewRecordVo.getHrRecruitReviewRecord());
            //建一个新的基础信息
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
            //只获取报名时间
            hrRecruitEntryinfoBase.setSignuptime(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase().getSignuptime());
            result.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);
            return result;
        } catch (Exception e) {
            logger.error("获取资格审核错误："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 是否审核
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/isReview")
    public boolean isReview(HttpServletRequest request){
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            int baseIdByUser = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);
            return hrRecruitReviewRecordService.isReview(baseIdByUser);
        } catch (Exception e) {
            logger.error("判断是否审核出错："+e.getMessage(),e);
        }
        return false;
    }

    /**
     * 用户消息
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/userMsg")
    public List<HrRecruitNotice> userMsg(HttpServletRequest request, int hasRead, PageVo page) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            //获取基础信息id
            int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);

            HrRecruitNotice hrRecruitNotice = new HrRecruitNotice();
            hrRecruitNotice.setBaseid(baseId);
            hrRecruitNotice.setStatus(1);
            hrRecruitNotice.setHasread(hasRead);
            List<HrRecruitNotice> list = hrRecruitNoticeService.list(hrRecruitNotice, null, page);
            return list;
        } catch (Exception e) {
            logger.error("获取用户消息出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 设为阅读
     * @param id
     */
    @RequestMapping("intercept/plan/userInfo/read")
    public void read(int id){
        try {
            hrRecruitNoticeService.read(id);
        } catch (Exception e) {
            logger.error("设为阅读出错："+e.getMessage(),e);
        }
    }

    /**
     * 用户所有设为已读
     * @param request
     */
    @RequestMapping("intercept/plan/userInfo/userReadAll")
    public void userReadAll(HttpServletRequest request) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            hrRecruitNoticeService.userReadAll(userContext);
        } catch (Exception e) {
            logger.error("用户所有设为已读出错："+e.getMessage(),e);
        }
    }


    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("plan/plan/upload/img")
    public ResultMsg uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        ResultMsg resultMsg = new ResultMsg();
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            //最大文件大小 单位kb。
            int maxSize = 5000;

            if (file.isEmpty()) {
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR, "文件不存在");
                return resultMsg;
            }

            //对文文件的全名进行截取然后在后缀名进行删选。
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            //获得文件后缀名
            String type = file.getOriginalFilename().substring(begin, last);
            //我这边需要的xlsx文件所以说我这边直接判断就是了
            if (!type.equals(".jpg") && !type.equals(".png")){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"请选择jpg或png类型");
                return resultMsg;
            }

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); // 通过MultipartFile得到InputStream，从而得到BufferedImage
            Integer width = bufferedImage.getWidth();
            Integer height = bufferedImage.getHeight();
            if(width != 100 || height != 140) return new ResultMsg(ResultMsg.MsgType.ERROR,"请选择正确的图片尺寸大小");



            //指定本地文件夹存储图片
            String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER_IMG;

            return fileLoad.uploadFile(userContext,file, filePath, maxSize);
        } catch (Exception e) {
            logger.error("上传头像出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 下载头像
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping("plan/plan/download/img/{id}")
    public void downloadImg(HttpServletResponse response, @PathVariable int id) throws Exception {
        try {
            hrRecruitEntryinfoBaseService.downloadImg(response,id);
        } catch (Exception e) {
            logger.error("下载头像出错："+e.getMessage(),e);
        }
    }

    @RequestMapping("plan/userInfo/syncFile")
    public ResultMsg syncFile(HttpServletResponse response) {
        ResultMsg resultMsg = new ResultMsg();

        //查询未同步的文件
        HrRecruitFile hrRecruitFile = new HrRecruitFile();
        hrRecruitFile.setSyncfile(1);
        List<HrRecruitFile> hrRecruitFiles = fileLoad.fileList(hrRecruitFile, null, null);

        for (HrRecruitFile hrRecruitFileEach:hrRecruitFiles) {
            String filecname = hrRecruitFileEach.getFilecname();
            String fileurl = hrRecruitFileEach.getFileurl();
            try {
                fileLoad.downloadFile(response,filecname,fileurl);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("同步文件时报错。id="+hrRecruitFileEach.getId());
            }
        }

        resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"同步成功");
        return resultMsg;
    }



}

package com.nsoft.gkzp.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.common.dao.AffixfileDao;
import com.nsoft.gkzp.common.dao.HrRecruitFileDao;
import com.nsoft.gkzp.common.entity.Affixfile;
import com.nsoft.gkzp.common.entity.FileVo;
import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;
import com.nsoft.gkzp.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Component
public class FileLoad {

    @Autowired
    HrRecruitFileDao hrRecruitFileDao;

    @Autowired
    AffixfileDao affixfileDao;


    /**
     * 上传文件
     * @param file 文件
     * @param filePath 路径
     * @param maxSize 最大文件大小  单位k；
     * @return
     */
    public ResultMsg uploadFile(UserContext userContext, MultipartFile file, String filePath,int maxSize){
        ResultMsg resultMsg = new ResultMsg();

        if(file.getSize()/1024>maxSize){
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"上传文件过大");
            return resultMsg;
        }

        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        String UUIDName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        //String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER;
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath+UUIDName));
            //保存进数据库
            HrRecruitFile hrRecruitFile = new HrRecruitFile();
            hrRecruitFile.setFilecname(fileName);
            hrRecruitFile.setFileurl(filePath+UUIDName);
            hrRecruitFile.setSubmittime(new Date());
            hrRecruitFile.setLoginuserid(userContext.getLoginUserId());
            hrRecruitFile.setSyncstatus(1);
            hrRecruitFile.setSyncfile(1);
            hrRecruitFileDao.insertSelective(hrRecruitFile);
            /*Affixfile affixfile = new Affixfile();
            affixfile.setFilecname(fileName);
            affixfile.setFileurl(filePath+UUIDName);
            affixfileDao.insertSelective(affixfile);*/
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,fileName,hrRecruitFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"上传失败");
        }

        return resultMsg;
    }

    /*public ResultData upload(MultipartFile file) {
        if (!file.isEmpty()) {

            StringBuffer requestURL = sessionUtil.getRequestURL();
            int end = requestURL.indexOf("/user/upload");
            String basePath = requestURL.substring(0, end);
            String savePath = basePath + "/static/img/logo/";
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();

            String saveDbPath = savePath + fileName;

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/logo/";

            // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
            try {
                FileUtil.fileupload(file.getBytes(), path, fileName);
                // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
                User user = sessionUtil.getSessionUser();
                user.setLogo(saveDbPath);
                User whereUser = new User();
                whereUser.setId(user.getId());
                ConfigHelper.upate(user, "user", whereUser);
                Map<String, Object> map = new HashMap<>();
                map.put("msg", "头像修改成功");
                map.put("data", user);
                return ResultData.ok(map);
            }catch (Exception e) {
                return ResultData.failed(e.getMessage());
            }

        } else {
            return ResultData.failed("上传图片失败");
        }
    }*/

    /**
     * 下载文件
     * @param response
     * @param fileName  文件名称
     * @param filePath  文件路径
     * @throws Exception
     */
    public void downloadFile(HttpServletResponse response,String fileName,String filePath) throws Exception{

        /*Affixfile affixfile = affixfileDao.selectByPrimaryKey(id);
        //设置文件名称
        String fileName = affixfile.getFilecname();
        String filePath = affixfile.getFileurl();*/

        File file = new File(filePath);
        if (file.exists()) {
            //response.setContentType("application/force-download");// 设置强制下载不打开
            //response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            //response.setContentType("multipart/form-data;charset=UTF-8");也可以明确的设置一下UTF-8，测试中不设置也可以。
            response.setHeader("Content-Disposition", "attachment;fileName="+ new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 查询外网的文件表
     * @param hrRecruitFile
     * @param order
     * @param page
     * @return
     */
    public List<HrRecruitFile> fileList(HrRecruitFile hrRecruitFile, String order, PageVo page){
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitFile.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitFile);

        List<HrRecruitFile> list = hrRecruitFileDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitFile> pageInfo = new PageInfo<HrRecruitFile>(list);
        return list;
    }

    public void fileEditExample(HrRecruitFile hrRecruitFile,Example example){
        hrRecruitFileDao.updateByExampleSelective(hrRecruitFile,example);
    }

    /**
     * 通过多个id进行查询文件   开发平台的文件表
     * @param ids 数组id
     * @return
     */
    public List<FileVo> getFileListByIds(Integer[] ids){
        List<Integer> idList = Arrays.asList(ids);
        //查询文件
        Example example = new Example(Affixfile.class);
        example.createCriteria().andIn("id",idList);
        List<Affixfile> affixfiles = affixfileDao.selectByExample(example);

        //组装文件list
        List<FileVo> fileVos = new ArrayList<>();
        for (Affixfile affixfile:affixfiles) {
            FileVo fileVo = new FileVo();
            fileVo.setId(affixfile.getId());
            fileVo.setName(affixfile.getFilecname());
            fileVos.add(fileVo);
        }

        return fileVos;
    }

    /**
     * 通过id获取  开发平台的文件表
     * @param id
     * @return
     */
    public FileVo getAffixFileById(int id){
        Affixfile affixfile = affixfileDao.selectByPrimaryKey(id);
        FileVo fileVo = new FileVo();
        fileVo.setId(affixfile.getId());
        fileVo.setName(affixfile.getFilecname());
        fileVo.setUrl(affixfile.getFileurl());
        return fileVo;
    }

    /**
     * 通过多个id进行查询文件   开发平台的文件表
     * @param ids 数组id
     * @return
     */
    public List<FileVo> getAffixFileListByIds(Integer[] ids){
        List<Integer> idList = Arrays.asList(ids);
        //查询文件
        Example example = new Example(Affixfile.class);
        example.createCriteria().andIn("id",idList);
        List<Affixfile> affixfiles = affixfileDao.selectByExample(example);

        //组装文件list
        List<FileVo> fileVos = new ArrayList<>();
        for (Affixfile affixfile:affixfiles) {
            FileVo fileVo = new FileVo();
            fileVo.setId(affixfile.getId());
            fileVo.setName(affixfile.getFilecname());
            fileVos.add(fileVo);
        }

        return fileVos;
    }


    /**
     * 文件同步
     * @param actionUrl  需要推送的路径
     * @param files  文件
     * @return
     * @throws IOException
     */
    public String upLoadFilePost(String actionUrl, Map<String, File> files) throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(5 * 1000);
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setRequestMethod("POST"); // Post方式
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
                + ";boundary=" + BOUNDARY);

        DataOutputStream outStream = new DataOutputStream(
                conn.getOutputStream());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                        + file.getKey() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());
                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }

                is.close();
                outStream.write(LINEND.getBytes());
            }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();

        // 得到响应码
        int res = conn.getResponseCode();
        if (res == 200) {
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(isReader);
            String line = "";
            String data = "";
            while ((line = bufReader.readLine()) != null) {
                data += line;
            }
            outStream.close();
            conn.disconnect();
            return data;
        }
        outStream.close();
        conn.disconnect();
        return null;
    }
}

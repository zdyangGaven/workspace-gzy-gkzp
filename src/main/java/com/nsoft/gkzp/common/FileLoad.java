package com.nsoft.gkzp.common;

import com.nsoft.gkzp.common.dao.HrRecruitFileDao;
import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Component
public class FileLoad {
    @Autowired
    ResultMsg resultMsg;

    @Autowired
    HrRecruitFileDao hrRecruitFileDao;

    /**
     * 上传文件
     * @param file 文件
     * @param filePath 路径
     * @return
     */
    public ResultMsg uploadFile(MultipartFile file,String filePath){
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
            hrRecruitFile.setName(fileName);
            hrRecruitFile.setUuidname(UUIDName);
            hrRecruitFileDao.insertSelective(hrRecruitFile);
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,fileName,hrRecruitFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"上传失败");
        }

        return resultMsg;
    }

    /**
     * 下载文件
     * @param response
     * @param id    文件id
     * @param filePath 路径
     * @throws Exception
     */
    public void downloadFile(HttpServletResponse response,int id,String filePath) throws Exception{
        //查询文件
        HrRecruitFile hrRecruitFileById = hrRecruitFileDao.selectByPrimaryKey(id);
        //设置文件名称
        String fileName = hrRecruitFileById.getName();
        String UUIDName = hrRecruitFileById.getUuidname();
        //文件路径
        //String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER;

        File file = new File(filePath , UUIDName);
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
}

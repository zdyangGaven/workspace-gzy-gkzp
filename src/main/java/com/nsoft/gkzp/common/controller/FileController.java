package com.nsoft.gkzp.common.controller;


import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.common.service.HrRecruitFileService;
import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
public class FileController extends AbstractController {

    @Autowired
    private MyDefinedUtil myDefinedUtil;

    @Autowired
    ResultMsg resultMsg;

    @Autowired
    HrRecruitFileService hrRecruitFileService;

    @PostMapping("uploadFile")
    public ResultMsg multipleSave(@RequestParam("file") MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        String UUIDName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER;
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath+UUIDName));
            //保存进数据库
            HrRecruitFile hrRecruitFile = new HrRecruitFile();
            hrRecruitFile.setName(fileName);
            hrRecruitFile.setUuidname(UUIDName);
            hrRecruitFileService.add(hrRecruitFile);
            resultMsg.setResultMsg(1,fileName,hrRecruitFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setResultMsg(2,"上传失败");
        }

        return resultMsg;
    }

    // 文件下载相关代码
    @RequestMapping("downfile/{id}")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) throws Exception{
        //查询文件
        HrRecruitFile hrRecruitFileById = hrRecruitFileService.getHrRecruitFileById(id);
        //设置文件名称
        String fileName = hrRecruitFileById.getUuidname();
        //文件路径
        String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER;

        File file = new File(filePath , fileName);
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

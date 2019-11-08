package com.nsoft.gkzp.notice.controller;

import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.notice.service.HrRecruitArticleService;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.util.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
public class HrRecruitArticleController extends AbstractController {

    @Autowired
    HrRecruitArticleService hrRecruitArticleService;

    /**
     * 查询公告数据
     * @param page 分页
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    @RequestMapping("/HrRecruitArticleController/list")
    public List<HrRecruitArticle> list(HrRecruitArticle hrRecruitArticle, String order, PageVo page){
        try {
            return hrRecruitArticleService.list(hrRecruitArticle,order, page);
        } catch (Exception e) {
            logger.error("公告出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @RequestMapping("notice/getHrRecruitArticleById")
    public HrRecruitArticle getHrRecruitArticleById(int id){
        try {
            return hrRecruitArticleService.getHrRecruitArticleById(id);
        } catch (Exception e) {
            logger.error("公告详情出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 模糊搜索公告
     * @param title
     * @return
     */
    @RequestMapping("notice/getHrRecruitArticleByTitle")
    public List<HrRecruitArticle> getHrRecruitArticleByTitle(String title){
        try {
            if(title.equals("") || title == null) return null;
            HrRecruitArticle hrRecruitArticle = new HrRecruitArticle();
            hrRecruitArticle.setTitle(title);
            return hrRecruitArticleService.list(hrRecruitArticle,null,null);
        } catch (Exception e) {
            logger.error("模糊搜索公告出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 接收内网推送的文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("notice/syncFile")
    public void  syncFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 文件保存路径
        String filePath = "E:/upload/Form/";
        System.out.println("进入");
        //操作成功则返回OK
        String result = "";
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        System.out.println(multiRequest);
        //解析request，将结果放置在list中
        Map<String, List<MultipartFile>> fileMap = multiRequest.getMultiFileMap();
        for (String key : fileMap.keySet()) {
            List<MultipartFile> files = fileMap.get(key);
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileNamePath = file.getOriginalFilename();
                    String[] params = fileNamePath.split("\\.");
                    String filename = "";
                    int i = 0;
                    for (String str : params) {
                        i = i + 1;
                        if (StringUtils.isNotEmpty(filename)) {
                            if (i == params.length) {
                                filename = filename + "." + str;
                            } else {
                                filename = filename + "/" + str;
                            }
                        } else {
                            filename = str;
                        }
                    }

                    File iFile = new File(filePath + filename);
                    File iFileParent = iFile.getParentFile();
                    if (!iFileParent.exists()) {
                        iFileParent.mkdirs();
                    }
                    // 转存文件
                    file.transferTo(new File(filePath + filename));
                    result = "ok";
                }
            }
        }

        response.setContentType("text/html; charset=GBK");
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.println("ok");
        out.flush();
        out.close();

    }
}

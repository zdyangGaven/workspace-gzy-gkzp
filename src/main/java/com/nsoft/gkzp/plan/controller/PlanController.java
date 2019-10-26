package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.common.FileLoad;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.HrPostTypeService;
import com.nsoft.gkzp.plan.service.HrRecruitReviewRecordService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import com.nsoft.gkzp.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
public class PlanController extends AbstractController {
    //人才需求
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    //计划
    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    //类型
    @Autowired
    HrPostTypeService hrPostTypeService;

    //审核
    @Autowired
    HrRecruitReviewRecordService hrRecruitReviewRecordService;

    @Autowired
    private MyDefinedUtil myDefinedUtil;

    @Autowired
    FileLoad fileLoad;

    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    @RequestMapping("HrPostTypeController/list")
    public List<HrPostType> hrPostTypeControllerList( HrPostType hrPostType, String order,Page page){
        return hrPostTypeService.list(hrPostType,order, page);
    }

    /**
     * 计划的集合
     * @param page 分页
     * @return
     */
    @RequestMapping("plan/getPlanByCurrentTime")
    public List<HrRecuritPlan> getPlanByCurrentTime( HrRecuritPlan hrRecuritPlan, String order,Page page){
        //传入当前时间
        hrRecuritPlan.setStarttime(new Date());
        hrRecuritPlan.setEndtime(new Date());
        return hrRecuritPlanService.list(hrRecuritPlan,order, page);
    }

    //招聘技术人才需求
    /**
     * 查询数据
     * @param page
     * @param hrRecuritPlanNeeds
     * @param order
     * @return
     */
    @RequestMapping("plan/plan/hrRecuritPlanNeeds/list")
    public List<HrRecuritPlanNeeds> hrRecuritPlanNeedsList( HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page){
        return hrRecuritPlanNeedsService.list(hrRecuritPlanNeeds,order, page);
    }


    /**
     * 根据计划获取人才需求
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @param page 分页
     * @return
     */
    @RequestMapping("/plan/getPlanNeedsListByPlan")
    public List<HrRecuritPlanNeedsDo> getPlanNeedsListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page){
        return hrRecuritPlanNeedsService.getListByPlan(hrRecuritPlanNeeds,order,page);
    }

    /**
     * 根据登录用户获取岗位信息和招聘计划
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/getHrRecuritPlanNeedsVoByUser")
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoByUser(userContext);
        hrRecuritPlanNeedsVoByUser.setHrRecruitEntryinfoBase(null);
        return hrRecuritPlanNeedsVoByUser;
    }

    //根据岗位id获取岗位信息和招聘计划
    @RequestMapping("plan/plan/getHrRecuritPlanNeedsVoById")
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoById(int id) {
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoById(id);
        return hrRecuritPlanNeedsVoByUser;
    }

    /**
     * 根据身份证号查询报名提交时间和资格审核
     * @param idCard   身份证号
     * @return
     */
    @RequestMapping("plan/plan/getHrRecruitReviewRecordVoByIdCard")
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByName(String idCard){
        //输入为空返回
        if(idCard.trim().equals("") || idCard == null) return null;

        HrRecruitReviewRecordVo result = new HrRecruitReviewRecordVo();

        HrRecruitEntryinfoBase infoBase = new HrRecruitEntryinfoBase();
        infoBase.setIdcardno(idCard);
        HrRecruitReviewRecordVo hrRecruitReviewRecordVo = hrRecruitReviewRecordService.getHrRecruitReviewRecordVoByInfoBase(infoBase);
        //资格审核为空返回null
        if(hrRecruitReviewRecordVo == null) return null;

        result.setHrRecruitReviewRecord(hrRecruitReviewRecordVo.getHrRecruitReviewRecord());
        //建一个新的基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        //只获取报名时间
        hrRecruitEntryinfoBase.setSignuptime(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase().getSignuptime());
        result.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);
        return result;
    }

    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("plan/plan/upload/img")
    public ResultMsg uploadImg(@RequestParam("file") MultipartFile file){

        //指定本地文件夹存储图片
        String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER_IMG;
        ResultMsg resultMsg = fileLoad.uploadFile(file, filePath);
        return resultMsg;
    }

    /**
     * 下载头像
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping("plan/plan/download/img/{id}")
    public void downloadImg(HttpServletResponse response, @PathVariable int id) throws Exception{
        fileLoad.downloadFile(response,id);
    }


    @RequestMapping("/download")
    public File download() throws Exception{
        InputStream f = new FileInputStream("F:/1.png");
        return null;
    }

}

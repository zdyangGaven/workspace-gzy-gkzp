package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.common.FileLoad;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.PageVo;
import com.nsoft.gkzp.util.ResultMsg;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
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

    //配置参数
    @Autowired
    private MyDefinedUtil myDefinedUtil;

    //民族
    @Autowired
    private UenumdataService uenumdataService;

    //基础信息
    @Autowired
    private HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

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
    public List<HrPostType> hrPostTypeControllerList(HrPostType hrPostType, String order, PageVo page){
        return hrPostTypeService.list(hrPostType,order, page);
    }

    /**
     * 计划的集合
     * @param page 分页
     * @return
     */
    @RequestMapping("plan/getPlanByCurrentTime")
    public List<HrRecuritPlan> getPlanByCurrentTime(HrRecuritPlan hrRecuritPlan, String order, PageVo page){
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
    public List<HrRecuritPlanNeeds> hrRecuritPlanNeedsList(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page){
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
    public List<HrRecuritPlanNeedsDo> getPlanNeedsListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page){
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
        if(hrRecuritPlanNeedsVoByUser != null)hrRecuritPlanNeedsVoByUser.setHrRecruitEntryinfoBase(null);
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
        System.out.println(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase());
        //只获取报名时间
        hrRecruitEntryinfoBase.setSignuptime(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase().getSignuptime());
        result.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);
        return result;
    }




    /**
     * 申请职位
     * @param id
     * @param request
     */
    @RequestMapping("intercept/plan/plan/planNeedsApply")
    public ResultMsg planNeedsApply(int id,HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        ResultMsg resultMsg = new ResultMsg();
        try {
            hrRecuritPlanNeedsService.planNeedsApply(id,userContext);
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"申请岗位成功");
            return resultMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"申请岗位失败");
        return resultMsg;
    }

    /**
     * 申请职位使用旧的信息
     * @param id
     * @param request
     */
    @RequestMapping("intercept/plan/plan/planNeedsApplyOldInfo")
    public ResultMsg planNeedsApplyOldInfo(int id,HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        ResultMsg resultMsg = new ResultMsg();
        try {
            //获取基础信息
            HrRecruitEntryinfo infoByUser = hrRecruitEntryinfoBaseService.getInfoByUser(userContext);
            JSONObject jsonObject = new JSONObject(infoByUser);

            //新增基础信息
            hrRecruitEntryinfoBaseService.add(jsonObject,userContext);
            //添加岗位
            hrRecuritPlanNeedsService.planNeedsApply(id,userContext);
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"申请岗位成功");
            return resultMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"申请岗位失败");
        return resultMsg;
    }

    /**
     * 申请职位  获取用户的当前状态  是否申请职位，申请职位时间
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/plan/getApplyByUser")
    public ResultMsg getApplyByUser(HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritPlanNeedsService.getApplyByUser(userContext);
    }



    /**
     * 查询民族
     * @return
     */
    @RequestMapping("plan/plan/uenumdataList")
    public List<Uenumdata> uenumdataList(){
        Uenumdata uenumdata = new Uenumdata();
        uenumdata.setEnumtypecode("SS_race");
        return uenumdataService.list(uenumdata,null,null);
    }

}

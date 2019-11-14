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
        try {
            return hrPostTypeService.list(hrPostType,order, page);
        } catch (Exception e) {
            logger.error("招聘类别出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 通过类型查询子类
     * @param typeString
     * @return
     */
    @RequestMapping("plan/plan/getPostTypeByTypeString")
    public List<HrPostType> getPostTypeByTypeString(String typeString){
        try {
            return hrPostTypeService.getPostTypeByTypeString(typeString);
        } catch (Exception e) {
            logger.error("招聘类别出错：typeString="+typeString,e);
        }
        return null;
    }

    /**
     * 计划的集合
     * @param page 分页
     * @return
     */
    @RequestMapping("plan/getPlanByCurrentTime")
    public List<HrRecuritPlan> getPlanByCurrentTime(HrRecuritPlan hrRecuritPlan, String order, PageVo page){
        try {
            //传入当前时间
            hrRecuritPlan.setStarttime(new Date());
            hrRecuritPlan.setEndtime(new Date());
            return hrRecuritPlanService.list(hrRecuritPlan,order, page);
        } catch (Exception e) {
            logger.error("获取计划出错:"+e.getMessage(),e);
        }
        return null;
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
        try {
            return hrRecuritPlanNeedsService.list(hrRecuritPlanNeeds,order, page);
        } catch (Exception e) {
            logger.error("获取岗位出错："+e.getMessage(),e);
        }
        return null;
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
        try {
            System.out.println(hrRecuritPlanNeeds);
            return hrRecuritPlanNeedsService.getListByPlan(hrRecuritPlanNeeds,order,page);
        } catch (Exception e) {
            logger.error("获取岗位出错："+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据登录用户获取岗位信息和招聘计划
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/getHrRecuritPlanNeedsVoByUser")
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByUser(HttpServletRequest request) {
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoByUser(userContext);
            if(hrRecuritPlanNeedsVoByUser != null)hrRecuritPlanNeedsVoByUser.setHrRecruitEntryinfoBase(null);
            return hrRecuritPlanNeedsVoByUser;
        } catch (Exception e) {
            logger.error("根据登录用户获取岗位信息和招聘计划错误："+e.getMessage(),e);
        }
        return null;
    }

    //根据岗位id获取岗位信息和招聘计划 岗位详情
    @RequestMapping("plan/plan/getHrRecuritPlanNeedsVoById")
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoById(int id) {
        try {
            HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoById(id);
            return hrRecuritPlanNeedsVoByUser;
        } catch (Exception e) {
            logger.error("岗位详情出错:"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据身份证号查询报名提交时间和资格审核
     * @param idCard   身份证号
     * @return
     */
    @RequestMapping("plan/plan/getHrRecruitReviewRecordVoByIdCard")
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByName(String idCard){
        try {
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
        } catch (Exception e) {
            logger.error("身份证号查询资格审核出错："+e.getMessage(),e);
        }
        return null;
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
            logger.error("申请职位报错:"+e.getMessage(),e);
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
            logger.error("申请职位使用旧的信息报错:"+e.getMessage(),e);
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
        try {
            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            return hrRecuritPlanNeedsService.getApplyByUser(userContext);
        } catch (Exception e) {
            logger.error("获取用户申请职位状态报错:"+e.getMessage(),e);
        }
        return null;
    }



    /**
     * 查询民族
     * @return
     */
    @RequestMapping("plan/plan/uenumdataList")
    public List<Uenumdata> uenumdataList(){
        try {
            Uenumdata uenumdata = new Uenumdata();
            uenumdata.setEnumtypecode("SS_race");
            return uenumdataService.list(uenumdata,null,null);
        } catch (Exception e) {
            logger.error("查询民族出错:"+e.getMessage(),e);
        }
        return null;
    }

}

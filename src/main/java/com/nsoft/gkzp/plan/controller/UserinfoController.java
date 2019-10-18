package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.ResultMsg;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class UserinfoController extends AbstractController {
    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    @Autowired
    ResultMsg resultMsg;

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

    /**
     * 根据登录用户查询信息
     * @param request
     * @return
     */
    @RequestMapping("plan/userInfo/getInfoByUser")
    public HrRecruitEntryinfo getInfoByUser(HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecruitEntryinfoBaseService.getInfoByUser(userContext);
    }


    /**
     * 基础信息新增
     * @param data
     * @return
     */
    @RequestMapping(value="plan/userInfo/add",method= RequestMethod.POST)
    public ResultMsg add(String data, HttpServletRequest request){
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            logger.info(userContext);
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);

            hrRecruitEntryinfoBaseService.add(jsonObject);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.NONE,"");
            return resultMsg;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
    @RequestMapping(value="plan/userInfo/edit",method= RequestMethod.POST)
    public ResultMsg edit(String data, HttpServletRequest request){
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);
            //JSONObject jsonObject = new JSONObject();
            hrRecruitEntryinfoBaseService.edit(jsonObject,userContext);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.NONE,"");
            return resultMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //错误信息
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"");
        return resultMsg;
    }

    /**
     * 通过用户获取体检数据
     * @param request
     * @return
     */
    @RequestMapping("plan/userInfo/getHrRecruitHealthchkByUser")
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecruitHealthchkService.getHrRecruitHealthchkByUser(userContext);
    }

    /**
     * 通过用户获取面试数据
     * @param request
     * @return
     */
    @RequestMapping("plan/userInfo/getHrRecuritInterviewByUser")
    public HrRecuritInterview getHrRecuritInterviewByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritInterviewService.getHrRecuritInterviewByUser(userContext);
    }

    /**
     * 通过用户获取笔试数据
     * @param request
     * @return
     */
    @RequestMapping("plan/userInfo/getHrRecuritWriteByUser")
    public HrRecuritWrite getHrRecuritWriteByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritWriteService.getHrRecuritWriteByUser(userContext);
    }

    /**
     * 通过用户获取资格审核
     * @param request
     * @return
     */
    @RequestMapping("plan/userInfo/getHrRecruitReviewRecordVoByUser")
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByUser(HttpServletRequest request) {
        HrRecruitReviewRecordVo result = new HrRecruitReviewRecordVo();
        
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        HrRecruitReviewRecordVo hrRecruitReviewRecordVo = hrRecruitReviewRecordService.getHrRecruitReviewRecordVoByUser(userContext);

        result.setHrRecruitReviewRecord(hrRecruitReviewRecordVo.getHrRecruitReviewRecord());
        //建一个新的基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        //只获取提交时间
        hrRecruitEntryinfoBase.setSubmittime(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase().getSubmittime());
        result.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);
        return result;
    }
}

package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfo;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
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
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    @Autowired
    ResultMsg resultMsg;


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
}

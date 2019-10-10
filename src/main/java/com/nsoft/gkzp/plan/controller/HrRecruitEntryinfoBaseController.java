package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrRecruitEntryinfo;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.util.ResultMsg;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class HrRecruitEntryinfoBaseController {
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    @Autowired
    ResultMsg resultMsg;

    @RequestMapping("HrRecruitEntryinfoBaseController/getInfoByUser")
    @ResponseBody
    public HrRecruitEntryinfo getInfoByUser(){
        return hrRecruitEntryinfoBaseService.getInfoByUser();
    }

    /**
     * 基础信息新增
     * @param data
     * @return
     */
    @RequestMapping(value="HrRecruitEntryinfoBaseController/add",method= RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(String data){
        try {
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


}

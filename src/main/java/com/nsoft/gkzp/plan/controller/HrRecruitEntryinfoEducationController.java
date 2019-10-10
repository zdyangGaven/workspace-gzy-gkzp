package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HrRecruitEntryinfoEducationController {
    @Autowired
    HrRecruitEntryinfoEducationService hrRecruitEntryinfoEducationService;
    //教育经历
    /**
     * 新增
     * @param list //获取json集合转成实体类集合
     * @return
     */
    /*@ResponseBody
    @RequestMapping(value = "HrRecruitEntryinfoEducationController/add",method= RequestMethod.POST)
    public int add(String list) throws Exception{

        list = URLDecoder.decode(list, "UTF-8");
        List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = JSONObject.parseArray(list, HrRecruitEntryinfoEducation.class);

        return hrRecruitEntryinfoEducationService.add(hrRecruitEntryinfoEducations);
    }*/
}

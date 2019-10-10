package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.plan.entity.HrPostType;
import com.nsoft.gkzp.plan.service.HrPostTypeService;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HrPostTypeController {
    @Autowired
    HrPostTypeService hrPostTypeService;

    /**
     * 招聘类别查询
     * @param page 分页
     * @param hrPostType
     * @param order 排序
     * @return
     */
    @RequestMapping("HrPostTypeController/list")
    public List<HrPostType> list(Page page, HrPostType hrPostType, String order){
        return hrPostTypeService.list(page,hrPostType,order);
    }
}

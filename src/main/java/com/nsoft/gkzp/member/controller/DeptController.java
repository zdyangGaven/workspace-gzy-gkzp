package com.nsoft.gkzp.member.controller;

import com.nsoft.gkzp.member.entity.Dept;
import com.nsoft.gkzp.member.service.DeptService;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping("DeptController/list")
    public List<Dept> list(Dept Dept, String order, PageVo page){
        return deptService.list(Dept,order,page);
    }

    @RequestMapping("dept/getDeptByDeptName")
    List<Dept> getDeptByDeptName(String deptName){
        return deptService.getDeptByDeptName(deptName);
    }
}

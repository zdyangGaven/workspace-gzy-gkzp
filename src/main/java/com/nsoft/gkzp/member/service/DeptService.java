package com.nsoft.gkzp.member.service;

import com.nsoft.gkzp.member.entity.Dept;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface DeptService {
    /**
     * 查询
     * @param dept
     * @param order
     * @param page
     * @return
     */
    List<Dept> list(Dept dept, String order, PageVo page);

    List<Dept> getDeptByDeptName(String deptName);
}

package com.nsoft.gkzp.member.service;

import com.nsoft.gkzp.member.entity.Dept;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface DeptService {
    List<Dept> list(Dept dept, String order, PageVo page);
}

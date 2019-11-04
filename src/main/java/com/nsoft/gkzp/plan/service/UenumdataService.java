package com.nsoft.gkzp.plan.service;

import com.nsoft.gkzp.plan.entity.Uenumdata;
import com.nsoft.gkzp.util.PageVo;

import java.util.List;

public interface UenumdataService {
    /**
     *  查询
     * @param page 分页
     * @param uenumdata
     * @param order 排序
     * @return
     */
    public List<Uenumdata> list(Uenumdata uenumdata, String order, PageVo page);
}

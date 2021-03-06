package com.nsoft.gkzp.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.member.dao.DeptDao;
import com.nsoft.gkzp.member.entity.Dept;
import com.nsoft.gkzp.member.service.DeptService;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptDao;

    /**
     * 查询
     * @param dept
     * @param order
     * @param page
     * @return
     */
    @Override
    public List<Dept> list(Dept dept, String order, PageVo page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(Dept.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(dept);

        List<Dept> list = deptDao.selectByExample(example);

        // 取分页信息
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(list);
        return list;
    }

    @Override
    public List<Dept> getDeptByDeptName(String deptName) {
        //查询出部门id
        Dept dept = new Dept();
        dept.setDeptname(deptName);
        List<Dept> select = deptDao.select(dept);
        if(select.size() == 0) return null;
        Integer parentId = select.get(0).getId();
        
        //查询部门的子类
        Dept deptChild = new Dept();
        deptChild.setParentid(parentId);
        return deptDao.select(deptChild);
    }
}

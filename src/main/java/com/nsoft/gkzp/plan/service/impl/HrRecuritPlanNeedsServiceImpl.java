package com.nsoft.gkzp.plan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecuritPlanNeedsDao;
import com.nsoft.gkzp.plan.dao.HrRecuritPlanNeedsMapper;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.HrRecruitEntryinfoBaseService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanNeedsService;
import com.nsoft.gkzp.plan.service.HrRecuritPlanService;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.DataFormat;
import com.nsoft.gkzp.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HrRecuritPlanNeedsServiceImpl implements HrRecuritPlanNeedsService {

    @Autowired
    HrRecuritPlanNeedsDao hrRecuritPlanNeedsDao;

    @Autowired
    HrRecuritPlanNeedsMapper hrRecuritPlanNeedsMapper;

    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    //招聘计划
    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    //人才需求
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    //时间的公共方法
    @Autowired
    DataFormat dataFormat;


    //招聘技术人才需求
    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecuritPlanNeeds> list( HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page) {
        return list(hrRecuritPlanNeeds,order,page,null);
    }
    @Override
    public List<HrRecuritPlanNeeds> list( HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page, List<Object> planIdList) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecuritPlanNeeds.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        Example.Criteria criteria = example.createCriteria();

        //模糊筛选
        if(hrRecuritPlanNeeds.getPostname() != null) criteria.andLike("postname","%"+hrRecuritPlanNeeds.getPostname()+"%");

        //招聘计划
        if(planIdList != null){
            //包含查询
            criteria.andIn("planId",planIdList);
            //清除
            hrRecuritPlanNeeds.setPlanId(null);
        }
        //清除
        hrRecuritPlanNeeds.setPostname(null);
        //筛选
        criteria.andEqualTo(hrRecuritPlanNeeds);



        List<HrRecuritPlanNeeds> list = hrRecuritPlanNeedsDao.selectByExample(example);


        // 取分页信息
        PageInfo<HrRecuritPlanNeeds> pageInfo = new PageInfo<HrRecuritPlanNeeds>(list);
        return list;
    }

    @Override
    public List<HrRecuritPlanNeedsDo> find(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, Page page, List<Object> planIdList) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        HrRecuritPlanNeedsExample hrRecuritPlanNeedsExample = new HrRecuritPlanNeedsExample();
        //排序
        if(order != null) hrRecuritPlanNeedsExample.setOrderByClause(order);

        HrRecuritPlanNeedsExample.Criteria criteria = hrRecuritPlanNeedsExample.createCriteria();

        //模糊筛选
        if(hrRecuritPlanNeeds.getPostname() != null) criteria.andPostnameLike("%"+hrRecuritPlanNeeds.getPostname()+"%");


        //岗位类型筛选
        if(hrRecuritPlanNeeds.getPosttype() != null) criteria.andPosttypeEqualTo(hrRecuritPlanNeeds.getPosttype());
        //部门筛选
        if(hrRecuritPlanNeeds.getDept() != null) criteria.andDeptEqualTo(hrRecuritPlanNeeds.getDept());
        //计划筛选
        if(hrRecuritPlanNeeds.getPlanId() != null) criteria.andPlanIdEqualTo(hrRecuritPlanNeeds.getPlanId());
        //学历
        if(hrRecuritPlanNeeds.getDegree() != null) criteria.andDegreeEqualTo(hrRecuritPlanNeeds.getDegree());

        List<HrRecuritPlanNeedsDo> list = hrRecuritPlanNeedsMapper.selectByExample(hrRecuritPlanNeedsExample);

        // 取分页信息
        PageInfo<HrRecuritPlanNeedsDo> pageInfo = new PageInfo<HrRecuritPlanNeedsDo>(list);
        return list;
    }

    /**
     * 根据岗位id获取岗位信息和招聘计划
     * @param id 岗位id
     * @return
     */
    @Override
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoById(int id) {
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVo = new HrRecuritPlanNeedsVo();
        //人才需求
        HrRecuritPlanNeeds hrRecuritPlanNeeds = new HrRecuritPlanNeeds();
        hrRecuritPlanNeeds.setId(id);
        List<HrRecuritPlanNeeds> needs = hrRecuritPlanNeedsService.list( hrRecuritPlanNeeds,null, null);
        hrRecuritPlanNeedsVo.setHrRecuritPlanNeeds(needs.get(0));

        //招聘计划
        HrRecuritPlan hrRecuritPlan = new HrRecuritPlan();
        hrRecuritPlan.setId(needs.get(0).getPlanId());
        List<HrRecuritPlan> plans = hrRecuritPlanService.list( hrRecuritPlan, null,null);
        hrRecuritPlanNeedsVo.setHrRecuritPlan(plans.get(0));
        return hrRecuritPlanNeedsVo;
    }

    /**
     *根据登录用户获取岗位信息和招聘计划
     * @param userContext 用户信息
     * @return
     */
    @Override
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByUser(UserContext userContext) {
        //基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVo = getHrRecuritPlanNeedsVoByHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);

        return hrRecuritPlanNeedsVo;
    }

    /**
     * 根据基础信息获取岗位信息和招聘计划和基础信息
     * @param hrRecruitEntryinfoBase
     * @return
     */
    @Override
    public HrRecuritPlanNeedsVo getHrRecuritPlanNeedsVoByHrRecruitEntryinfoBase(HrRecruitEntryinfoBase hrRecruitEntryinfoBase) {
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVo = new HrRecuritPlanNeedsVo();

        List<HrRecruitEntryinfoBase> bases = hrRecruitEntryinfoBaseService.list( hrRecruitEntryinfoBase, "id DESC",null);

        //判断是否有基础信息
        if(bases.size() > 0 ){
            hrRecuritPlanNeedsVo.setHrRecruitEntryinfoBase(bases.get(0));
            //判断是否关联计划id
            if(bases.get(0).getPostid() != null){
                int postId = bases.get(0).getPostid();
                hrRecuritPlanNeedsVo = getHrRecuritPlanNeedsVoById(postId);
            }
        }

        return hrRecuritPlanNeedsVo;
    }

    /**
     * 根据计划获取人才需求
     * @return
     */
    @Override
    public List<HrRecuritPlanNeedsDo> getListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order,Page page) {
        //获取当前所招聘的计划
        HrRecuritPlan hrRecuritPlan = new HrRecuritPlan();
        hrRecuritPlan.setStarttime(new Date());
        hrRecuritPlan.setEndtime(new Date());
        List<HrRecuritPlan> plans = hrRecuritPlanService.list( hrRecuritPlan,null, null);

        //获取招聘计划id的集合
        List<Object> planIds = new ArrayList<>();
        for ( HrRecuritPlan plan: plans) {
            planIds.add(plan.getId());
        }



        return find(hrRecuritPlanNeeds,order,page,planIds);
    }
}

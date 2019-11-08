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
import com.nsoft.gkzp.util.PageVo;
import com.nsoft.gkzp.util.ResultMsg;
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

    @Autowired
    ResultMsg resultMsg;

    //redis
    /*@Autowired
    JedisUtil jedisUtil;

    //序列化工具
    @Autowired
    SerializeUtil serializeUtil;*/

    //招聘技术人才需求
    /**
     * 查询数据
     * @param page 分页
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecuritPlanNeeds> list(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page) {
        return list(hrRecuritPlanNeeds,order,page,null);
    }

    /**
     * 查询数据
     * @param hrRecuritPlanNeeds
     * @param order 排序
     * @param page 分页
     * @param planIdList 计划id 包含查询
     * @return
     */
    @Override
    public List<HrRecuritPlanNeeds> list(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page, List<Object> planIdList) {
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
    public HrRecuritPlanNeeds getHrRecuritPlanNeedsById(int id) {
        return hrRecuritPlanNeedsDao.selectByPrimaryKey(id);
    }

    /**
     * 查询关联岗位类别
     * @param hrRecuritPlanNeeds
     * @param order
     * @param page
     * @param planIdList
     * @return
     */
    @Override
    public List<HrRecuritPlanNeedsDo> find(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page, List<Integer> planIdList) {
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

        //招聘计划
        if(planIdList != null){
            criteria.andPlanIdIn(planIdList);
        }
        
        // id
        if(hrRecuritPlanNeeds.getId() != null) criteria.andIdEqualTo(hrRecuritPlanNeeds.getId());
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
     * 通过id查询岗位 包括关联类别表的信息
     * @param id
     * @return
     */
    @Override
    public HrRecuritPlanNeedsDo findById(int id) {
        HrRecuritPlanNeedsExample hrRecuritPlanNeedsExample = new HrRecuritPlanNeedsExample();
        HrRecuritPlanNeedsExample.Criteria criteria = hrRecuritPlanNeedsExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<HrRecuritPlanNeedsDo> hrRecuritPlanNeedsDos = hrRecuritPlanNeedsMapper.selectByExample(hrRecuritPlanNeedsExample);

        if(hrRecuritPlanNeedsDos.size() == 0) return null;

        return hrRecuritPlanNeedsDos.get(0);
    }

    /**
     * 申请职位  获取用户的当前状态  是否申请职位，申请职位时间
     * @param userContext
     * @return
     */
    public ResultMsg getApplyByUser(UserContext userContext){
        ResultMsg resultMsg1 = new ResultMsg();
        //查询基础信息
        HrRecruitEntryinfoBase baseByUser = hrRecruitEntryinfoBaseService.getBaseByUser(userContext);
        if(baseByUser == null){
            resultMsg1.setResultMsg(ResultMsg.MsgType.INFO,"没填写基础信息",1);
            return resultMsg1;
        }

        //是否选择岗位 查询是否有计划
        if(baseByUser.getPlanid() == null || baseByUser.getPlanid() == 0){
            resultMsg1.setResultMsg(ResultMsg.MsgType.INFO,"填写基础信息，没申请岗位",2);
            return resultMsg1;
        }

        //查询计划
        HrRecuritPlan hrRecuritPlanById = hrRecuritPlanService.getHrRecuritPlanById(baseByUser.getPlanid());
        //计划结束时间
        Date endTime = null;
        if(hrRecuritPlanById != null) endTime = hrRecuritPlanById.getEndtime();
        if(endTime != null && endTime.before(new Date())) {//判断该计划是否结束
            resultMsg1.setResultMsg(ResultMsg.MsgType.INFO,"之前申请过岗位,有历史信息",3);
            return resultMsg1;
        } else {
            resultMsg1.setResultMsg(ResultMsg.MsgType.INFO,"已申请了岗位,不能在申请",4);
            return resultMsg1;
        }

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
        List<HrRecuritPlanNeedsDo> needs = hrRecuritPlanNeedsService.find( hrRecuritPlanNeeds,"id DESC", null,null);

        if(needs.size() == 0) return null;
        hrRecuritPlanNeedsVo.setHrRecuritPlanNeedsDo(needs.get(0));


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
        hrRecuritPlanNeedsVo.setHrRecruitEntryinfoBase(bases.get(0));
        //判断是否关联计划id
        if(bases.get(0).getPostid() != null && bases.get(0).getPostid() != 0){
            int postId = bases.get(0).getPostid();
            hrRecuritPlanNeedsVo = getHrRecuritPlanNeedsVoById(postId);
        }


        return hrRecuritPlanNeedsVo;
    }

    /**
     * 根据计划获取人才需求
     * @return
     */
    @Override
    public List<HrRecuritPlanNeedsDo> getListByPlan(HrRecuritPlanNeeds hrRecuritPlanNeeds, String order, PageVo page) {
        //redis测试读取
        /*try {
            Jedis jedis = jedisUtil.init();
            if(jedis.get("slk".getBytes()) != null ){
                List<HrRecuritPlanNeedsDo> hrRecuritPlanNeedsDos = (List<HrRecuritPlanNeedsDo>) serializeUtil.unSerialize(jedis.get("slk".getBytes()));


                return hrRecuritPlanNeedsDos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //获取当前所招聘的计划
        HrRecuritPlan hrRecuritPlan = new HrRecuritPlan();
        hrRecuritPlan.setStarttime(new Date());
        hrRecuritPlan.setEndtime(new Date());
        List<HrRecuritPlan> plans = hrRecuritPlanService.list( hrRecuritPlan,null, null);

        if(plans.size() == 0) return null;

        //获取招聘计划id的集合
        List<Integer> planIds = new ArrayList<>();
        for ( HrRecuritPlan plan: plans) {
            planIds.add(plan.getId());
        }

        List<HrRecuritPlanNeedsDo> list = find(hrRecuritPlanNeeds, order, page, planIds);

        //redis测试新增
        /*try {
            Jedis jedis = jedisUtil.init();
            jedis.set("slk".getBytes(),serializeUtil.serialize(list));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return list;
    }

    /**
     *新增
     * @param hrRecuritPlanNeeds
     */
    @Override
    public void add(HrRecuritPlanNeeds hrRecuritPlanNeeds) {
        hrRecuritPlanNeedsDao.insertSelective(hrRecuritPlanNeeds);
    }

    /**
     * 修改
     * @param hrRecuritPlanNeeds
     */
    @Override
    public void edit(HrRecuritPlanNeeds hrRecuritPlanNeeds) {
        hrRecuritPlanNeedsDao.updateByPrimaryKeySelective(hrRecuritPlanNeeds);
    }

    /**
     * 申请岗位
     * @param id 岗位id
     * @param userContext 登录用户
     */
    @Override
    public void planNeedsApply(int id, UserContext userContext) {
        //获取用户基础信息id
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);

        //查询岗位
        HrRecuritPlanNeeds hrRecuritPlanNeeds = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsById(id);
        //申请岗位
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setId(baseId);
        hrRecruitEntryinfoBase.setPlanid(hrRecuritPlanNeeds.getPlanId());
        hrRecruitEntryinfoBase.setPostid(hrRecuritPlanNeeds.getId());
        hrRecruitEntryinfoBase.setSignuptime(new Date());
        hrRecruitEntryinfoBase.setPosttypeid(hrRecuritPlanNeeds.getPosttype());
        hrRecruitEntryinfoBase.setSyncstatus(1);
        hrRecruitEntryinfoBaseService.edit(hrRecruitEntryinfoBase);
    }
}

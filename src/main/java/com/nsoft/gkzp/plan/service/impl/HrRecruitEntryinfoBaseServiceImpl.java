package com.nsoft.gkzp.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoBaseDao;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.Page;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class HrRecruitEntryinfoBaseServiceImpl extends AbstractService implements HrRecruitEntryinfoBaseService {
    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseDao hrRecruitEntryinfoBaseDao;
    //教育经历
    @Autowired
    HrRecruitEntryinfoEducationService hrRecruitEntryinfoEducationService;

    //家庭成员
    @Autowired
    HrRecruitEntryinfoFamilyService hrRecruitEntryinfoFamilyService;

    //其他信息
    @Autowired
    HrRecruitEntryinfoOtherService hrRecruitEntryinfoOtherService;

    //工作经历
    @Autowired
    HrRecruitEntryinfoWorkService hrRecruitEntryinfoWorkService;

    //招聘计划
    @Autowired
    HrRecuritPlanService hrRecuritPlanService;

    /**
     * 查询
     * @param page 分页
     * @param hrRecruitEntryinfoBase
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitEntryinfoBase> list( HrRecruitEntryinfoBase hrRecruitEntryinfoBase, String order,Page page) {
        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitEntryinfoBase.class);
        //排序
        if(order != null) example.setOrderByClause(order);

        //筛选
        example.createCriteria().andEqualTo(hrRecruitEntryinfoBase);

        List<HrRecruitEntryinfoBase> list = hrRecruitEntryinfoBaseDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitEntryinfoBase> pageInfo = new PageInfo<HrRecruitEntryinfoBase>(list);
        return list;
    }

    /**
     * 根据登录用户查询信息
     * @param userContext
     * @return
     */
    @Override
    public HrRecruitEntryinfo getInfoByUser(UserContext userContext) {
        HrRecruitEntryinfo hrRecruitEntryinfo = new HrRecruitEntryinfo();

        //基础信息
        Example example = new Example(HrRecruitEntryinfoBase.class);
        //筛选
        example.createCriteria().andEqualTo("loginuserid",userContext.getLoginUserId());
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = hrRecruitEntryinfoBaseDao.selectByExample(example);
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = hrRecruitEntryinfoBases.get(0);
        hrRecruitEntryinfo.setBaseInfo(hrRecruitEntryinfoBase);

        //教育经历
        HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation = new HrRecruitEntryinfoEducation();
        //根据基础信息id筛选
        hrRecruitEntryinfoEducation.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = hrRecruitEntryinfoEducationService.list( hrRecruitEntryinfoEducation, null,null);
        hrRecruitEntryinfo.setEducationInfo(hrRecruitEntryinfoEducations);

        //家庭成员
        HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily = new HrRecruitEntryinfoFamily();
        //根据基础信息id筛选
        hrRecruitEntryinfoFamily.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamilys = hrRecruitEntryinfoFamilyService.list( hrRecruitEntryinfoFamily, null,null);
        hrRecruitEntryinfo.setFamilyInfo(hrRecruitEntryinfoFamilys);

        //其他信息
        HrRecruitEntryinfoOther hrRecruitEntryinfoOther = new HrRecruitEntryinfoOther();
        //根据基础信息id筛选
        hrRecruitEntryinfoOther.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoOther> hrRecruitEntryinfoOthers = hrRecruitEntryinfoOtherService.list( hrRecruitEntryinfoOther, null,null);
        hrRecruitEntryinfo.setOtherInfo(hrRecruitEntryinfoOthers.get(0));

        //工作经历
        HrRecruitEntryinfoWork hrRecruitEntryinfoWork = new HrRecruitEntryinfoWork();
        //根据基础信息id筛选
        hrRecruitEntryinfoWork.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks = hrRecruitEntryinfoWorkService.list( hrRecruitEntryinfoWork,null, null);
        hrRecruitEntryinfo.setWorkInfo(hrRecruitEntryinfoWorks);

        return hrRecruitEntryinfo;
    }

    /**
     *根据登录用户获取基础信息id
     * @param userContext 用户信息
     * @return
     */
    @Override
    public int getBaseIdByUser(UserContext userContext) {
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = list(hrRecruitEntryinfoBase, "id DESC", null);
        //用户没有填基础信息退出返回-1
        if(hrRecruitEntryinfoBases.size() == 0) return -1;
        //返回基础信息id
        return hrRecruitEntryinfoBases.get(0).getId();
    }

    /**
     * 基础信息新增
     * @param jsonObject
     * @return
     */
    @Override
    public void add(JSONObject jsonObject) {
        try {
            //基础信息
            String baseInfo = jsonObject.getJSONObject("baseInfo").toString();
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = JSON.parseObject(baseInfo, HrRecruitEntryinfoBase.class);
            //id设为空
            hrRecruitEntryinfoBase.setId(null);
            //新增
            hrRecruitEntryinfoBaseDao.insertSelective(hrRecruitEntryinfoBase);
            //获取新增的id
            int baseId = hrRecruitEntryinfoBase.getId();

            //教育经历
            String educationInfo = jsonObject.getJSONArray("educationInfo").toString();
            List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = JSON.parseArray(educationInfo, HrRecruitEntryinfoEducation.class);
            hrRecruitEntryinfoEducationService.add(hrRecruitEntryinfoEducations,baseId);

            //家庭成员
            String familyInfo = jsonObject.getJSONArray("familyInfo").toString();
            List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamilys = JSON.parseArray(familyInfo, HrRecruitEntryinfoFamily.class);
            hrRecruitEntryinfoFamilyService.add(hrRecruitEntryinfoFamilys,baseId);

            //其他信息
            String otherInfo = jsonObject.getJSONObject("otherInfo").toString();
            HrRecruitEntryinfoOther hrRecruitEntryinfoOther = JSON.parseObject(otherInfo, HrRecruitEntryinfoOther.class);
            //添加基础id进行关联
            hrRecruitEntryinfoOther.setBaseid(baseId);
            //新增
            hrRecruitEntryinfoOtherService.add(hrRecruitEntryinfoOther);

            //工作经历
            String workInfo = jsonObject.getJSONArray("workInfo").toString();
            List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks = JSON.parseArray(workInfo, HrRecruitEntryinfoWork.class);
            hrRecruitEntryinfoWorkService.add(hrRecruitEntryinfoWorks,baseId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-基础信息新增报错",e);
        }
    }

    /**
     * 根据用户来进行判断是否修改还是新增
     * @param jsonObject
     * @param userContext
     */
    @Override
    public void edit(JSONObject jsonObject,UserContext userContext) {
        try{

            //根据登录用户获取基础信息
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
            hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());//userContext.getLoginUserId()
            List<HrRecruitEntryinfoBase> bases = list( hrRecruitEntryinfoBase, null,null);
            //没有关联基础信息
            if(bases.size() == 0){
                add(jsonObject);
                return;
            }else if(bases.get(0).getPlanid() == null){ //计划为空
                edit(jsonObject);
                return;
            }

                //有基础信息    转成bean进行修改
            /*String baseInfo = jsonObject.getJSONObject("baseInfo").toString();
            HrRecruitEntryinfoBase recruitEntryinfoBase = JSON.parseObject(baseInfo, HrRecruitEntryinfoBase.class);
            hrRecruitEntryinfoBaseDao.updateByPrimaryKeySelective(recruitEntryinfoBase);*/

            //获取计划
            HrRecuritPlan hrRecuritPlan = hrRecuritPlanService.getHrRecuritPlanById(bases.get(0).getPlanid());
            //计划结束时间
            Date endTime = null;
            if(hrRecuritPlan != null) endTime = hrRecuritPlan.getEndtime();

            if(endTime != null && endTime.before(new Date())){//判断该计划是否结束
                //结束进行新增
                add(jsonObject);
            } else {
                edit(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-基础信息报错",e);
        }
    }

    /**
     * 修改编辑
     * @param jsonObject
     */
    @Override
    public void edit(JSONObject jsonObject) {
        try {

            //基础信息
            JSONObject baseInfo = jsonObject.getJSONObject("baseInfo");
            //清空提交时间和修改时间
            baseInfo.put("submittime","");
            baseInfo.put("modifytime","");

            String baseInfoStr = baseInfo.toString();
            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = JSON.parseObject(baseInfoStr, HrRecruitEntryinfoBase.class);
            //新增
            hrRecruitEntryinfoBaseDao.updateByPrimaryKeySelective(hrRecruitEntryinfoBase);
            //获取基础信息的id
            int baseId = hrRecruitEntryinfoBase.getId();

            //教育经历
            String educationInfo = jsonObject.getJSONArray("educationInfo").toString();
            List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = JSON.parseArray(educationInfo, HrRecruitEntryinfoEducation.class);
            for (HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation:hrRecruitEntryinfoEducations) {
                //判断id是否为空 为空进行新增
                if(hrRecruitEntryinfoEducation.getId() == null){
                    //关联基础信息
                    hrRecruitEntryinfoEducation.setBaseid(baseId);
                    hrRecruitEntryinfoEducationService.add(hrRecruitEntryinfoEducation);
                } else {
                    hrRecruitEntryinfoEducationService.edit(hrRecruitEntryinfoEducation);
                }
            }

            //家庭成员
            String familyInfo = jsonObject.getJSONArray("familyInfo").toString();
            List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamilys = JSON.parseArray(familyInfo, HrRecruitEntryinfoFamily.class);
            for (HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily:hrRecruitEntryinfoFamilys) {
                //判断id是否为空 为空进行新增
                if(hrRecruitEntryinfoFamily.getId() == null){
                    //关联基础信息
                    hrRecruitEntryinfoFamily.setBaseid(baseId);
                    hrRecruitEntryinfoFamilyService.add(hrRecruitEntryinfoFamily);
                } else {
                    hrRecruitEntryinfoFamilyService.edit(hrRecruitEntryinfoFamily);
                }
            }

            //其他信息
            String otherInfo = jsonObject.getJSONObject("otherInfo").toString();
            HrRecruitEntryinfoOther hrRecruitEntryinfoOther = JSON.parseObject(otherInfo, HrRecruitEntryinfoOther.class);
            //修改
            hrRecruitEntryinfoOtherService.edit(hrRecruitEntryinfoOther);

            //工作经历
            String workInfo = jsonObject.getJSONArray("workInfo").toString();
            List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks = JSON.parseArray(workInfo, HrRecruitEntryinfoWork.class);
            for (HrRecruitEntryinfoWork hrRecruitEntryinfoWork:hrRecruitEntryinfoWorks) {
                //判断id是否为空 为空进行新增
                if(hrRecruitEntryinfoWork.getId() == null){
                    //关联基础信息
                    hrRecruitEntryinfoWork.setBaseid(baseId);
                    hrRecruitEntryinfoWorkService.add(hrRecruitEntryinfoWork);
                } else {
                    hrRecruitEntryinfoWorkService.edit(hrRecruitEntryinfoWork);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("报名-基础信息修改报错",e);
        }
    }
}

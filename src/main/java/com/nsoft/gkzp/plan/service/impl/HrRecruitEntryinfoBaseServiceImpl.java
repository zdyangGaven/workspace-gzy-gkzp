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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    //前端的时间格式进行格式化
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

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

        //时间格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        //基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBaseSel = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBaseSel.setLoginuserid(userContext.getLoginUserId());
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = list(hrRecruitEntryinfoBaseSel, "id DESC", null);
        //没有基础信息返回null
        if(hrRecruitEntryinfoBases.size() == 0) return null;
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = hrRecruitEntryinfoBases.get(0);
        if(hrRecruitEntryinfoBase.getBirthdate() != null && !hrRecruitEntryinfoBase.getBirthdate().equals("")){
            //转换生日时间
            hrRecruitEntryinfoBase.setBirthdateStr(sdf.format(hrRecruitEntryinfoBase.getBirthdate()));
        }
        System.out.println("hrRecruitEntryinfoBase"+hrRecruitEntryinfoBase);

        hrRecruitEntryinfo.setBaseInfo(hrRecruitEntryinfoBase);


        //教育经历
        HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation = new HrRecruitEntryinfoEducation();
        //根据基础信息id筛选
        hrRecruitEntryinfoEducation.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = hrRecruitEntryinfoEducationService.list( hrRecruitEntryinfoEducation, null,null);
        List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducationResult = new ArrayList<>();

        for (HrRecruitEntryinfoEducation hrRecruitEntryinfoEducationEach:hrRecruitEntryinfoEducations) {

            if(hrRecruitEntryinfoEducationEach.getStarttime() != null){

                //起止时间
                String[] duration = new String[2];
                duration[0] = sdf.format(hrRecruitEntryinfoEducationEach.getStarttime());
                duration[1] = sdf.format(hrRecruitEntryinfoEducationEach.getEndtime());

                //清除开始结束时间
                hrRecruitEntryinfoEducationEach.setStarttime(null);
                hrRecruitEntryinfoEducationEach.setEndtime(null);

                hrRecruitEntryinfoEducationEach.setDuration(duration);

            }
            hrRecruitEntryinfoEducationResult.add(hrRecruitEntryinfoEducationEach);
        }

        //hrRecruitEntryinfo.setEducationInfo(hrRecruitEntryinfoEducationResult);
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

        if(hrRecruitEntryinfoOthers.size() > 0){
            hrRecruitEntryinfo.setOtherInfo(hrRecruitEntryinfoOthers.get(0));
        }


        //工作经历
        HrRecruitEntryinfoWork hrRecruitEntryinfoWork = new HrRecruitEntryinfoWork();
        //根据基础信息id筛选
        hrRecruitEntryinfoWork.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks = hrRecruitEntryinfoWorkService.list( hrRecruitEntryinfoWork,null, null);
        List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorkResult = new ArrayList<>();
        for (HrRecruitEntryinfoWork hrRecruitEntryinfoWorkEach:hrRecruitEntryinfoWorks) {
            if(hrRecruitEntryinfoWorkEach.getStarttime() != null) {
                //起止时间
                String[] duration = new String[2];
                duration[0] = sdf.format(hrRecruitEntryinfoWorkEach.getStarttime());
                duration[1] = sdf.format(hrRecruitEntryinfoWorkEach.getEndtime());

                //清除开始结束时间
                hrRecruitEntryinfoWorkEach.setStarttime(null);
                hrRecruitEntryinfoWorkEach.setEndtime(null);

                hrRecruitEntryinfoWorkEach.setDuration(duration);
            }

            hrRecruitEntryinfoWorkResult.add(hrRecruitEntryinfoWorkEach);
        }
        hrRecruitEntryinfo.setWorkInfo(hrRecruitEntryinfoWorkResult);

        return hrRecruitEntryinfo;
    }


    /**
     * 根据用户获取基础信息
     * @param userContext
     * @return
     */
    @Override
    public HrRecruitEntryinfoBase getBaseByUser(UserContext userContext) {
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());
        List<HrRecruitEntryinfoBase> hrRecruitEntryinfoBases = list(hrRecruitEntryinfoBase, "id DESC", null);
        //用户没有填基础信息退出返回null
        if(hrRecruitEntryinfoBases.size() == 0) return null;
        //返回基础信息
        return hrRecruitEntryinfoBases.get(0);
    }

    /**
     *根据登录用户获取基础信息id
     * @param userContext 用户信息
     * @return
     */
    @Override
    public int getBaseIdByUser(UserContext userContext) {
        HrRecruitEntryinfoBase baseByUser = getBaseByUser(userContext);
        //用户没有填基础信息退出返回-1
        if(baseByUser == null) return -1;
        //返回基础信息id
        return baseByUser.getId();
    }

    /**
     * 身份证验证是否存在     true存在
     * @param idCard
     * @return
     */
    @Override
    public boolean verifyIdCard(String idCard) {
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        hrRecruitEntryinfoBase.setIdcardno(idCard);
        List<HrRecruitEntryinfoBase> list = list(hrRecruitEntryinfoBase, null, null);
        if(list.size() > 0) return true;
        return false;
    }

    /**
     * 基础信息新增
     * @param jsonObject
     * @return
     */
    @Override
    public void add(JSONObject jsonObject,UserContext userContext) {
        try {
            //把其他的基础信息 设为旧信息
            Example example = new Example(HrRecruitEntryinfoBase.class);
            example.createCriteria().andEqualTo("loginuserid",userContext.getLoginUserId());

            HrRecruitEntryinfoBase infoBaseOld = new HrRecruitEntryinfoBase();
            infoBaseOld.setIsnewest(2);
            hrRecruitEntryinfoBaseDao.updateByExampleSelective(infoBaseOld,example);

            //基础信息
            JSONObject baseInfoJson = jsonObject.getJSONObject("baseInfo");
            System.out.println(jsonObject);
            //时间的处理
            if(baseInfoJson.has("birthdateStr") && !baseInfoJson.getString("birthdateStr").equals("")) baseInfoJson.put("birthdate",baseInfoJson.get("birthdateStr"));
            baseInfoJson.put("submittime","");
            baseInfoJson.put("modifytime","");
            baseInfoJson.put("signuptime","");

            String baseInfo = baseInfoJson.toString();

            HrRecruitEntryinfoBase hrRecruitEntryinfoBase = JSON.parseObject(baseInfo, HrRecruitEntryinfoBase.class);
            //id设为空
            hrRecruitEntryinfoBase.setId(null);
            //待同步
            hrRecruitEntryinfoBase.setSyncstatus(1);
            //提交时间
            hrRecruitEntryinfoBase.setSubmittime(new Date());
            //登录id
            hrRecruitEntryinfoBase.setLoginuserid(userContext.getLoginUserId());
            //岗位和计划为空
            hrRecruitEntryinfoBase.setPlanid(null);
            hrRecruitEntryinfoBase.setPostid(null);

            //新增
            hrRecruitEntryinfoBaseDao.insertSelective(hrRecruitEntryinfoBase);
            //获取新增的id
            int baseId = hrRecruitEntryinfoBase.getId();

            //教育经历
            String educationInfo = jsonObject.getJSONArray("educationInfo").toString();
            List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = JSON.parseArray(educationInfo, HrRecruitEntryinfoEducation.class);
            for (HrRecruitEntryinfoEducation hrRecruitEntryinfoEducation:hrRecruitEntryinfoEducations) {
                String[] duration = hrRecruitEntryinfoEducation.getDuration();
                //转换data格式并保存
                hrRecruitEntryinfoEducation.setStarttime(sdf.parse(duration[0]));
                hrRecruitEntryinfoEducation.setEndtime(sdf.parse(duration[1]));
                hrRecruitEntryinfoEducation.setDuration(null);

                //关联基础信息
                hrRecruitEntryinfoEducation.setBaseid(baseId);
                hrRecruitEntryinfoEducationService.add(hrRecruitEntryinfoEducation);

            }


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
            for (HrRecruitEntryinfoWork hrRecruitEntryinfoWork:hrRecruitEntryinfoWorks) {
                String[] duration = hrRecruitEntryinfoWork.getDuration();
                //转换data格式并保存
                hrRecruitEntryinfoWork.setStarttime(sdf.parse(duration[0]));
                hrRecruitEntryinfoWork.setEndtime(sdf.parse(duration[1]));
                hrRecruitEntryinfoWork.setDuration(null);
                //关联基础信息
                hrRecruitEntryinfoWork.setBaseid(baseId);
                hrRecruitEntryinfoWorkService.add(hrRecruitEntryinfoWork);
            }

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
            HrRecruitEntryinfoBase base = getBaseByUser(userContext);
            //没有关联基础信息
            if(base == null){
                add(jsonObject,userContext);
                return;
            }else if(base.getPlanid() == null){ //计划为空
                edit(jsonObject);
                return;
            }

                //有基础信息    转成bean进行修改
            /*String baseInfo = jsonObject.getJSONObject("baseInfo").toString();
            HrRecruitEntryinfoBase recruitEntryinfoBase = JSON.parseObject(baseInfo, HrRecruitEntryinfoBase.class);
            hrRecruitEntryinfoBaseDao.updateByPrimaryKeySelective(recruitEntryinfoBase);*/

            //获取计划
            HrRecuritPlan hrRecuritPlan = hrRecuritPlanService.getHrRecuritPlanById(base.getPlanid());
            //计划结束时间
            Date endTime = null;
            if(hrRecuritPlan != null) endTime = hrRecuritPlan.getEndtime();
            if(endTime != null && endTime.before(new Date())){//判断该计划是否结束
                //结束进行新增
                add(jsonObject,userContext);
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
            //清空提交时间和修改时间和报名时间
            /*baseInfo.put("submittime","");
            baseInfo.put("modifytime","");
            baseInfo.put("signuptime","");*/

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
                String[] duration = hrRecruitEntryinfoEducation.getDuration();
                //转换data格式并保存
                hrRecruitEntryinfoEducation.setStarttime(sdf.parse(duration[0]));
                hrRecruitEntryinfoEducation.setEndtime(sdf.parse(duration[1]));
                hrRecruitEntryinfoEducation.setDuration(null);

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
                String[] duration = hrRecruitEntryinfoWork.getDuration();
                //转换data格式并保存
                hrRecruitEntryinfoWork.setStarttime(sdf.parse(duration[0]));
                hrRecruitEntryinfoWork.setEndtime(sdf.parse(duration[1]));
                hrRecruitEntryinfoWork.setDuration(null);

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

    /**
     * 修改
     * @param hrRecruitEntryinfoBase
     */
    @Override
    public void edit(HrRecruitEntryinfoBase hrRecruitEntryinfoBase) {
        hrRecruitEntryinfoBaseDao.updateByPrimaryKeySelective(hrRecruitEntryinfoBase);
    }
}

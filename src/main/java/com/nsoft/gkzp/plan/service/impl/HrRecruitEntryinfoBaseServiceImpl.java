package com.nsoft.gkzp.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.nsoft.gkzp.plan.dao.HrRecruitEntryinfoBaseDao;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.service.AbstractService;
import com.nsoft.gkzp.syscore.service.ServiceException;
import com.nsoft.gkzp.syscore.web.UserContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        List<HrRecruitEntryinfoEducation> hrRecruitEntryinfoEducations = hrRecruitEntryinfoEducationService.list(null, hrRecruitEntryinfoEducation, null);
        hrRecruitEntryinfo.setEducationInfo(hrRecruitEntryinfoEducations);

        //家庭成员
        HrRecruitEntryinfoFamily hrRecruitEntryinfoFamily = new HrRecruitEntryinfoFamily();
        //根据基础信息id筛选
        hrRecruitEntryinfoFamily.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoFamily> hrRecruitEntryinfoFamilys = hrRecruitEntryinfoFamilyService.list(null, hrRecruitEntryinfoFamily, null);
        hrRecruitEntryinfo.setFamilyInfo(hrRecruitEntryinfoFamilys);

        //其他信息
        HrRecruitEntryinfoOther hrRecruitEntryinfoOther = new HrRecruitEntryinfoOther();
        //根据基础信息id筛选
        hrRecruitEntryinfoOther.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoOther> hrRecruitEntryinfoOthers = hrRecruitEntryinfoOtherService.list(null, hrRecruitEntryinfoOther, null);
        hrRecruitEntryinfo.setOtherInfo(hrRecruitEntryinfoOthers.get(0));

        //工作经历
        HrRecruitEntryinfoWork hrRecruitEntryinfoWork = new HrRecruitEntryinfoWork();
        //根据基础信息id筛选
        hrRecruitEntryinfoWork.setBaseid(hrRecruitEntryinfoBase.getId());
        List<HrRecruitEntryinfoWork> hrRecruitEntryinfoWorks = hrRecruitEntryinfoWorkService.list(null, hrRecruitEntryinfoWork, null);
        hrRecruitEntryinfo.setWorkInfo(hrRecruitEntryinfoWorks);

        return hrRecruitEntryinfo;
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
}

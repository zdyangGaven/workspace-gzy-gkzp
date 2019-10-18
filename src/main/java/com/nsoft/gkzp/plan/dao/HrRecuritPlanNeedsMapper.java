package com.nsoft.gkzp.plan.dao;

import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsDo;
import com.nsoft.gkzp.plan.entity.HrRecuritPlanNeedsExample;

import java.util.List;

public interface HrRecuritPlanNeedsMapper {
    List<HrRecuritPlanNeedsDo> selectByExample(HrRecuritPlanNeedsExample example);
}
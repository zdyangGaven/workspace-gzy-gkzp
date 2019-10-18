package com.nsoft.gkzp.plan.entity;

import java.util.ArrayList;
import java.util.List;

public class HrRecuritPlanNeedsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HrRecuritPlanNeedsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("Plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("Plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("Plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("Plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("Plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("Plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("Plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("Plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("Plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("Plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andDeptIsNull() {
            addCriterion("Dept is null");
            return (Criteria) this;
        }

        public Criteria andDeptIsNotNull() {
            addCriterion("Dept is not null");
            return (Criteria) this;
        }

        public Criteria andDeptEqualTo(Integer value) {
            addCriterion("Dept =", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotEqualTo(Integer value) {
            addCriterion("Dept <>", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThan(Integer value) {
            addCriterion("Dept >", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThanOrEqualTo(Integer value) {
            addCriterion("Dept >=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThan(Integer value) {
            addCriterion("Dept <", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThanOrEqualTo(Integer value) {
            addCriterion("Dept <=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptIn(List<Integer> values) {
            addCriterion("Dept in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotIn(List<Integer> values) {
            addCriterion("Dept not in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptBetween(Integer value1, Integer value2) {
            addCriterion("Dept between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotBetween(Integer value1, Integer value2) {
            addCriterion("Dept not between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNull() {
            addCriterion("PostName is null");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNotNull() {
            addCriterion("PostName is not null");
            return (Criteria) this;
        }

        public Criteria andPostnameEqualTo(String value) {
            addCriterion("PostName =", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotEqualTo(String value) {
            addCriterion("PostName <>", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThan(String value) {
            addCriterion("PostName >", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThanOrEqualTo(String value) {
            addCriterion("PostName >=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThan(String value) {
            addCriterion("PostName <", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThanOrEqualTo(String value) {
            addCriterion("PostName <=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLike(String value) {
            addCriterion("PostName like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotLike(String value) {
            addCriterion("PostName not like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameIn(List<String> values) {
            addCriterion("PostName in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotIn(List<String> values) {
            addCriterion("PostName not in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameBetween(String value1, String value2) {
            addCriterion("PostName between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotBetween(String value1, String value2) {
            addCriterion("PostName not between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnumIsNull() {
            addCriterion("PostNum is null");
            return (Criteria) this;
        }

        public Criteria andPostnumIsNotNull() {
            addCriterion("PostNum is not null");
            return (Criteria) this;
        }

        public Criteria andPostnumEqualTo(String value) {
            addCriterion("PostNum =", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumNotEqualTo(String value) {
            addCriterion("PostNum <>", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumGreaterThan(String value) {
            addCriterion("PostNum >", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumGreaterThanOrEqualTo(String value) {
            addCriterion("PostNum >=", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumLessThan(String value) {
            addCriterion("PostNum <", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumLessThanOrEqualTo(String value) {
            addCriterion("PostNum <=", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumLike(String value) {
            addCriterion("PostNum like", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumNotLike(String value) {
            addCriterion("PostNum not like", value, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumIn(List<String> values) {
            addCriterion("PostNum in", values, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumNotIn(List<String> values) {
            addCriterion("PostNum not in", values, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumBetween(String value1, String value2) {
            addCriterion("PostNum between", value1, value2, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostnumNotBetween(String value1, String value2) {
            addCriterion("PostNum not between", value1, value2, "postnum");
            return (Criteria) this;
        }

        public Criteria andPostintentionIsNull() {
            addCriterion("PostIntention is null");
            return (Criteria) this;
        }

        public Criteria andPostintentionIsNotNull() {
            addCriterion("PostIntention is not null");
            return (Criteria) this;
        }

        public Criteria andPostintentionEqualTo(Integer value) {
            addCriterion("PostIntention =", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionNotEqualTo(Integer value) {
            addCriterion("PostIntention <>", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionGreaterThan(Integer value) {
            addCriterion("PostIntention >", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionGreaterThanOrEqualTo(Integer value) {
            addCriterion("PostIntention >=", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionLessThan(Integer value) {
            addCriterion("PostIntention <", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionLessThanOrEqualTo(Integer value) {
            addCriterion("PostIntention <=", value, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionIn(List<Integer> values) {
            addCriterion("PostIntention in", values, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionNotIn(List<Integer> values) {
            addCriterion("PostIntention not in", values, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionBetween(Integer value1, Integer value2) {
            addCriterion("PostIntention between", value1, value2, "postintention");
            return (Criteria) this;
        }

        public Criteria andPostintentionNotBetween(Integer value1, Integer value2) {
            addCriterion("PostIntention not between", value1, value2, "postintention");
            return (Criteria) this;
        }

        public Criteria andPosttypeIsNull() {
            addCriterion("PostType is null");
            return (Criteria) this;
        }

        public Criteria andPosttypeIsNotNull() {
            addCriterion("PostType is not null");
            return (Criteria) this;
        }

        public Criteria andPosttypeEqualTo(Integer value) {
            addCriterion("PostType =", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeNotEqualTo(Integer value) {
            addCriterion("PostType <>", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeGreaterThan(Integer value) {
            addCriterion("PostType >", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PostType >=", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeLessThan(Integer value) {
            addCriterion("PostType <", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeLessThanOrEqualTo(Integer value) {
            addCriterion("PostType <=", value, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeIn(List<Integer> values) {
            addCriterion("PostType in", values, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeNotIn(List<Integer> values) {
            addCriterion("PostType not in", values, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeBetween(Integer value1, Integer value2) {
            addCriterion("PostType between", value1, value2, "posttype");
            return (Criteria) this;
        }

        public Criteria andPosttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("PostType not between", value1, value2, "posttype");
            return (Criteria) this;
        }

        public Criteria andPostconfigIsNull() {
            addCriterion("PostConfig is null");
            return (Criteria) this;
        }

        public Criteria andPostconfigIsNotNull() {
            addCriterion("PostConfig is not null");
            return (Criteria) this;
        }

        public Criteria andPostconfigEqualTo(String value) {
            addCriterion("PostConfig =", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigNotEqualTo(String value) {
            addCriterion("PostConfig <>", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigGreaterThan(String value) {
            addCriterion("PostConfig >", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigGreaterThanOrEqualTo(String value) {
            addCriterion("PostConfig >=", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigLessThan(String value) {
            addCriterion("PostConfig <", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigLessThanOrEqualTo(String value) {
            addCriterion("PostConfig <=", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigLike(String value) {
            addCriterion("PostConfig like", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigNotLike(String value) {
            addCriterion("PostConfig not like", value, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigIn(List<String> values) {
            addCriterion("PostConfig in", values, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigNotIn(List<String> values) {
            addCriterion("PostConfig not in", values, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigBetween(String value1, String value2) {
            addCriterion("PostConfig between", value1, value2, "postconfig");
            return (Criteria) this;
        }

        public Criteria andPostconfigNotBetween(String value1, String value2) {
            addCriterion("PostConfig not between", value1, value2, "postconfig");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesIsNull() {
            addCriterion("Specialities is null");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesIsNotNull() {
            addCriterion("Specialities is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesEqualTo(String value) {
            addCriterion("Specialities =", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesNotEqualTo(String value) {
            addCriterion("Specialities <>", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesGreaterThan(String value) {
            addCriterion("Specialities >", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesGreaterThanOrEqualTo(String value) {
            addCriterion("Specialities >=", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesLessThan(String value) {
            addCriterion("Specialities <", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesLessThanOrEqualTo(String value) {
            addCriterion("Specialities <=", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesLike(String value) {
            addCriterion("Specialities like", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesNotLike(String value) {
            addCriterion("Specialities not like", value, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesIn(List<String> values) {
            addCriterion("Specialities in", values, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesNotIn(List<String> values) {
            addCriterion("Specialities not in", values, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesBetween(String value1, String value2) {
            addCriterion("Specialities between", value1, value2, "specialities");
            return (Criteria) this;
        }

        public Criteria andSpecialitiesNotBetween(String value1, String value2) {
            addCriterion("Specialities not between", value1, value2, "specialities");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("Degree is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("Degree is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(Integer value) {
            addCriterion("Degree =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(Integer value) {
            addCriterion("Degree <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(Integer value) {
            addCriterion("Degree >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Degree >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(Integer value) {
            addCriterion("Degree <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(Integer value) {
            addCriterion("Degree <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<Integer> values) {
            addCriterion("Degree in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<Integer> values) {
            addCriterion("Degree not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(Integer value1, Integer value2) {
            addCriterion("Degree between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(Integer value1, Integer value2) {
            addCriterion("Degree not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("Age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("Age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(String value) {
            addCriterion("Age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(String value) {
            addCriterion("Age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(String value) {
            addCriterion("Age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(String value) {
            addCriterion("Age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(String value) {
            addCriterion("Age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(String value) {
            addCriterion("Age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLike(String value) {
            addCriterion("Age like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotLike(String value) {
            addCriterion("Age not like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<String> values) {
            addCriterion("Age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<String> values) {
            addCriterion("Age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(String value1, String value2) {
            addCriterion("Age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(String value1, String value2) {
            addCriterion("Age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("Title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("Title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(Integer value) {
            addCriterion("Title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(Integer value) {
            addCriterion("Title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(Integer value) {
            addCriterion("Title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(Integer value) {
            addCriterion("Title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(Integer value) {
            addCriterion("Title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(Integer value) {
            addCriterion("Title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<Integer> values) {
            addCriterion("Title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<Integer> values) {
            addCriterion("Title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(Integer value1, Integer value2) {
            addCriterion("Title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(Integer value1, Integer value2) {
            addCriterion("Title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPoliticIsNull() {
            addCriterion("Politic is null");
            return (Criteria) this;
        }

        public Criteria andPoliticIsNotNull() {
            addCriterion("Politic is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticEqualTo(Integer value) {
            addCriterion("Politic =", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticNotEqualTo(Integer value) {
            addCriterion("Politic <>", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticGreaterThan(Integer value) {
            addCriterion("Politic >", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticGreaterThanOrEqualTo(Integer value) {
            addCriterion("Politic >=", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticLessThan(Integer value) {
            addCriterion("Politic <", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticLessThanOrEqualTo(Integer value) {
            addCriterion("Politic <=", value, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticIn(List<Integer> values) {
            addCriterion("Politic in", values, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticNotIn(List<Integer> values) {
            addCriterion("Politic not in", values, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticBetween(Integer value1, Integer value2) {
            addCriterion("Politic between", value1, value2, "politic");
            return (Criteria) this;
        }

        public Criteria andPoliticNotBetween(Integer value1, Integer value2) {
            addCriterion("Politic not between", value1, value2, "politic");
            return (Criteria) this;
        }

        public Criteria andConditionIsNull() {
            addCriterion("Condition is null");
            return (Criteria) this;
        }

        public Criteria andConditionIsNotNull() {
            addCriterion("Condition is not null");
            return (Criteria) this;
        }

        public Criteria andConditionEqualTo(String value) {
            addCriterion("Condition =", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotEqualTo(String value) {
            addCriterion("Condition <>", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionGreaterThan(String value) {
            addCriterion("Condition >", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionGreaterThanOrEqualTo(String value) {
            addCriterion("Condition >=", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionLessThan(String value) {
            addCriterion("Condition <", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionLessThanOrEqualTo(String value) {
            addCriterion("Condition <=", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionLike(String value) {
            addCriterion("Condition like", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotLike(String value) {
            addCriterion("Condition not like", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionIn(List<String> values) {
            addCriterion("Condition in", values, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotIn(List<String> values) {
            addCriterion("Condition not in", values, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionBetween(String value1, String value2) {
            addCriterion("Condition between", value1, value2, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotBetween(String value1, String value2) {
            addCriterion("Condition not between", value1, value2, "condition");
            return (Criteria) this;
        }

        public Criteria andExaminationIsNull() {
            addCriterion("Examination is null");
            return (Criteria) this;
        }

        public Criteria andExaminationIsNotNull() {
            addCriterion("Examination is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationEqualTo(String value) {
            addCriterion("Examination =", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationNotEqualTo(String value) {
            addCriterion("Examination <>", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationGreaterThan(String value) {
            addCriterion("Examination >", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationGreaterThanOrEqualTo(String value) {
            addCriterion("Examination >=", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationLessThan(String value) {
            addCriterion("Examination <", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationLessThanOrEqualTo(String value) {
            addCriterion("Examination <=", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationLike(String value) {
            addCriterion("Examination like", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationNotLike(String value) {
            addCriterion("Examination not like", value, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationIn(List<String> values) {
            addCriterion("Examination in", values, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationNotIn(List<String> values) {
            addCriterion("Examination not in", values, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationBetween(String value1, String value2) {
            addCriterion("Examination between", value1, value2, "examination");
            return (Criteria) this;
        }

        public Criteria andExaminationNotBetween(String value1, String value2) {
            addCriterion("Examination not between", value1, value2, "examination");
            return (Criteria) this;
        }

        public Criteria andInterviewIsNull() {
            addCriterion("Interview is null");
            return (Criteria) this;
        }

        public Criteria andInterviewIsNotNull() {
            addCriterion("Interview is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewEqualTo(String value) {
            addCriterion("Interview =", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewNotEqualTo(String value) {
            addCriterion("Interview <>", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewGreaterThan(String value) {
            addCriterion("Interview >", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewGreaterThanOrEqualTo(String value) {
            addCriterion("Interview >=", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewLessThan(String value) {
            addCriterion("Interview <", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewLessThanOrEqualTo(String value) {
            addCriterion("Interview <=", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewLike(String value) {
            addCriterion("Interview like", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewNotLike(String value) {
            addCriterion("Interview not like", value, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewIn(List<String> values) {
            addCriterion("Interview in", values, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewNotIn(List<String> values) {
            addCriterion("Interview not in", values, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewBetween(String value1, String value2) {
            addCriterion("Interview between", value1, value2, "interview");
            return (Criteria) this;
        }

        public Criteria andInterviewNotBetween(String value1, String value2) {
            addCriterion("Interview not between", value1, value2, "interview");
            return (Criteria) this;
        }

        public Criteria andEmploymentIsNull() {
            addCriterion("Employment is null");
            return (Criteria) this;
        }

        public Criteria andEmploymentIsNotNull() {
            addCriterion("Employment is not null");
            return (Criteria) this;
        }

        public Criteria andEmploymentEqualTo(Integer value) {
            addCriterion("Employment =", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentNotEqualTo(Integer value) {
            addCriterion("Employment <>", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentGreaterThan(Integer value) {
            addCriterion("Employment >", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("Employment >=", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentLessThan(Integer value) {
            addCriterion("Employment <", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentLessThanOrEqualTo(Integer value) {
            addCriterion("Employment <=", value, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentIn(List<Integer> values) {
            addCriterion("Employment in", values, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentNotIn(List<Integer> values) {
            addCriterion("Employment not in", values, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentBetween(Integer value1, Integer value2) {
            addCriterion("Employment between", value1, value2, "employment");
            return (Criteria) this;
        }

        public Criteria andEmploymentNotBetween(Integer value1, Integer value2) {
            addCriterion("Employment not between", value1, value2, "employment");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("Reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("Reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("Reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("Reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("Reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("Reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("Reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("Reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("Reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("Reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("Reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("Reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("Reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("Reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("Remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("Remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("Remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("Remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("Remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("Remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("Remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("Remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("Remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("Remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("Remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("Remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("Remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("Remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andAffixfileIsNull() {
            addCriterion("AffixFile is null");
            return (Criteria) this;
        }

        public Criteria andAffixfileIsNotNull() {
            addCriterion("AffixFile is not null");
            return (Criteria) this;
        }

        public Criteria andAffixfileEqualTo(String value) {
            addCriterion("AffixFile =", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileNotEqualTo(String value) {
            addCriterion("AffixFile <>", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileGreaterThan(String value) {
            addCriterion("AffixFile >", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileGreaterThanOrEqualTo(String value) {
            addCriterion("AffixFile >=", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileLessThan(String value) {
            addCriterion("AffixFile <", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileLessThanOrEqualTo(String value) {
            addCriterion("AffixFile <=", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileLike(String value) {
            addCriterion("AffixFile like", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileNotLike(String value) {
            addCriterion("AffixFile not like", value, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileIn(List<String> values) {
            addCriterion("AffixFile in", values, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileNotIn(List<String> values) {
            addCriterion("AffixFile not in", values, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileBetween(String value1, String value2) {
            addCriterion("AffixFile between", value1, value2, "affixfile");
            return (Criteria) this;
        }

        public Criteria andAffixfileNotBetween(String value1, String value2) {
            addCriterion("AffixFile not between", value1, value2, "affixfile");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPostnoteIsNull() {
            addCriterion("PostNote is null");
            return (Criteria) this;
        }

        public Criteria andPostnoteIsNotNull() {
            addCriterion("PostNote is not null");
            return (Criteria) this;
        }

        public Criteria andPostnoteEqualTo(String value) {
            addCriterion("PostNote =", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteNotEqualTo(String value) {
            addCriterion("PostNote <>", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteGreaterThan(String value) {
            addCriterion("PostNote >", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteGreaterThanOrEqualTo(String value) {
            addCriterion("PostNote >=", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteLessThan(String value) {
            addCriterion("PostNote <", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteLessThanOrEqualTo(String value) {
            addCriterion("PostNote <=", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteLike(String value) {
            addCriterion("PostNote like", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteNotLike(String value) {
            addCriterion("PostNote not like", value, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteIn(List<String> values) {
            addCriterion("PostNote in", values, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteNotIn(List<String> values) {
            addCriterion("PostNote not in", values, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteBetween(String value1, String value2) {
            addCriterion("PostNote between", value1, value2, "postnote");
            return (Criteria) this;
        }

        public Criteria andPostnoteNotBetween(String value1, String value2) {
            addCriterion("PostNote not between", value1, value2, "postnote");
            return (Criteria) this;
        }

        public Criteria andDeptnameIsNull() {
            addCriterion("DeptName is null");
            return (Criteria) this;
        }

        public Criteria andDeptnameIsNotNull() {
            addCriterion("DeptName is not null");
            return (Criteria) this;
        }

        public Criteria andDeptnameEqualTo(String value) {
            addCriterion("DeptName =", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotEqualTo(String value) {
            addCriterion("DeptName <>", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThan(String value) {
            addCriterion("DeptName >", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThanOrEqualTo(String value) {
            addCriterion("DeptName >=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThan(String value) {
            addCriterion("DeptName <", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThanOrEqualTo(String value) {
            addCriterion("DeptName <=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLike(String value) {
            addCriterion("DeptName like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotLike(String value) {
            addCriterion("DeptName not like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameIn(List<String> values) {
            addCriterion("DeptName in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotIn(List<String> values) {
            addCriterion("DeptName not in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameBetween(String value1, String value2) {
            addCriterion("DeptName between", value1, value2, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotBetween(String value1, String value2) {
            addCriterion("DeptName not between", value1, value2, "deptname");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
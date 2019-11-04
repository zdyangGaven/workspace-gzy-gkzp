package com.nsoft.gkzp.notice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsoft.gkzp.common.FileLoad;
import com.nsoft.gkzp.common.entity.FileVo;
import com.nsoft.gkzp.notice.dao.HrRecruitArticleDao;
import com.nsoft.gkzp.notice.entity.HrRecruitArticle;
import com.nsoft.gkzp.notice.service.HrRecruitArticleService;
import com.nsoft.gkzp.util.DataFormat;
import com.nsoft.gkzp.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HrRecruitArticleServiceImpl implements HrRecruitArticleService {

    @Autowired
    HrRecruitArticleDao hrRecruitArticleDao;

    //数据转换
    @Autowired
    DataFormat dataFormat;

    //获取文件
    @Autowired
    FileLoad fileLoad;

    /**
     * 查询公告数据
     * @param page 分页
     * @param hrRecruitArticle
     * @param order 排序
     * @return
     */
    @Override
    public List<HrRecruitArticle> list(HrRecruitArticle hrRecruitArticle, String order, PageVo page) {

        //判断都有值通过
        if(page != null && page.getPageNum() != 0 && page.getPageSize() != 0){
            //分页处理，显示第一页的10条数据
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        Example example = new Example(HrRecruitArticle.class);

        //排序
        if(order != null) example.setOrderByClause(order);

        Example.Criteria criteria = example.createCriteria();

        //模糊筛选
        if(hrRecruitArticle.getTitle() != null) criteria.andLike("title","%"+hrRecruitArticle.getTitle()+"%");

        //清除
        hrRecruitArticle.setTitle(null);

        //筛选
        criteria.andEqualTo(hrRecruitArticle);

        List<HrRecruitArticle> list = hrRecruitArticleDao.selectByExample(example);

        // 取分页信息
        PageInfo<HrRecruitArticle> pageInfo = new PageInfo<HrRecruitArticle>(list);
        return list;
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public HrRecruitArticle getHrRecruitArticleById(int id) {
        HrRecruitArticle hrRecruitArticle = hrRecruitArticleDao.selectByPrimaryKey(id);
        //文件
        String affixfile = hrRecruitArticle.getAffixfile();
        if(affixfile != null && affixfile != ""){
            //查询文件
            Integer[] affixArr = dataFormat.stringArrToIntArr(affixfile.split(","));
            List<FileVo> fileList = fileLoad.getFileListByIds(affixArr);
            hrRecruitArticle.setAffixfileList(fileList);
        }
        //阅读次数
        hrRecruitArticle.setClicktimes(hrRecruitArticle.getClicktimes()+1);

        return hrRecruitArticle;
    }

    /**
     * 新增
     * @param hrRecruitArticle
     */
    @Override
    public void add(HrRecruitArticle hrRecruitArticle) {
        hrRecruitArticleDao.insertSelective(hrRecruitArticle);
    }

    /**
     * 修改
     * @param hrRecruitArticle
     */
    @Override
    public void edit(HrRecruitArticle hrRecruitArticle) {
        hrRecruitArticleDao.updateByPrimaryKeySelective(hrRecruitArticle);
    }
}

package com.nsoft.gkzp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import com.nsoft.gkzp.demo.service.EmployeesDemoService;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.ControllerException;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.syscore.web.JSONFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author zdyang
 * @date 2019.08.27
 */
@Controller
public class EmployeesDemoController extends AbstractController {

    @Autowired
    EmployeesDemoService demoService;

    /**
     * demo   保存
     * @param demoInfo  页面参数（映射为 entity类）
     * @param session   session
     * @param model     model
     */
    @ResponseBody
    @PostMapping("/demo/save.json") // 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
    public void  save(EmployeesDemo demoInfo, HttpSession session, Model model,Writer writer) throws ControllerException {
        this.userContext = new UserContext();
        try{
            logger.info("**************EmployeesDemoControlle.save参数="+demoInfo);

            if(demoInfo != null &&!"".equals(demoInfo.getName())){
             //   demoService.save(demoInfo);
                this.addErrorMessage("dadadadada");
            }
            writer.write(JSONFactory.toJSONString(userContext));

        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("雇员信息保存失败",e,userContext);
        }finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        throw new ControllerException("故意的抛出异常",userContext);
    }

    /**
     *  demo
     * @param arg0       request
     * @param arg1       response
     * @param session    session
     * @param model      模板
     * @return
     */
    @PostMapping("/demo/getIdByName")
    public String  getIdByName(HttpServletRequest arg0, HttpServletResponse arg1, HttpSession session, Model model)throws ControllerException {
        int id = -1;
        String  name = "";
        try{
            if(arg0.getParameter("name") != null){
                name =  arg0.getParameter("name");
            }
            logger.info("**************参数="+name);
           id =  demoService.getIdByName(name);
           model.addAttribute("id",id);


        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("获得ID失败",e,userContext);
        }
        return "demo/test2";

    }


    /**
     * demo
     * @param type    =1  返回以类EmployeesDemo组成的list     !=1 返回以hashmap组成的list
     * @return
     */
    @ResponseBody   //返回json数据
    @RequestMapping("/demo/getEmployees")
    public List<EmployeesDemo> getEmployees(int type)throws ControllerException {
        List<EmployeesDemo> userList =  null;
        List userList2 =  null;
        EmployeesDemo aa = null;
        HashMap  bb = new HashMap();
        try {
            if (type == 1) {
                userList = demoService.selectEmployees();
                if (userList != null && !userList.isEmpty()) {
                    Iterator it = userList.iterator();
                    while (it.hasNext()) {
                        aa = (EmployeesDemo) it.next();
                        logger.info("***********selectEmployees=" + aa);
                    }

                }
                logger.info("***********selectEmployees=" + userList);
            } else {
                userList2 = demoService.selectEmployeesToMap();
                if (userList2 != null && !userList2.isEmpty()) {
                    Iterator it = userList2.iterator();
                    while (it.hasNext()) {
                        bb = (HashMap) it.next();
                        logger.info("***********selectEmployeesToMap-bb=" + bb);
                    }

                }
                logger.info("***********selectEmployeesToMap=" + userList2);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("查询信息失败",e,userContext);
        }
        return type==1?userList:userList2;
    }


    /**
     * demo
     * @return
     */
    @ResponseBody   //返回json数据
    @RequestMapping("/demo/getTest")
    public String getTest()throws ControllerException {
        List list = new ArrayList();
        list.add("id: 1, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).'");
        list.add("id: 2, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).'");
        list.add("id: 3, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).'");
        net.sf.json.JSONArray array = new net.sf.json.JSONArray();
        array.add(list);
      String aa ="["
              +" { id: 1, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).'},"
              +" { id: 2, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' },"
              +" { id: 3, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' },"
              +" { id: 4, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' },"
              +" { id: 5, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' },"
              +" { id: 6, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' }"
              +" ]";

      aa ="{ id: 1, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).'}";
      net.sf.json.JSONObject bb = net.sf.json.JSONObject.fromObject(aa);
        net.sf.json.JSONArray array2 = new net.sf.json.JSONArray();
        array2.add(bb);
        aa ="{ id: 2, date: '2019-07-06', title: '广西职业技术学院2019年公开招聘高层次人才公告', description: '根据《广西职业技术学院招调急需岗位人员公告》有关规定，经过报名、资格审查、面试，考核、体检等程序，并经学院领导班子集体研究决定，拟调动覃华燕、李艳红、杨珍珍等3名同志到广西职业技术学院工作，现予以公示，公示期为5个工作日(2019年6月14日- -6月20日).' }";
        bb =  net.sf.json.JSONObject.fromObject(aa);
        array2.add(bb);
        return array2.toString();
    }


    /**
     *  demo
     * @return
     */

    @RequestMapping("/demo/findInfoByColumn")
    public String  findInfoByColumn(String column,String value, String status)throws ControllerException {
        EmployeesDemo  info = null;
        try{
          info =   demoService.findInfoByColumn("name","张三",1);

        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("查询信息失败",e,userContext);
        }

        return "/jsp/demo/test1";
    }

    /**demo   分页查询
     *
     * @param pageNum  当前页数
     * @param pageSize 当前页最多显示多少行
     * @return
     */
    @ResponseBody   //返回json数据
    @GetMapping("/demo/findByPaging")
    public  String findByPaging(Integer pageNum, Integer pageSize)throws ControllerException {
        int age = 27;
        JSONObject result = new JSONObject();
        try{
            PageHelper.startPage(pageNum,pageSize);
            Page<EmployeesDemo> data = demoService.findByPaging( age);

            result.put("employees",data);//
            //获取页面总数
            result.put("pages",data.getPages());
            //获取数据总数
            result.put("total",data.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("分页查询失败",e,userContext);
        }

        return result !=null?result.toString():null;
    }




}

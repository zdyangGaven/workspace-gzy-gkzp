package com.nsoft.gkzp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nsoft.gkzp.demo.entity.EmployeesDemo;
import com.nsoft.gkzp.demo.service.EmployeesDemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author zdyang
 * @date 2019.08.27
 */
@Controller
public class EmployeesDemoController {
    Logger logger = LogManager.getLogger(EmployeesDemoController.class);

    @Autowired
    EmployeesDemoService demoService;

    /**
     * demo   保存
     * @param demoInfo  页面参数（映射为 entity类）
     * @param session   session
     * @param model     model
     */
    @ResponseBody
    @PostMapping("/demo/save") // 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
    public String  save(EmployeesDemo demoInfo, HttpSession session, Model model){
        try{
            logger.info("**************EmployeesDemoControlle.save参数="+demoInfo);
            if(demoInfo != null &&!"".equals(demoInfo.getName())){
                demoService.save(demoInfo);
            }

            //writer.write("保存成功了，啦啦啦啦");

        }catch (Exception e){
            e.printStackTrace();
        }
        return "成功保存了";
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
    public String  getIdByName(HttpServletRequest arg0, HttpServletResponse arg1, HttpSession session, Model model){
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
        }
       // return "demo/test2";
        return "success";
    }


    /**
     * demo
     * @param type    =1  返回以类EmployeesDemo组成的list     !=1 返回以hashmap组成的list
     * @return
     */
    @ResponseBody   //返回json数据
    @RequestMapping("/demo/getEmployees")
    public List<EmployeesDemo> getEmployees(int type){
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
        }
        return type==1?userList:userList2;
    }


    /**
     *  demo
     * @return
     */

    @RequestMapping("/demo/findInfoByColumn")
    public String  findInfoByColumn(String column,String value, String status){
        EmployeesDemo  info = null;
        try{
          info =   demoService.findInfoByColumn("name","张三",1);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "/demo/test1";
    }

    /**demo   分页查询
     *
     * @param pageNum  当前页数
     * @param pageSize 当前页最多显示多少行
     * @return
     */
    @ResponseBody   //返回json数据
    @GetMapping("/demo/findByPaging")
    public  String findByPaging(Integer pageNum, Integer pageSize){
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
        }catch(Exception e){
            e.printStackTrace();
        }

        return result !=null?result.toString():null;
    }




}

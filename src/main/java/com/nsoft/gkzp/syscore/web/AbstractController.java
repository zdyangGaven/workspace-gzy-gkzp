package com.nsoft.gkzp.syscore.web;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * 前台页面 Web 层基础Controller，所有的Controller必须继承AbstractController
 *
 * @author zdyang
 * @date 2019.08.29
 *
 */
public abstract class AbstractController {


    final protected Logger logger = LogManager.getLogger(getClass());

    //Web上下文
    protected UserContext userContext = null;



    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }




/*
     //Workflow
      @Autowired
      protected WorkflowInterface workflow = null;

    //DataGrid分页服务，每次重新加载。
       @Autowired
       protected DataGridService dataGridService = null;
 */
}


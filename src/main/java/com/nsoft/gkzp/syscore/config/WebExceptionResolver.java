package com.nsoft.gkzp.syscore.config;


import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsoft.gkzp.syscore.web.ControllerException;
import com.nsoft.gkzp.syscore.web.UserContext;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.nsoft.gkzp.syscore.repository.DataResult ;
import com.nsoft.gkzp.syscore.web.JSONFactory;

/**
 *
 * 对于Web请求产生异常后的处理
 *
 * @author zdyang
 * @date  2019.08.29
 *
 */
@Configuration
public class WebExceptionResolver implements HandlerExceptionResolver {

    final private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(WebExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {

        String uri = arg0.getRequestURI().toLowerCase();
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(arg0, "userContext");

        if (uri.lastIndexOf(".json") > 0) {   //.json结尾标识是获取数据  //非 .json结尾的标识是页面跳转

            DataResult dataResult = new DataResult();

            logger.error(arg3.getMessage());

            if (arg3 instanceof ControllerException == false) {
                userContext.setErrorMessage("系统产生异常：" + arg3.getMessage());
            }

//            arg1.resetBuffer();
//            arg1.setContentType("text/html; charset=UTF-8");
//            arg1.setHeader("Pragma", "no-cache");
//            arg1.setHeader("Cache-Control", "no-cache, must-revalidate");
            Writer writer = null;

            try {
                writer = arg1.getWriter();
                writer.write(JSONFactory.toJSONString(dataResult, userContext));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        } else {

            ModelAndView model = new ModelAndView();
            model.setViewName("error500");
            Object errorPage = arg0.getAttribute("errorPage");
            logger.error("********errorPage="+errorPage);
            if (errorPage != null) {

                model.setViewName(errorPage.toString());

                if (arg3 instanceof ControllerException) {
                    userContext.setErrorMessage(arg3.getMessage());
                } else {
                    userContext.setErrorMessage("系统产生异常！");
                }
            }

            return model;
            //   }
        }

    }

}


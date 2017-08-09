package org.mfm.sys.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.mfm.basic.util.PropertiesUtil;
import org.mfm.sys.org.iservice.IOrgTypeService;
import org.mfm.sys.web.context.BeanFactoryContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static WebApplicationContext wc;
    private static String realpath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //初始化spring的工厂
        InitServlet.wc = WebApplicationContextUtils
            .getWebApplicationContext(this.getServletContext());
        InitServlet.realpath = config.getServletContext().getRealPath("");
        List<String> orgTypes = this.initOrg();
        List<Integer> otids = this.initOrgTypeId(InitServlet.wc, orgTypes);
        System.out.println(otids);
        config.getServletContext().setAttribute("existOrgTypes", otids);
        BeanFactoryContext.setWc(InitServlet.wc);
    }

    private List<Integer> initOrgTypeId(WebApplicationContext wc,
            List<String> orgTypes) {
        IOrgTypeService orgTypeService = (IOrgTypeService) wc
            .getBean("orgTypeService");
        List<Integer> otids = new ArrayList<Integer>();
        for (String ot : orgTypes) {
            otids.add(orgTypeService.loadBySn(ot).getId());
        }
        return otids;
    }

    @SuppressWarnings("rawtypes")
    private List<String> initOrg() {
        Properties prop = PropertiesUtil.getInstance().load("tyut");
        Enumeration e = prop.propertyNames();
        List<String> orgs = new ArrayList<String>();
        while (e.hasMoreElements()) {
            orgs.add(prop.getProperty(e.nextElement().toString()));
        }
        return orgs;
    }

    public static String getRealpath() {
        return InitServlet.realpath;
    }

    public static WebApplicationContext getWc() {
        return InitServlet.wc;
    }

}

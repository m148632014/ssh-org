package org.mfm.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.mfm.sys.web.InitServlet;
import org.mfm.sys.web.filter.SystemContextFilter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        //Spring基本配置加载
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SysOrgConfig.class, JpaConfig.class,
            MvcConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));
        //SpringMVC配置
        ServletRegistration.Dynamic dispatcher = container
            .addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        //其他filters,servlets配置
        FilterRegistration.Dynamic encodingFilter = container
            .addFilter("characterFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic openSessionInViewerFilter = container
            .addFilter("openSessionInViewerFilter",
                OpenSessionInViewFilter.class);
        openSessionInViewerFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic systemContextFilter = container
            .addFilter("systemContextFilter", SystemContextFilter.class);
        systemContextFilter.addMappingForUrlPatterns(null, true, "/*");

        ServletRegistration.Dynamic initServlet = container
            .addServlet("initServlet", InitServlet.class);
        initServlet.setLoadOnStartup(2);

    }

}

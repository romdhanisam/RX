package com.github.rx.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(1)
public class ApplicationInitializer implements WebApplicationInitializer {

    private static final Log LOG = LogFactory.getLog(ApplicationInitializer.class);

    public void onStartup(ServletContext servletContext) throws ServletException {
        LOG.info("ApplicationInitializer : onStartup");
        final AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        servletContext.setInitParameter("contextConfigLocation", "com.github.rx.*");

        // lazily initialize a collection of role using
        // OpenEntityManagerInViewFilter
        final FilterRegistration.Dynamic entityManagerInViewFilter = servletContext.addFilter("entityManagerInViewFilter",
                org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class);
        entityManagerInViewFilter.setInitParameter("entityManagerFactoryBeanName", "entityManagerFactory");
        entityManagerInViewFilter.addMappingForUrlPatterns(null, true, "/*");
    }

}


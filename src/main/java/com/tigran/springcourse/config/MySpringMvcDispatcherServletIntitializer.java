package com.tigran.springcourse.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;

import static org.springframework.http.HttpHeaders.LOCATION;

public class MySpringMvcDispatcherServletIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
        ServletRegistration.Dynamic dispatcher = (ServletRegistration.Dynamic) aServletContext.getServletRegistration("dispatcher");
        if (dispatcher != null) {
            String uploadTempDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads"; // Change to a valid directory
            File uploadDir = new File(uploadTempDir);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            MultipartConfigElement multipartConfig = new MultipartConfigElement(
                    "C:/Users/benja/IdeaProjects/SpringLesson/uploads",  // Directory for temp storage
                    10 * 1024 * 1024, // Max file size (10MB)
                    20 * 1024 * 1024, // Max request size (20MB)
                    0  // Threshold size after which files will be written to disk
            );
            dispatcher.setMultipartConfig(multipartConfig);
        }
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                "/tmp", 10485760, 20971520, 0
        );
        registration.setMultipartConfig(multipartConfigElement);
    }
}

package ru.alishev.springcourse.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
  AbstractAnnotationConfigDispatcherServletInitializer -
  - реализует интерфейс WebApplicationInitializer.
  именно поэтому данный класс мы можем использовать как замену web.xml

 * */

public class mySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
        return new String[] {"/"};
    }
}

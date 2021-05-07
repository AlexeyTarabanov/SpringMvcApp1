package ru.alishev.springcourse.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
  AbstractAnnotationConfigDispatcherServletInitializer -
  - реализует интерфейс WebApplicationInitializer.
  именно поэтому данный класс мы можем использовать как замену web.xml

 * */

public class mySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // данный метод использовать не будем, поэтому возвращаем null
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // здесь мы должны вернуть конфигурационный класс (SpringConfig)
    // так же как и в файле web.xml мы подставляли путь до applicationContextMVC.xml

    /**
     * <!-- в этом файле мы будем конфигурировтаь spring mvc приложение -->
     * <param-value>/WEB-INF/applicationContextMVC.xml</param-value>
     */
    // так же и в java-конфигурации мы должны подставить класс SpringConfig
    // теперь наш java-класс, который исполняет роль web.xml знает где находится конфигурация
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // здесь возвращаем "/" - это эквивалентно тому же, что и в файле web.xml
    /**
     <servlet-name>dispatcher</servlet-name>
     <!-- и говорим, что любой url ("/"), который набирает пользователь в браузере
     должен перенаправляться на DispatcherServlet.
     Когда запрос будет перенаправлен сервером от пользователя на наш DispatcherServlet-
     - это будет означать, что этот запрос попал в наше spring mvc приложение -->
     <url-pattern>/</url-pattern>
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}

package ru.alishev.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 Этот класс будет похож на то, что мы делали в 13 уроке, то есть
 это будет конфигурационный java класс - для этого помечаем его как @Configuration

 После добавления всех аннотаций, нам осталось реализовать 3 бина, которые отвечали за
 конфигурацию thymeleaf
 */

@Configuration
// здесь передаем тот пакет, где лежат наши компоненты, где лежит наш контроллер
@ComponentScan("ru.alishev.springcourse")
// так как наше приложение поддерживае web функции
// эта аннотация равноценна тэгу mvc:annotation-driven
@EnableWebMvc
// реализовали интерфейс WebMvcConfigurer и вместе с ним метод configureViewResolvers()
// данный интерфейс реализуется в том случае, когда мы хотим под себя настроить spring_MVC
// в данном случае, мы вместо стандартного шаблонизатора, хотим использовать шаблонизатор thymeleaf
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    // так же с помощью аннотации @Autowired внедряем applicationContext
    // applicationContext будет внедрен самим Spring-ом за нас
    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // applicationContext мы используем в бине templateResolver, чтобы настроить thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        // здесь мы как и в xml-конфигурации создаем папку, где будут лежать наши представления
        templateResolver.setPrefix("/WEB-INF/views/");
        // задаем расширение у этих представлений
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    // здесь так же производим конфигурацию наших представлений
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // пердаем Spring-у информацию, о том что мы будем использовать шаблонизатор thymeleaf
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}

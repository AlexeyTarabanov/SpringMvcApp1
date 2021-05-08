package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 @Controller - данная аннотация помечает класс как контроллер
 (и будет отсканирована с помощью @ComponentScan)

 @RequestMapping - используется для мапинга (связывания) с URL
 для всего класса или для конкретного метода обработчика
 */

@Controller
@RequestMapping("/first")
public class FirstController {

    // в данном методе мы будем приниать get-запросы
    // метод будет возвращать страницу представления
    @GetMapping("/hello")
    public String helloPage() {
        // представления будут лежать в отдельной папке first,
        // которая относится к контроллеру
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
 }

package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 @Controller - данная аннотация помечает класс как контроллер
 (и будет отсканирована с помощью @ComponentScan)

 @RequestMapping - используется для мапинга (связывания) с URL
 для всего класса или для конкретного метода обработчика
 */

@Controller
@RequestMapping("/first")
public class FirstController {

    /** в данном методе мы будем приниать get-запросы.
     *  метод будет возвращать страницу представления.
     *  представления будут лежать в отдельной папке first, которая относится к контроллеру */
    // для того, чтобы принимать параметры из url в этом методе
    // мы должны внедрить объект HttpServletRequest
    // и в этом объекте будут содержаться все сведения о поступающем http запросе пользователя
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {
        // из объекта request мы можем получить параметры запроса
        // ** для примера скажем, что в url пользователь должен будет передавать 2 параметра (имя и фамилию)
        // получаем данные параметры по ключу
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    // теперь применим аннотацию @RequestParam для того, чтобы сделать тоже самое
    // здесь мы используем большой объект, который в себе содержит все сведения об http - запросе поступающем
    // из него мы берем только параметры
    // используем аннотацию @RequestParam
    // ей мы передаем ключ "name" и значение по этому ключу name
    // будет положено в параметр "name"
    // тоже саимое делаем для фамилии
    public String goodByePage(@RequestParam (value = "name", required = false) String name,
                              @RequestParam (value = "surname", required = false) String surname) {

        System.out.println("Goodbye, " + name + " " + surname + "!");

        return "first/goodbye";
    }
 }

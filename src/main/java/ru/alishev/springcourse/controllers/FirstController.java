package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Controller - данная аннотация помечает класс как контроллер
 * (и будет отсканирована с помощью @ComponentScan)
 * @RequestMapping - используется для мапинга (связывания) с URL
 * для всего класса или для конкретного метода обработчика
 */

@Controller
@RequestMapping("/first")
public class FirstController {

    /**
     * в данном методе мы будем приниать get-запросы.
     * метод будет возвращать страницу представления.
     * представления будут лежать в отдельной папке first, которая относится к контроллеру
     */

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

        // c помощью метода кладем в модель пару ключ=значение
        // здесь мы должны выбрать ключ, ключ - это строка и строка может быть любой
        // в данном случаем выберм ключ "message"
        // и здесь мы должны положить значение
        // значение это строка: "Hello, " + name + " " + surname
        model.addAttribute("message", "Hello, " + name + " " + surname);

        // System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a") int a,
                             @RequestParam(value = "b") int b,
                             @RequestParam (value = "action") String action,
                             Model model) {

        double result;

        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "division":
                result = a / (double) b; // 1 / 2 = 0.5
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);

        return "first/calculator";
    }
}

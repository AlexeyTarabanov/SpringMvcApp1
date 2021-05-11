package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;

/**

 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    // Spring сам ищет подходящий bean
    // и автоматически внедряет его
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    // возвращает список из людей
    // @GetMapping будет пустой, потому что адрес этого метода будет просто "/people"
    // набрав "/people" и сделав GET запрос мы попадем в этот метод
    @GetMapping
    public String index(Model model) {
        // получим всех людей из DAO и передадим на отображение в представление
        // model.addAttribute
        // назовем нашу переменную people
        // дальше - обратимся к DAO и вызовем метод index()
        model.addAttribute("people", personDAO.index());
        // теперь под ключом "people" у нас будет лежать список из людей
        // вернем шаблон, который будет отображать список из людей
        return "people/index";
    }

    @GetMapping("/{id}")
    // в этом аргументе id - будет лежать то целое число, которое передается в адресе запроса к этому методу "/{id}"
    public String show(@PathVariable("id") int id, Model model)  {
        // получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        // возвращаем название шаблона, где будет показываться человек
        return "people/show";
    }


}

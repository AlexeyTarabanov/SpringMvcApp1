package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 Этот класс будет "общаться" со списком.
 - будет извлекать людей из списка
 - будет находить конкретного человека по id из списка
 - добавлять человека в список
 - обновлять чеоловека из списка
 - удалять человека из списка

 *** Список - условная база данных
 */

@Component
public class PersonDAO {

    // id
    private static int PEOPLE_COUNT;
    // база данных людей
    private List<Person> people;

    // добавим людей в список
    {
        people= new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Katy"));
    }

    // будет возвращать список из людей
    public List<Person> index() {
        // возвращаем спиок
        // в будущем, с помощью Thymeleaf отобразим этот список в браузере
        return people;
    }

    // будет находить конкретного человека по id из списка
    public Person show(int id) {
        return people.stream()
                // отфильтруем человека по его id
                .filter(person -> person.getId() == id)
                // найдем этого человека (если он есть)
                .findAny()
                // если нет вернем null
                .orElse(null);
    }
}

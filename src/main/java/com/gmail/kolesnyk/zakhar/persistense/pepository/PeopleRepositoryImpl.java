package com.gmail.kolesnyk.zakhar.persistense.pepository;

import com.gmail.kolesnyk.zakhar.persistense.entity.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleRepositoryImpl implements PeopleRepository {

    private List<People> db = new ArrayList<People>() {{
        add(People.builder().id("1").firstname("Иван1").lastname("Иванов1").patronymic("Иванович1").build());
        add(People.builder().id("2").firstname("Иван2").lastname("Иванов2").patronymic("Иванович2").build());
        add(People.builder().id("3").firstname("Иван3").lastname("Иванов3").patronymic("Иванович3").build());
        add(People.builder().id("4").firstname("Иван4").lastname("Иванов4").patronymic("Иванович4").build());
        add(People.builder().id("5").firstname("Иван5").lastname("Иванов5").patronymic("Иванович5").build());
    }};

    @Override
    public List<People> list() {
        return db;
    }

    @Override
    public People byId(String id) {
        for (People people : db) {
            if (people.getId().equals(id)) {
                return people;
            }
        }
        return null;
    }

    @Override
    public People byFirstName(String firstname) {
        for (People people : db) {
            if (people.getFirstname().equals(firstname)) {
                return people;
            }
        }
        return null;
    }

    @Override
    public People save(People people) {
        people.setId(String.valueOf(System.currentTimeMillis()));
        db.add(people);
        return people;
    }

    @Override
    public People delete(String id) {
        for (People people : db) {
            if (people.getId().equals(id)) {
                db.remove(people);
                return people;
            }
        }
        return null;
    }
}

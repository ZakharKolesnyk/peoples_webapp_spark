package com.gmail.kolesnyk.zakhar.persistense.pepository;

import com.gmail.kolesnyk.zakhar.persistense.entity.People;

import java.util.List;

public interface PeopleRepository {
    List<People> list();

    People byId(String id);

    People byFirstName(String firstname);

    People save(People people);

    People delete(String id);
}

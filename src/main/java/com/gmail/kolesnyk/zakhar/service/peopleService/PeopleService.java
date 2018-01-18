package com.gmail.kolesnyk.zakhar.service.peopleService;

public interface PeopleService {

    String jsonById(String id);

    String jsonList();

    String save(String body);

    String deleteById(String params);
}

package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.service.peopleService.PeopleService;

import static spark.Spark.get;

public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
        init();
    }

    private void init() {
        get("/:id", (request, response) -> "s");
    }
}

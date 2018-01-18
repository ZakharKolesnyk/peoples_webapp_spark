package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.service.peopleService.PeopleService;

import static spark.Spark.*;

public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
        init();
    }

    private void init() {
        path("/people", () -> {
            get("/:id", (req, res) -> peopleService.jsonById(req.params(":id")));
            get("", (req, res) -> peopleService.jsonList());
            post("", (req, res) -> peopleService.save(req.body()));
            delete("/:id", (req, res) -> peopleService.deleteById(req.params(":id")));
        });
    }
}

package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.service.peopleService.PeopleService;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
        init();
    }

    private void init() {
        get("/people/:id", (req, res) -> peopleService.jsonById(req.params(":id")));
        get("/people", (req, res) -> peopleService.jsonList());
        post("/people", (req, res) -> peopleService.save(req.body()));
        delete("/people/:id", (req, res) -> peopleService.deleteById(req.params(":id")));
    }
}

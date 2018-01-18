package com.gmail.kolesnyk.zakhar.service.peopleService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kolesnyk.zakhar.persistense.pepository.PeopleRepository;

public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    private final ObjectMapper objectMapper;

    public PeopleServiceImpl(PeopleRepository peopleRepository, ObjectMapper objectMapper) {
        this.peopleRepository = peopleRepository;
        this.objectMapper = objectMapper;
    }
}

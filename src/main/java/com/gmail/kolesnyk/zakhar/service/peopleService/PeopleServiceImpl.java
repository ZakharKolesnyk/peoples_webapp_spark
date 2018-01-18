package com.gmail.kolesnyk.zakhar.service.peopleService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kolesnyk.zakhar.persistense.entity.People;
import com.gmail.kolesnyk.zakhar.persistense.pepository.peopleRepository.PeopleRepository;

import java.io.IOException;

public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    private final ObjectMapper objectMapper;

    public PeopleServiceImpl(PeopleRepository peopleRepository, ObjectMapper objectMapper) {
        this.peopleRepository = peopleRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public String jsonById(String id) {
        try {
            return objectMapper.writeValueAsString(peopleRepository.byId(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String jsonList() {
        try {
            return objectMapper.writeValueAsString(peopleRepository.list());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String save(String body) {
        try {
            People people = objectMapper.readValue(body, People.class);
            peopleRepository.save(people);
            people = peopleRepository.byId(people.getId());
            return objectMapper.writeValueAsString(people);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String deleteById(String id) {
        try {
            return objectMapper.writeValueAsString(peopleRepository.remove(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}

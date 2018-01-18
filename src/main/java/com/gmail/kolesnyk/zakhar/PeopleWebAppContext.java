package com.gmail.kolesnyk.zakhar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kolesnyk.zakhar.controller.PeopleController;
import com.gmail.kolesnyk.zakhar.persistense.pepository.peopleRepository.PeopleRepository;
import com.gmail.kolesnyk.zakhar.persistense.pepository.peopleRepository.PeopleRepositoryImpl;
import com.gmail.kolesnyk.zakhar.service.annotation.Bean;
import com.gmail.kolesnyk.zakhar.service.peopleService.PeopleService;
import com.gmail.kolesnyk.zakhar.service.peopleService.PeopleServiceImpl;

import java.lang.reflect.Field;
import java.util.Objects;

import static spark.Spark.port;

public class PeopleWebAppContext {

    static {
        //spark configuration
        port(8080);
    }

    @Bean
    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Bean
    private final static PeopleRepository peopleRepository = new PeopleRepositoryImpl();
    @Bean
    private final static PeopleService peopleService = new PeopleServiceImpl(peopleRepository, objectMapper);
    @Bean
    private final static PeopleController peopleController = new PeopleController(peopleService);

    public static <T> T getBean(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            throw new RuntimeException("bean cannot be null");
        }
        for (Field field : PeopleWebAppContext.class.getDeclaredFields()) {
            if (field.getType().equals(clazz) && field.isAnnotationPresent(Bean.class)) {
                field.setAccessible(true);
                try {
                    return (T) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("bean not found: " + clazz.getSimpleName());
                }
            }
        }
        throw new RuntimeException("bean not found: " + clazz.getSimpleName());
    }


    public static void main(String[] args) {
        //see static block
    }
}

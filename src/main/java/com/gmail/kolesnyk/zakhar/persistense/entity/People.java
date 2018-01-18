package com.gmail.kolesnyk.zakhar.persistense.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People implements Serializable {

    private String id;
    private String firstname;
    private String lastname;
    private String patronymic;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"," +
                "\"firstname\":" + "\"" + firstname + "\"," +
                "\"lastname\":" + "\"" + lastname + "\"," +
                "\"patronymic\":" + "\"" + patronymic + "\"" +
                "}";
    }
}

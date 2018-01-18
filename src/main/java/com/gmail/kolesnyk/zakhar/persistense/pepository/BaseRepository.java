package com.gmail.kolesnyk.zakhar.persistense.pepository;

import java.util.List;

public interface BaseRepository<T, I> {

    T byId(I id);

    void save(T object);

    T update(T object);

    T remove(I id);

    List<T> list();
}

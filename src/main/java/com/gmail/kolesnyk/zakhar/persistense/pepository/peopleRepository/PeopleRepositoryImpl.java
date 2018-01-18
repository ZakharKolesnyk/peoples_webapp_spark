package com.gmail.kolesnyk.zakhar.persistense.pepository.peopleRepository;

import com.gmail.kolesnyk.zakhar.persistense.entity.People;
import com.gmail.kolesnyk.zakhar.persistense.pepository.MongoRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

public class PeopleRepositoryImpl extends MongoRepository<People, String> implements PeopleRepository {

    @Override
    public People update(People people) {
        getMongoCollection().updateOne(eq("_id", people.getId()), convert(people));
        return getMongoCollection().find(eq("_id", people.getId())).first();
    }

    @Override
    public List<People> list() {
        return getMongoCollection().find().into(new ArrayList<>());
    }

    private Bson convert(People people) {
        Set<Method> getters = ReflectionUtils.getAllMethods(People.class, ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));
        Document document = new Document();
        for (Method getter : getters) {
            try {
                document.append(getter.getName(), getter.invoke(people));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return document;
    }
}

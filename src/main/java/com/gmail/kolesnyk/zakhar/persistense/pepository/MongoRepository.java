package com.gmail.kolesnyk.zakhar.persistense.pepository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public abstract class MongoRepository<T extends Serializable, I extends Serializable> implements BaseRepository<T, I> {

    private final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private final MongoClient mongoClient = new MongoClient("localhost",
            MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

    private final MongoDatabase mongoDatabase = mongoClient.getDatabase("people_webapp_spark");

    private final Class<T> entityClass;

    private final MongoCollection<T> mongoCollection;

    @SuppressWarnings("unchecked")
    public MongoRepository() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.mongoCollection = mongoDatabase.getCollection("peoples", entityClass);
    }

    @Override
    public T byId(I id) {
        return mongoCollection.find(eq("_id", id)).first();
    }

    @Override
    public void save(T object) {
        mongoCollection.insertOne(object);
    }

    @Override
    public T remove(I id) {
        return mongoCollection.findOneAndDelete(eq("_id", id));
    }

    @Override
    public List<T> list() {
        return null;
    }

    public MongoCollection<T> getMongoCollection() {
        return mongoCollection;
    }
}

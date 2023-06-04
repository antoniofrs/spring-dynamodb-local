package it.antoniofrisenda.springdynamodblocal.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public class DynamoDbCrudRepository<T, U> {

    @SuppressWarnings("unchecked")
    private final Class<T> classType = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public T save(T t){
        dynamoDBMapper.save(t);
        return t;
    }

    public Optional<T> findById(U u){
        return Optional.of(dynamoDBMapper.load(classType, u));
    }

    public T delete(U u){

        T toDelete = findById(u).orElseThrow(() -> new RuntimeException("Cannot delete"));

        dynamoDBMapper.delete(toDelete);
        return toDelete;
    }

}

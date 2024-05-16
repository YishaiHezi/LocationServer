package com.example.demo.model;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Spring data will look for this interface and add implementation to it,
 * then it will instantiate it, and it will deliver calls that the application do
 * to the DynamoDB.
 */
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Returns all the Users whose name starts with prefix.
     */
    List<User> findByNameStartsWith(String prefix);

}

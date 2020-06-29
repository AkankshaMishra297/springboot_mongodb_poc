package com.neo.mongoDemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.neo.mongoDemo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,Integer>{

}

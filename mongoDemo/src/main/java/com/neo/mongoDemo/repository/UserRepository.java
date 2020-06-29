package com.neo.mongoDemo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.neo.mongoDemo.model.UserDetails;

@Repository
public interface UserRepository extends MongoRepository<UserDetails,Integer>{
	
	UserDetails findById(ObjectId id);

}

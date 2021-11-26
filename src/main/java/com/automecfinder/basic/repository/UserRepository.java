package com.automecfinder.basic.repository;

import com.automecfinder.basic.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    User findByActivationToken(String activationToken);
}

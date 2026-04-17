package com.fp.midterm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.fp.midterm.model.Checking;

@Repository
public interface CheckingRepository extends ReactiveMongoRepository<Checking, String> {
}

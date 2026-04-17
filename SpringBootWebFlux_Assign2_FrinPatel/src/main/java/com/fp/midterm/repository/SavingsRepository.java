package com.fp.midterm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.fp.midterm.model.Savings;

@Repository
public interface SavingsRepository extends ReactiveMongoRepository<Savings, String> {
}

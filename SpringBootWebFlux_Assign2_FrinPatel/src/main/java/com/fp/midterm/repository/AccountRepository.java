package com.fp.midterm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.fp.midterm.model.Account;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}

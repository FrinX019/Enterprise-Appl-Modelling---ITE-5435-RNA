package com.fp.week4;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends ReactiveMongoRepository<Publisher, String> {
}

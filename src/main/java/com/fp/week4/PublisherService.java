package com.fp.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepo;

    // Get all publishers
    public Flux<Publisher> getAll() {
        return publisherRepo.findAll().switchIfEmpty(Flux.empty());
    }

    // Get publisher by ID
    public Mono<Publisher> getById(final String id) {
        return publisherRepo.findById(id);
    }

    // Update publisher
    public Mono<Publisher> update(final String id, final Publisher publisher) {
        return publisherRepo.findById(id)
                .flatMap(existingPublisher -> {
                    publisher.setId(id);
                    return publisherRepo.save(publisher);
                });
    }

    // Save new publisher
    public Mono<Publisher> save(final Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    // Delete publisher
    public Mono<Void> delete(final String id) {
        return publisherRepo.deleteById(id);
    }
}

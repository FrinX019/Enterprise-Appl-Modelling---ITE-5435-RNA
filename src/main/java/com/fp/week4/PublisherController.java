package com.fp.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("publisher")
@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // Get all publishers
    @GetMapping
    public Flux<Publisher> getAll() {
        System.out.println("Getting all publishers");
        return publisherService.getAll();
    }

    // Get publisher by ID
    @GetMapping("{id}")
    public Mono<Publisher> getById(@PathVariable("id") final String id) {
        System.out.println("Getting publisher by ID: " + id);
        return publisherService.getById(id);
    }

    // Create new publisher
    @PostMapping
    public Mono<Publisher> save(@RequestBody final Publisher publisher) {
        System.out.println("Creating new publisher: " + publisher.getName());
        return publisherService.save(publisher);
    }

    // Update publisher
    @PutMapping("{id}")
    public Mono<Publisher> update(@PathVariable("id") final String id, @RequestBody final Publisher publisher) {
        System.out.println("Updating publisher with ID: " + id);
        return publisherService.update(id, publisher);
    }

    // Delete publisher
    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable("id") final String id) {
        System.out.println("Deleting publisher with ID: " + id);
        return publisherService.delete(id);
    }
}

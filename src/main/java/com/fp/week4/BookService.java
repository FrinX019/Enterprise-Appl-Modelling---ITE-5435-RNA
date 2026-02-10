package com.fp.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepo;

  public Flux<Book> getAll() {

    return bookRepo.findAll().switchIfEmpty(Flux.empty());

  }

  public Mono<Book> getById(final String id) {

    return bookRepo.findById(id);

  }

  public Mono update(final String id, final Book book) {

    return bookRepo.save(book);

  }

  public Mono save(final Book book) {

    return bookRepo.save(book);

  }

  // Delete book
  public Mono<Void> delete(final String id) {

    return bookRepo.deleteById(id);

  }

}

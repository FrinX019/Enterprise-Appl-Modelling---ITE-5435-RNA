package com.fp.week4;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("book")
// @AllArgsConstructor
@RestController
public class BookController {

      @Autowired
      private BookService bookService;

      @GetMapping
      public Flux<Book> getAll() {
            System.out.println("All the book information");
            return bookService.getAll();

      }

      @GetMapping("{id}")

      public Mono<Book> getById(@PathVariable("id") final String id) {
            System.out.println("One book information based for the given ID");
            return bookService.getById(id);

      }

      @PutMapping("{id}")

      public Mono updateById(@PathVariable("id") final String id, @RequestBody final Book book) {
            System.out.println("Updating a book Info");
            return bookService.update(id, book);

      }

      @PostMapping
      public Mono save(@RequestBody final Book book) {
            System.out.println("Added book Info " + book.getId() + " - " + book.getTitle()
                        + " - " + book.getAuthor() + " - " + book.getPrice() + " - " + book.isAvailable());
            return bookService.save(book);

      }

      // Delete book
      @DeleteMapping("{id}")
      public Mono<Void> delete(@PathVariable("id") final String id) {
            System.out.println("Deleting book with ID: " + id);
            return bookService.delete(id);
      }

}

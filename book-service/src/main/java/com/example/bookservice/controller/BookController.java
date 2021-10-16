package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import feign.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private BookService bookService;
    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Operation(summary = "Find a specific book by your Id")
    @GetMapping(value = "/{id}/{currency}")
    @Retry(name = "default", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//    @RateLimiter(name = "default")
    public ResponseEntity<Book> findBook(@PathVariable("id") Long id,
                                         @PathVariable("currency") String currency
    ) {
        Book book = bookService.findBook(id, currency);
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<String> fallbackMethod(Exception exception) {
        logger.info("Falback method in execution {}", exception.getMessage());
        return ResponseEntity.ok("Falback method active " + exception.getMessage());
    }
}

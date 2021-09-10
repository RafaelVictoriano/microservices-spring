package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import com.example.bookservice.proxy.CambioProxy;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CambioProxy proxy;
    @Autowired
    private Environment environment;


    public Book findBook(Long id, String currency) {
        var book = repository.getById(id);

        Optional.ofNullable(book)
                .orElseThrow(() -> new RuntimeException("Book not Found"));

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port + " FEIGN");
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

}

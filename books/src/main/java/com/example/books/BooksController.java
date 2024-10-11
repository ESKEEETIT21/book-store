package com.example.books;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BooksController {

    private final AtomicLong counter = new AtomicLong();
    private ArrayList<Book> myBookAL = new ArrayList<>();


    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getBooks(HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        Object books;

        response.put("Request", request.getMethod() + " /books");
        response.put("Respone", null);
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value());
        response.put("payload", myBookAL);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestParam String author, @RequestParam String title, HttpServletRequest request) {
        Book book = new Book(counter.incrementAndGet(), author, title);
        myBookAL.add(book);
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("Request", request.getMethod() + " /books");
        response.put("Respone", null);
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value());
        response.put("payload", myBookAL);

         return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
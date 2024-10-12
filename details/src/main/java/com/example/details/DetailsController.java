package com.example.details;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DetailsController {

    private final AtomicLong counter = new AtomicLong();
    private Map<Long, Book> myBookDetailsMap = new HashMap<>();


    @GetMapping("/details/{bookId}")
    public ResponseEntity<Map<String, Object>> getDetails(@PathVariable("bookId") long bookId, HttpServletRequest request) {
        long id = counter.incrementAndGet();
        Book book = new Book(id, "Martin Kleppmann", 2017, "hardcover", "O'Reilly Media", "english", "978-1449373320", 590, "Designing Data-Intensive Applications","Data is at the center of many challenges in system design today. Difficult issues need to be figured out, such as scalability, consistency, reliability, efficiency, and maintainability.");
        myBookDetailsMap.put(id, book);

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("Request", request.getMethod() + "/details/{bookId}");
        response.put("Respone", null);
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value());
        if (myBookDetailsMap.containsKey(bookId)) {
            response.put("Payload", myBookDetailsMap.get(bookId));
        } else {
            response.put("Error", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
/*
    @GetMapping("/details/add")
    public ResponseEntity<Map<String, Object>> addBook(
            @RequestParam String author,
            @RequestParam String title,
            @RequestParam int year,
            @RequestParam String type,
            @RequestParam String publisher,
            @RequestParam String language,
            @RequestParam String isbn13,
            @RequestParam int pages,
            @RequestParam String description,
            HttpServletRequest request) {

        Book book = new Book(
                counter.incrementAndGet(),
                author,
                year,
                type,
                publisher,
                language,
                isbn13,
                pages,
                title,
                description
        );

        myBookDetailsMap.put(book.id(), book);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Request", request.getMethod() + " /books");
        response.put("Response", null);
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value());
        response.put("payload", myBookDetailsMap);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 */
}
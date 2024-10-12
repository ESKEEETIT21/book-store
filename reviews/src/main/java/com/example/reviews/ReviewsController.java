package com.example.reviews;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReviewsController {
    private final AtomicLong counter = new AtomicLong();
    Map<Long, Review> myReviewMap = new HashMap<>();

    @GetMapping("/reviews/{bookId}")
    public ResponseEntity<Map<String, Object>> getReview(@PathVariable("bookId") long bookID, HttpServletRequest request) {
        long id = counter.incrementAndGet();
        Review review = new Review("John Doe", "Best book ever!");
        myReviewMap.put(id, review);

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("Request", request.getMethod() + "/details/{bookId}");
        response.put("Response", null);
        response.put("Content-Type", "application/json");
        response.put("Status Code", HttpStatus.OK.value());
        if (myReviewMap.containsKey(bookID)) {
            response.put("Payload", myReviewMap.get(bookID));
        } else {
            response.put("Error", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
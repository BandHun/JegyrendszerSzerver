package hu.bandi.szerver.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class PingTestController {
    @GetMapping("/ping")
    public ResponseEntity<String> getCommentById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>("Pinged: " + id, HttpStatus.OK);
    }
}

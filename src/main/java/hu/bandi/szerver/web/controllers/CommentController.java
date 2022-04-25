package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.special.serverfunctions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @Autowired
    private UserRepository userRepository;


    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody final String name,
                                              @RequestBody final List<MultipartFile> documents) {
        //TODO: fájlok mentése és dokumentumba szervezése
        return new ResponseEntity<>(
                commentService.addComment(name, new ArrayList<>(), CurrentUser.getUser(userRepository)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") final Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

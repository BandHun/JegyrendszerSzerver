package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    TicketService ticketService;


    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add/{ticketid}")
    public ResponseEntity<Comment> addComment(@PathVariable("ticketid") final Long ticketid,@RequestBody final String name) {
        Comment newComment = commentService.addComment(name, userService.getCurrentUser());
        ticketService.addComment(ticketid,newComment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") final Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

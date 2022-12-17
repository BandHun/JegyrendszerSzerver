package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;

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
    public ResponseEntity<Comment> addComment(@PathVariable("ticketid") final Long ticketid,
                                              @RequestBody final Comment comment) {
        final Comment newComment = commentService.addComment(comment);
        ticketService.addComment(ticketid, newComment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody final Comment comment) {
        return new ResponseEntity<>(commentService.modifyComment(comment.getId(), comment.getCommentMessage()),
                                    HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") final Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

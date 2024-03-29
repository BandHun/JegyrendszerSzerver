package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment addComment(Comment comment);

    Comment modifyComment(Long id, String message);

    void deleteComment(Long id);

    Comment findById(Long id);


    Optional<Comment> getCommentById(Long id);

    List<Comment> getCommentsByIds(List<Long> ids);
}

package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.models.Document;
import hu.bandi.szerver.models.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void addComment(String message, List<Document> documents, User user);

    void modifyComment(Long id, String message, List<Document> documents);

    void deleteComment(Long id);

    Optional<Comment> getCommentById(Long id);

    List<Comment> getCommentsByIds(List<Long> ids);
}

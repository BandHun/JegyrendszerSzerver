package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.repositories.CommentRepository;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.services.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    DocumentService documentService;


    @Override
    public Comment addComment(final Comment comment) {
        comment.setCreator(CurrentUserService.getCurrentUser());
        return commentRepository.save(comment);
    }

    @Override
    public Comment modifyComment(final Long id, final String message) {
        final Comment toEdit = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toEdit.setCommentMessage(message);
        return commentRepository.save(toEdit);
    }

    @Override
    public void deleteComment(final Long id) {
        final Comment toEdit = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toEdit.setValid(false);
        commentRepository.save(toEdit);
    }

    @Override
    public Optional<Comment> getCommentById(final Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getCommentsByIds(final List<Long> ids) {
        return commentRepository.findAllById(ids);
    }

    @Override
    public Comment findById(final Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
    }
    
}

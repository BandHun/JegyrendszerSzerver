package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Comment;
import hu.bandi.szerver.models.Document;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.CommentRepository;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.services.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    DocumentService documentService;


    @Override
    public Comment addComment(final String message, final User user) {
        return commentRepository.save(new Comment(message, user));
    }

    @Override
    public void modifyComment(final Long id, final String message) {
        final Comment toEdit = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toEdit.setCommentMessage(message);
        commentRepository.save(toEdit);
    }

    @Override
    public void deleteComment(final Long id) {
        final Comment toEdit = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toEdit.setValid(false);
        documentService.deleteDocuments(
                toEdit.getDocuments().stream().map(document -> document.getId()).collect(Collectors.toList()));
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

    @Override
    public void addDocument(Document document, Long commentId) {
Comment toEdit = findById(commentId);
toEdit.addDocument(document);
commentRepository.save(toEdit);
    }

    @Override
    public void removeDocument(Document document) {
        List<Comment> all = commentRepository.findAll();
        for(Comment c:all){
            c.aremoveDocument(document);
            commentRepository.save(c);
        }
    }
}

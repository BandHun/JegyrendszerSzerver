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
    public void addComment(final String message, final List<Document> documents, final User user) {
        commentRepository.save(new Comment(message, documents, user));
    }

    @Override
    public void modifyComment(final Long id, final String message, final List<Document> validDocuments) {
        final Comment toEdit = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toEdit.setCommentMessage(message);
        final List<Document> commentDocuments = toEdit.getDocuments();
        final List<Long> toInvalidate = commentDocuments.stream().map(
                document -> validDocuments.contains(document) ? null : document.getId()).collect(Collectors.toList());
        documentService.deleteDocuments(toInvalidate);

        final List<Document> newDocuments = validDocuments.stream().map(
                document -> commentDocuments.contains(document) ? null : document).collect(Collectors.toList());
        documentService.addDocuments(newDocuments);

        toEdit.setDocuments(newDocuments);
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
}

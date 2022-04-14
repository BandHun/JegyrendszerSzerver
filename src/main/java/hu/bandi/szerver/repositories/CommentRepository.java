package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

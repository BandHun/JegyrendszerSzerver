package hu.bandi.szerver.repositories;


import hu.bandi.szerver.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

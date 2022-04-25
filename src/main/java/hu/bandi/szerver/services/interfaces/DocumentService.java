package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    Document addDocument(final MultipartFile file);

    void addDocuments(List<Document> documents);

    void deleteDocument(Long id);

    void deleteDocuments(List<Long> ids);

}

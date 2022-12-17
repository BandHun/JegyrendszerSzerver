package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    Document addDocument(final MultipartFile file, final String filename) throws IOException;

    void deleteDocument(Long id);

    Document getById(Long id);

    void deleteDocuments(List<Long> ids);

}

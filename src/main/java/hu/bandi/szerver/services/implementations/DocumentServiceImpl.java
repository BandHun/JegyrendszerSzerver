package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Document;
import hu.bandi.szerver.repositories.DocumentRepository;
import hu.bandi.szerver.services.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    public final static String folderLocation = System.getProperty("user.dir") + "/uploads";

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Document addDocument(final MultipartFile file, final String filename) throws IOException {
        return documentRepository.save(new Document(file.getBytes(), filename));
    }

    @Override
    public void deleteDocument(final Long id) {
        final Document toDelete = getById(id);
        toDelete.setValid(false);
        documentRepository.save(toDelete);
    }

    @Override
    public Document getById(final Long id) {
        return documentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
    }

    @Override
    public void deleteDocuments(final List<Long> ids) {
        final List<Document> toDelete = documentRepository.findAllById(ids);
        for (final Document del : toDelete) {
            del.setValid(false);
            documentRepository.save(del);
        }
    }
}

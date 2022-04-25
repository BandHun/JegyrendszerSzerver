package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Document;
import hu.bandi.szerver.repositories.DocumentRepository;
import hu.bandi.szerver.services.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    public final static String folderLocation = System.getProperty("user.dir") + "/uploads";

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Document addDocument(final MultipartFile file) {
        final String fileLocation = UUID.randomUUID().toString();
        final Path fileStaticPath = Paths.get(folderLocation, fileLocation);
        try {
            Files.write(fileStaticPath, file.getBytes());
        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return documentRepository.save(new Document(fileLocation, file.getName()));
    }

    //TODO: ÁÁt kell gondolni a működését
    @Override
    public void addDocuments(final List<Document> documents) {
        throw new RuntimeException("TO DO");
    }

    @Override
    public void deleteDocument(final Long id) {
        final Document toDelete = documentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found by id:" + id + "."));
        toDelete.setValid(false);
        documentRepository.save(toDelete);
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

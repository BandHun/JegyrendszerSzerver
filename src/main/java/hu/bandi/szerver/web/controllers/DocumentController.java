package hu.bandi.szerver.web.controllers;


import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Document;
import hu.bandi.szerver.services.interfaces.CommentService;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.DocumentService;
import hu.bandi.szerver.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private final TicketService ticketService;

    @Autowired
    private final DocumentService documentService;

    private final CommentService commentService;

    public DocumentController(final TicketService ticketService, final DocumentService documentService, final CommentService commentService) {
        this.ticketService = ticketService;
        this.commentService=commentService;
        this.documentService = documentService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Document> addToTicket(@PathVariable("id") Long documentId) {
        return new ResponseEntity<>(documentService.getById(documentId), HttpStatus.OK);
    }

    @GetMapping("/getbyticketid/{id}")
    public ResponseEntity<List<Document>> addToTicke(@PathVariable("id") Long documentId) {
        return new ResponseEntity<>(ticketService.findById(documentId).getDocuments(), HttpStatus.OK);
    }
    @PostMapping("/toticket/{id}")
    public ResponseEntity<Document> addToTicket(@PathVariable("id") Long ticketId, @RequestParam("file") MultipartFile file,@RequestParam("filename") String name) throws IOException {
        Document create = documentService.addDocument(file,name);
        ticketService.addDocument(create,ticketId);
        return new ResponseEntity<>(create, HttpStatus.OK);
    }

    @GetMapping("/getdata/{id}")
    public ResponseEntity<byte[]> getData(@PathVariable("id") Long id){
        return new ResponseEntity<>(documentService.getById(id).getData(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> addCompany(@PathVariable("id") final Long id) {
        Document todelete=documentService.getById(id);
        commentService.removeDocument(todelete);
        ticketService.deleteDocument(todelete);
        documentService.deleteDocument(todelete.getId());
        return new ResponseEntity<>( HttpStatus.OK);
    }

}

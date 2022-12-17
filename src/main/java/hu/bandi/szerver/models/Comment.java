package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String commentMessage;
    @OneToMany
    private List<Document> documents;
    @ManyToOne
    @JoinColumn(name = "creator_ID")
    private User creator;

    @CreationTimestamp
    private Date createDateTime;

    @UpdateTimestamp
    private Date updateDateTime;

    public Comment(final String commentMessage, final User creator) {
        this.commentMessage = commentMessage;
        this.creator = creator;
        isValid = true;
    }

    public void addDocument(final Document document) {
        this.documents.add(document);
    }

    public void aremoveDocument(final Document document) {
        this.documents.remove(document);
    }
}

package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



/*
 * Jegy
 *
 * Szerepe, hogy feladatokat és a róluk tudható összes információt tárolja. Név, létrehozó, felelős, létrehozés
 * dátuma, határidő, jelenlegi állapot, hozzá kapcsolódó dokumentumok és kommentek.
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    boolean isValid;

    private String name;
    @ManyToOne
    @JoinColumn(name = "author_ID")
    @NonNull
    private User author;
    @ManyToOne
    @JoinColumn(name = "assigned_ID")
    @Nullable
    private User assigned;

    private Date createdAt;
    private Date deadline;

    private int storyPoints;
    private int usedStroyPoints;

    private String description;

    private TicketStatus status;
    @OneToMany
    private List<Document> documents;
    @OneToMany
    private List<Comment> comments;

    public Ticket(final String name, final String description, @NonNull final User author, final Date deadline,
                  final int storyPoints, final List<Document> documents) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.deadline = deadline;
        this.storyPoints = storyPoints;
        this.documents = documents;
        isValid = true;
        createdAt = new java.sql.Date(System.currentTimeMillis());
        comments = new ArrayList<Comment>();
        status = TicketStatus.TODO;
    }

    public void addDocument(final Document document) {
        documents.add(document);
    }

    public void removeDocument(final Document document) {
        documents.remove(document);
    }
}

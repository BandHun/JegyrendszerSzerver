package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    boolean valid;

    private String title;
    @ManyToOne
    @JoinColumn(name = "author_ID")
    @NonNull
    private User author;
    @ManyToOne
    @JoinColumn(name = "assigned_ID")
    @Nullable
    private User assignee;

    @JoinColumn(updatable = false)
    @JsonIgnore
    @ManyToOne
    private Company company;

    private Date createdAt;

    private int storyPoints;
    private long usedStroyPoints;

    private String description;

    private TicketStatus status;

    @JsonIgnore
    @OneToMany
    private List<Document> documents;
    @OneToMany
    private List<Comment> comments;

    @ManyToOne
    private Project project;

    @JsonIgnore
    @ManyToOne
    private Sprint sprint;

    @JsonIgnore
    @ManyToOne
    private Teams teams;

    public Ticket(final String title, final String description, @NonNull final User author, final int storyPoints,
                  final Company company) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.storyPoints = storyPoints;
        this.company = company;
        project = null;
        valid = true;
        createdAt = new java.sql.Date(System.currentTimeMillis());
        comments = new ArrayList<Comment>();
        status = TicketStatus.TODO;
        teams = null;
        documents=new ArrayList<>();
    }

    /*public void addDocument(final Document document) {
        documents.add(document);
    }*/

    public void removeDocument(final Document document) {
        documents.remove(document);
    }

    public void addComment(final Comment comment) {
        comments.add(comment);
    }

    public void removeComment(final Comment comment) {
        comments.remove(comment);
    }
}

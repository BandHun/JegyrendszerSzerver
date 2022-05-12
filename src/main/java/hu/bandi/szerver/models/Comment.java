package hu.bandi.szerver.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;



/*
 * Komment
 *
 * Szerepe, hogy tároljon egy felhasználó által írt szöveget és a hozzá tartozó dokumentumokat.
 * Emellett tároljuk a létrehozási és utolsó módosítási időpontot is.
 * */

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
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Comment(final String commentMessage, final User creator) {
        this.commentMessage = commentMessage;
        this.creator = creator;
        isValid = true;
    }
}

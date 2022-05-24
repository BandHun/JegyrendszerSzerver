package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;



/*
 * Elkönyvelt óra
 *
 * Szerepe, hogy nyílvántartsa, mely felhasználó és mely jegyre mennyi időt könyvelt
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recordedhours")
public class HourRecords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;
    private long recordedhours;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public HourRecords(final User user, final Ticket ticket, final Date toDate, final long recordedhours) {
        this.user = user;
        this.recordedhours = recordedhours;
        this.ticket = ticket;
        this.toDate = toDate;
        isValid = true;
    }
}

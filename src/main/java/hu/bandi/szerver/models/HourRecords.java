package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



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

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;
    private long recordedhours;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}

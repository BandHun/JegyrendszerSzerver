package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



/*
 * Csapat Tábla
 *
 * Szerepe, hogy tárolja, a hozzá tartozó jegyeket.
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Table(name = "grouptables")
public class TeamsTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;

    @OneToOne
    private Teams teams;
    @OneToMany
    private List<Ticket> tickets;
}

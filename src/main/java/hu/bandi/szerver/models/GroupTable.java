package hu.bandi.szerver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class GroupTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;

    @OneToOne
    private Group group;
    @OneToMany
    private List<Ticket> tickets;
}

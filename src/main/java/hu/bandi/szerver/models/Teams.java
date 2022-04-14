package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



/*
 * Csapat
 *
 * Szerepe, hogy csapatokat definiáljon és tartsa nyílván, hogy mely céghez tartozik és mely felhasználók tartoznak
 * hozzá. Emellett tárolja a hozzá tartozó táblákat is;
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;
    @OneToMany
    private List<User> users = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    private TeamsTable teamsTable;
}

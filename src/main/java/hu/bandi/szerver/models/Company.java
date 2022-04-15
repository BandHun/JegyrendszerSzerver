package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



/*
 * Cég
 *
 * A cég tárolja a hozzá tartozó felhasználókat, csoportokat, projekteket
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<Teams> teams;
    @OneToMany
    private List<Project> projects;
}

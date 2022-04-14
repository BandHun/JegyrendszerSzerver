package hu.bandi.szerver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<Group> groups;
    @OneToMany
    private List<Project> projects;
}

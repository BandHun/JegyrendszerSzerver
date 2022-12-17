package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @JsonIgnore
    @OneToMany
    private List<User> users;
    @JsonIgnore
    @OneToMany
    private List<Teams> teams;
    @JsonIgnore
    @OneToMany
    private List<Project> projects;

    public Company(final String name) {
        this.name = name;
        isValid = true;
        users = new ArrayList<>();
        teams = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public void addUser(final User user) {
        System.out.println("USER ADD");
        System.out.println(user);
        System.out.println(users);
        users.add(user);
    }

    public void addTeams(final Teams teams) {
        this.teams.add(teams);
    }

    public void addProject(final Project project) {
        projects.add(project);
    }

    public void removeUser(final User user) {
        users.remove(user);
    }

    public void removeTeams(final Teams teams) {
        this.teams.remove(teams);
    }

    public void removeProject(final Project project) {
        projects.remove(project);
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name='" + name + '}';
    }
}

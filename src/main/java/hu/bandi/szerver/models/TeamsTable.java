package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

    @JsonIgnore
    @OneToOne
    private Teams teams;
    @OneToMany
    private List<Ticket> backlog;

    @OneToMany
    private List<Sprint> sprints;

    public TeamsTable(final String name, final Teams teams) {
        this.name = name;
        this.teams = teams;
        isValid = true;
        this.backlog = new ArrayList<>();
        this.sprints = new ArrayList<>();
    }

    public void addSprint(final Sprint sprint) {
        sprints.add(sprint);
    }

    public void removeSprint(final Sprint sprint) {
        sprints.remove(sprint);
    }


    public void addTicket(final Ticket ticket) {
        backlog.add(ticket);
    }

    public void removeTicket(final Ticket ticket) {
        backlog.remove(ticket);
    }
}

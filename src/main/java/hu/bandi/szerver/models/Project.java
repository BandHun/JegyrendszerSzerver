package hu.bandi.szerver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;



/*
 * Projekt
 *
 * Szerepe, hogy a jegyeket csoportokban tartsa és gyors keresést biztosítson
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany
    private List<Ticket> tickets;
}

package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Project(final String name, final Company company) {
        this.name = name;
        this.company = company;
        isValid = true;
    }
}

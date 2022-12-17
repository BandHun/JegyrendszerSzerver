package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Teams implements Serializable {
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
    @OneToOne
    private TeamsTable teamsTable;

    public Teams(final String name, final Company company) {
        this.name = name;
        this.company = company;
        isValid = true;
    }

    @Override
    public String toString() {
        return "Teams{id=" + id + ", name=" + name + "}";
    }
}

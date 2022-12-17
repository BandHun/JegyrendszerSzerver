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
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean isAdmin;
    private String name;
    private String emailaddress;

    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JsonIgnore
    private Teams teams;

    private UserLevel userLevel;

    public User(final String name, final String emailaddress, final String password, final Company company) {
        this.name = name;
        this.isAdmin = false;
        this.emailaddress = emailaddress;
        this.password = password;
        this.company = company;
        this.userLevel = UserLevel.UNKNOWN;
    }

    public User update(final User toUpdate) {
        this.name = toUpdate.getName();
        this.emailaddress = toUpdate.getEmailaddress();
        this.teams = toUpdate.getTeams();
        return this;
    }
}

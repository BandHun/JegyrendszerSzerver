package hu.bandi.szerver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



/*
 * Komment
 *
 * Szerepe, hogy tároljon egy felhasználó által írt szöveget és a hozzá tartozó dokumentumokat.
 * */

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

    private String name;
    private String emailaddress;

    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;

    public User(final String name, final String emailaddress, final String password, final Company company) {
        this.name = name;
        this.emailaddress = emailaddress;
        this.password = password;
        this.company = company;
    }
}

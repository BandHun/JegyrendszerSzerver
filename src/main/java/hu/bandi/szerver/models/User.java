package hu.bandi.szerver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;



/*
 * Komment
 *
 * Szerepe, hogy tároljon egy felhasználó által írt szöveget és a hozzá tartozó dokumentumokat.
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class User {
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
    @JoinColumn(name = "group_id")
    private Group group;
}

package hu.bandi.szerver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;



/*
 * Filter
 *
 * Lehetséges, hogy nem fogom használni és a guiban fogom megoldani hogy filterezett adatot kérjünk le, lehet erre az
 *  osztályra nem lesz szükség
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filters")
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

}

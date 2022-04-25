package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



/*
 * Filter
 *
 * A userek különböző filtereket használhatnak majd a jegyek gyors rendezésére.
 *
 * Hogy pontosan milyen szűrési feltételeket fogok a usereknek engedélyezni még nem tudom.
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filters")
public class Filter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

}

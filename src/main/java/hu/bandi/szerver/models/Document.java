package hu.bandi.szerver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



/*
 * Dokumentum
 *
 * A feladatokhoz vagy kommentekhez csatolt dokumentum.
 * Tároljuk az eredeti dokumentum nevét és egy lokációs stringet ahol a dokumentum megtalálható.
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "documents")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isValid;

    private String documentLocation;
    private String documentName;
}

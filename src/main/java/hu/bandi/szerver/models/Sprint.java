package hu.bandi.szerver.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean valid;

    private Date startDate;

    private Date endDate;

    @OneToMany
    private List<Ticket> tickets;

    public Sprint(Date startDate,Date endDate){
        this.endDate=endDate;
        this.startDate=startDate;
        this.valid = true;
        this.tickets=new ArrayList<>();
    }
}

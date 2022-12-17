package hu.bandi.szerver.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "joinrequests")
public class JoinCompanyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Company company;

    @ManyToOne
    private User user;

    private RequestStatus requestStatus;

    public JoinCompanyRequest(final Company company, final User user) {
        this.company = company;
        this.user = user;
        this.requestStatus = RequestStatus.UNDER_REVIEW;
    }
}

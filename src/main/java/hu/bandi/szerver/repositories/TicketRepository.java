package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByAuthorIn(List<User> authors);
    List<Ticket> findByCompany(Company company);
}

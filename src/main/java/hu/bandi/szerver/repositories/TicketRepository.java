package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

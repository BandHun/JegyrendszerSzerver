package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllTickets();

    Ticket findById(Long id);

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Long ticketId);

    void changeTicketStatus(Long ticketId, TicketStatus fromStatus, TicketStatus toStatus);
}

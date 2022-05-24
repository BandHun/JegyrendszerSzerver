package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.*;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllTickets();

    Ticket findById(Long id);

    List<Ticket> findByCompany(Company company);

    Ticket addTicket(String title, int storyPoint, String description);

    Ticket assigneToUser(Long id, User user);

    Ticket addToProject(Long id, Project project);

    Ticket updateTicket(Ticket ticket);

    void addComment(Long ticketId, Comment comment);

    void deleteTicket(Long ticketId);

    void changeTicketStatus(Long ticketId, TicketStatus toStatus);
}

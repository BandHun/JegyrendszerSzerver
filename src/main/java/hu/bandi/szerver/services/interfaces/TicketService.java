package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.*;

import java.util.List;

public interface TicketService {
    void removeTicketsFromSprint(List<Ticket> t);

    void setUsedStroyPoints(Ticket ticket, long hours);

    List<Ticket> findAllTickets();

    Ticket findById(Long id);

    void addDocument(Document document, Long ticketId);

    void removeUserFromAssignee(User user);

    void deleteDocument(Document document);

    List<Ticket> findByCompany(Company company);

    Ticket addTicket(Ticket ticket);

    Ticket assigneToUser(Long id, User user);

    Ticket addToProject(Long id, Project project);

    Ticket updateTicket(Ticket ticket);

    void addComment(Long ticketId, Comment comment);

    void deleteTicket(Long ticketId);

    void changeTicketStatus(Long ticketId, TicketStatus toStatus);

    void addTeam(Long ticketId, Teams teams);
}

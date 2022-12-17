package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.*;
import hu.bandi.szerver.repositories.TicketRepository;
import hu.bandi.szerver.services.interfaces.SprintService;
import hu.bandi.szerver.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SprintService sprintService;


    @Override
    public void removeTicketsFromSprint(final List<Ticket> t) {
        for (final Ticket tic : t) {
            tic.setSprint(null);
            ticketRepository.save(tic);
        }
    }

    @Override
    public void setUsedStroyPoints(final Ticket ticket, final long hours) {
        final Ticket t = getTicket(ticket.getId());
        t.setUsedStroyPoints(hours);
        ticketRepository.save(t);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(final Long id) {
        return getTicket(id);
    }

    @Override
    public void addDocument(final Document document, final Long ticketId) {
        final Ticket toEdit = findById(ticketId);
        final List<Document> d = toEdit.getDocuments();
        d.add(document);
        toEdit.setDocuments(d);
        ticketRepository.save(toEdit);
    }

    @Override
    public void removeUserFromAssignee(final User user) {
        for (final Ticket ticket : ticketRepository.findByAssigneeAndValid(user, true)) {
            ticket.setAssignee(null);
            ticketRepository.save(ticket);
        }
    }

    @Override
    public void deleteDocument(final Document document) {
        final List<Ticket> all = ticketRepository.findAll();
        for (final Ticket a : all) {
            a.removeDocument(document);
        }
    }

    @Override
    public List<Ticket> findByCompany(final Company company) {
        return ticketRepository.findByAuthorInAndValid(company.getUsers(), true);
    }

    @Override
    public Ticket addTicket(final Ticket ticket) {
        final User currentUser = CurrentUserService.getCurrentUser();
        ticket.setAuthor(currentUser);
        ticket.setCompany(currentUser.getCompany());
        ticket.setStatus(TicketStatus.TODO);
        ticket.setValid(true);
        ticket.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket assigneToUser(final Long id, final User user) {
        final Ticket edit = getTicket(id);
        edit.setAssignee(user);
        return ticketRepository.save(edit);
    }

    @Override
    public Ticket addToProject(final Long id, final Project project) {
        final Ticket edit = getTicket(id);
        edit.setProject(project);
        return ticketRepository.save(edit);
    }

    @Override
    public Ticket updateTicket(final Ticket ticket) {
        final Ticket toUpdate = findById(ticket.getId());
        ticket.setDocuments(toUpdate.getDocuments());
        if (toUpdate.getSprint() != null) {
            sprintService.aremoveTicketToSprint(ticket.getSprint(), toUpdate);
        }
        if (ticket.getSprint() != null) {
            try {
                sprintService.addTicketToSprint(ticket.getSprint(), toUpdate);
            } catch (final Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (ticket.getStatus() == toUpdate.getStatus()) {
            return ticketRepository.save(ticket);
        } else {
            changeTicketStatus(ticket.getId(), ticket.getStatus());
            return ticketRepository.save(ticket);
        }

    }

    @Override
    public void addComment(final Long ticketId, final Comment comment) {
        final Ticket toAddcomment = getTicket(ticketId);
        toAddcomment.addComment(comment);
        ticketRepository.save(toAddcomment);
    }

    @Override
    public void deleteTicket(final Long ticketId) {
        final Ticket toEdit = getTicket(ticketId);
        toEdit.setValid(false);
        ticketRepository.save(toEdit);
    }

    @Override
    public void changeTicketStatus(final Long ticketId, final TicketStatus toStatus) {
        final Ticket toEdit = getTicket(ticketId);
        if (!TicketStatus.isValidChange(toEdit.getStatus(), toStatus)) {
            throw new RuntimeException(
                    "Invalid status change from " + toEdit.getStatus() + " to " + toStatus.name() + ".");
        }
        toEdit.setStatus(toStatus);
        ticketRepository.save(toEdit);
    }

    @Override
    public void addTeam(final Long ticketId, final Teams teams) {
        final Ticket toEdit = findById(ticketId);
        toEdit.setTeams(teams);
        ticketRepository.save(toEdit);
    }

    private Ticket getTicket(final Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket not found by id:" + id + "."));
    }
}

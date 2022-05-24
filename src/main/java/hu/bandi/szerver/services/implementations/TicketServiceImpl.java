package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.*;
import hu.bandi.szerver.repositories.TicketRepository;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserService userService;


    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(final Long id) {
        return getTicket(id);
    }

    @Override
    public List<Ticket> findByCompany(Company company) {
        System.out.println(company.getUsers());
        return ticketRepository.findByAuthorIn(company.getUsers());
    }

    @Override
    public Ticket addTicket(String title, int storyPoint, String description) {
        User currentUser = userService.getCurrentUser();
        Ticket newTicket = new Ticket(title,description, currentUser , storyPoint, currentUser.getCompany());
        return ticketRepository.save(newTicket);
    }

    @Override
    public Ticket assigneToUser(Long id, User user) {
        Ticket edit = getTicket(id);
        edit.setAssignee(user);
        return ticketRepository.save(edit);
    }

    @Override
    public Ticket addToProject(Long id, Project project) {
        Ticket edit = getTicket(id);
        edit.setProject(project);
        System.out.println("AAAAAAAAAAAAAAA");
        System.out.println(project);
        return ticketRepository.save(edit);
    }

    @Override
    public Ticket updateTicket(final Ticket ticket) {
        System.out.println(ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public void addComment(Long ticketId, Comment comment) {
        Ticket toAddcomment = getTicket(ticketId);
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

    private Ticket getTicket(final Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket not found by id:" + id + "."));
    }
}

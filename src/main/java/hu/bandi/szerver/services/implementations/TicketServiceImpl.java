package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;
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
    public Ticket addTicket(final Ticket ticket) {
        ticket.setAuthor(userService.getCurrentUser());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(final Ticket ticket) {
        return ticketRepository.save(ticket);
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

package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;
import hu.bandi.szerver.repositories.TicketRepository;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.special.serverfunctions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;


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
        ticket.setAuthor(CurrentUser.getUser(userRepository));
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
    public void changeTicketStatus(final Long ticketId, final TicketStatus fromStatus, final TicketStatus toStatus) {
        if (!TicketStatus.isValidChange(fromStatus, toStatus)) {
            throw new RuntimeException(
                    "Invalid status change from " + fromStatus.name() + " to " + toStatus.name() + ".");
        }
        final Ticket toEdit = getTicket(ticketId);
        toEdit.setStatus(toStatus);
        ticketRepository.save(toEdit);
    }

    private Ticket getTicket(final Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket not found by id:" + id + "."));
    }
}

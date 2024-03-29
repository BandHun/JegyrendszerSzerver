package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;
import hu.bandi.szerver.repositories.TicketRepository;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.ProjectService;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TicketRepository ticketRepository;

    public TicketController(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTicket() {
        return new ResponseEntity<>(ticketService.findAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/allbycompany")
    public ResponseEntity<List<Ticket>> getAllByCompanyTicket() {
        return new ResponseEntity<>(
                ticketRepository.findByCompanyAndValid(CurrentUserService.getCurrentUser().getCompany(), true),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(ticketService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/getteam/{id}")
    public ResponseEntity<Teams> getTicketTeam(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(ticketService.findById(id).getTeams(), HttpStatus.OK);
    }


    @GetMapping("/getsprint/{id}")
    public ResponseEntity<Sprint> getTicketSprint(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(ticketService.findById(id).getSprint(), HttpStatus.OK);
    }

    @GetMapping("/alltouser")
    public ResponseEntity<List<Ticket>> getTicketByCompany() {
        return new ResponseEntity<>(
                ticketRepository.findByCompanyAndValid(CurrentUserService.getCurrentUser().getCompany(), true),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody final Ticket ticket) {
        return new ResponseEntity<>(ticketService.addTicket(ticket), HttpStatus.CREATED);
    }

    @PostMapping("/tosprint/{id}")
    public ResponseEntity<?> tosprint(@PathVariable("id") final Long id, @RequestBody final Sprint sprint) {
        final Ticket t = ticketService.findById(id);
        if (sprint == null) {
            throw new RuntimeException("Wrong sprint");
        }
        t.setSprint(sprint);
        ticketService.updateTicket(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Ticket> updateTicket(@RequestBody final Ticket ticket) {
        return new ResponseEntity<>(ticketService.updateTicket(ticket), HttpStatus.OK);
    }

    @PostMapping("/changestatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") final Long ticketId, @RequestBody final String status) {
        ticketService.changeTicketStatus(ticketId, TicketStatus.parse(status));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/assigneto/{id}")
    public ResponseEntity<Ticket> assigneTicket(@PathVariable("id") final Long ticketId,
                                                @RequestBody final Long userId) {
        return new ResponseEntity<>(ticketService.assigneToUser(ticketId, userService.findById(userId)), HttpStatus.OK);
    }

    @PutMapping("/addtoproject/{id}")
    public ResponseEntity<Ticket> addtoProject(@PathVariable("id") final Long ticketId,
                                               @RequestBody final Long projectId) {
        return new ResponseEntity<>(ticketService.addToProject(ticketId, projectService.findById(projectId)),
                                    HttpStatus.OK);
    }

    @PutMapping("/addtoteam/{id}")
    public ResponseEntity<?> addtoProject(@PathVariable("id") final Long ticketId, @RequestBody final Teams team) {
        ticketService.addTeam(ticketId, team);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateTicketStatus(@PathVariable("id") final Long id,
                                                @RequestBody final TicketStatus toStatus) {
        ticketService.changeTicketStatus(id, toStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") final Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

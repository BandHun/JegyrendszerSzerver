package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;
import hu.bandi.szerver.services.interfaces.SprintService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/sprint")
public class SprintController {

    @Autowired
    SprintService sprintService;

    @Autowired
    TeamsService teamsService;

    @Autowired
    TicketService ticketService;

    @PostMapping("/create/{id}")
    public ResponseEntity<Sprint> addUser(@PathVariable("id") final Long teamid, @RequestBody final Sprint sprint) {
        final Sprint tocreate = sprintService.createSprint(sprint.getStartDate(), sprint.getEndDate());
        teamsService.addSprintTotable(tocreate, teamid);
        return new ResponseEntity<>(tocreate, HttpStatus.CREATED);
    }

    @PostMapping("/valid")
    public ResponseEntity<?> valid(@RequestBody final Sprint s) {
        if (s != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new RuntimeException("NOT VALID SPRINT");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final long id) {

        final Sprint s = sprintService.getTicketById(id);
        for (final Ticket t : s.getTickets()) {
            if (t.getStatus() != TicketStatus.DONE) {
                throw new RuntimeException("Can not close sprint while you have tickets in not DONE status");
            }
        }
        ticketService.removeTicketsFromSprint(s.getTickets());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<Ticket>> g(@PathVariable("id") final Long sprintId) {
        final List<Ticket> ts = ticketService.findAllTickets();
        final List<Ticket> ret = new ArrayList<>();
        for (final Ticket t : ts) {
            if (t.getSprint() != null) {
                if (t.getSprint().getId() == sprintId) {
                    ret.add(t);
                }
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}

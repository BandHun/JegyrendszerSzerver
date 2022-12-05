package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.TicketStatus;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.interfaces.SprintService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<Sprint> addUser(@PathVariable("id") Long teamid,@RequestBody Sprint sprint) {
        Sprint tocreate = sprintService.createSprint(sprint.getStartDate(),sprint.getEndDate());
        teamsService.addSprintTotable(tocreate,teamid);
        return new ResponseEntity<>(tocreate,
                HttpStatus.CREATED);
    }

    @PostMapping("/valid")
    public ResponseEntity<?> valid(@RequestBody Sprint s){
        System.out.println(s);
        if(s!=null){
            System.out.println(s);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new RuntimeException("NOT VALID SPRINT");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){

        Sprint s = sprintService.getTicketById(id);
        for (Ticket t:s.getTickets()){
            if(t.getStatus()!= TicketStatus.DONE){
                throw new RuntimeException("Can not close sprint while you have tickets in not DONE status");
            }
        }
        ticketService.removeTicketsFromSprint(s.getTickets());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<Ticket>> g(@PathVariable("id") Long sprintId){
        List<Ticket> ts= ticketService.findAllTickets();
        List<Ticket> ret = new ArrayList<>();
        for(Ticket t: ts){
            if(t.getSprint()!= null){
                if(t.getSprint().getId() == sprintId){
                    ret.add(t);
                }
            }
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
}

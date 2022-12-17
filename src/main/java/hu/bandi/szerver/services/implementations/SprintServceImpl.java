package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.repositories.SprintRepository;
import hu.bandi.szerver.services.interfaces.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SprintServceImpl implements SprintService {

    @Autowired
    SprintRepository sprintRepository;
    @Override
    public Sprint createSprint(Date startDate, Date enddate) {
        Sprint create = new Sprint(startDate,enddate);
        return sprintRepository.save(create);

    }

    public void addTicketToSprint(Sprint sprint, Ticket ticket){
        if(sprint!=null){
        sprint.addTicket(ticket);
        sprintRepository.save(sprint);
    }}


    public void aremoveTicketToSprint(Sprint sprint, Ticket ticket){
        if(sprint!=null){
        sprint.removeTicket(ticket);
        sprintRepository.save(sprint);}
    }
    @Override
    public Sprint getTicketById(final Long id) {
        return sprintRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Sprint not found by id:" + id + "."));
    }
}

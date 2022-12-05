package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.Ticket;

public interface TeamsTableService {

    TeamsTable createTable(Teams team);

    void deleteTable(Long teamsTableId);

    void addTicket(Long teamsTableId, Ticket ticket);

    void removeTicket(Long teamsTableId, Ticket ticket);

    TeamsTable findById(Long id);

    void addSprint(Sprint sprint, TeamsTable table);
}

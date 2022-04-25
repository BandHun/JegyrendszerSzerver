package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.repositories.TeamsTableRepository;
import hu.bandi.szerver.services.interfaces.TeamsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsTableServiceImpl implements TeamsTableService {
    @Autowired
    TeamsTableRepository teamsTableRepository;

    @Override
    public TeamsTable addTable(final String name, final Teams team) {
        return teamsTableRepository.save(new TeamsTable(name, team));
    }

    @Override
    public void deleteTable(final Long teamsTableId) {
        final TeamsTable toEdit = getTable(teamsTableId);
        toEdit.setValid(false);
        teamsTableRepository.save(toEdit);
    }

    @Override
    public void addTicket(final Long teamsTableId, final Ticket ticket) {
        final TeamsTable toEdit = getTable(teamsTableId);
        toEdit.addTicket(ticket);
        teamsTableRepository.save(toEdit);
    }

    @Override
    public void removeTicket(final Long teamsTableId, final Ticket ticket) {
        final TeamsTable toEdit = getTable(teamsTableId);
        toEdit.removeTicket(ticket);
        teamsTableRepository.save(toEdit);
    }

    private TeamsTable getTable(final Long id) {
        return teamsTableRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Table not found by id:" + id + "."));
    }
}

package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.User;

import java.util.List;

public interface TeamsService {

    List<Teams> findAllTeams();

    Teams findById(Long id);

    Teams updateTeams(Teams teams);

    Teams addTeam(String name);

    void deleteTeams(Long teamsId);

    void setTable(Long teamsId, TeamsTable table);

    void addUser(Long teamsId, User user);

    void removeUser(Long teamsId, User user);
}

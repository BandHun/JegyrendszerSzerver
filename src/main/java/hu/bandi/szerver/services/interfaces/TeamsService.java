package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.User;

public interface TeamsService {

    Teams addTeam(String name, User user);

    void deleteTeams(Long teamsId);

    void setTable(Long teamsId, TeamsTable table);

    void addUser(Long teamsId, User user);

    void removeUser(Long teamsId, User user);
}

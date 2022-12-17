package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;

import java.util.List;

public interface TeamsService {

    List<Teams> findAllTeams();

    public List<Teams> findAllByCompanyIdTeams(Long id);

    Teams findById(Long id);

    Teams updateTeams(Teams teams);

    Teams addTeam(String name);

    void deleteTeams(Long teamsId);

    void setTable(Long teamsId, TeamsTable table);

    void addSprintTotable(Sprint sprint, Long teamid);

    Teams findTeamByTableId(long tableId);

    TeamsTable findTableByTeamId(Long teamid);
}

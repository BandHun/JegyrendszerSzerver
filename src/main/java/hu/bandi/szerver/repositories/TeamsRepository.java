package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.interfaces.TeamsService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Teams, Long> {

    Teams findByTeamsTable(TeamsTable teamsTable);
}

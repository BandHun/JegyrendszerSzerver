package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
 
    Teams findByTeamsTable(TeamsTable teamsTable);
}

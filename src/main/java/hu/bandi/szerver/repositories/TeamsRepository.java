package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
}

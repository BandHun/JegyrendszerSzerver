package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Teams, Long> {

    List<Teams> findTeamsByCompany(Company company);
}

package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailaddress(String emailaddress);

    List<User> findByTeams(Teams teams);

    List<User> findByCompany(Company company);
}

package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.JoinCompanyRequest;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRequestRepository extends JpaRepository<JoinCompanyRequest, Long> {

    List<JoinCompanyRequest> findByUser(User user);

    List<JoinCompanyRequest> findByCompany(Company company);

}

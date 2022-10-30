package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.JoinCompanyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRequestRepository extends JpaRepository<JoinCompanyRequest, Long> {


}

package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.HourRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourRecordRepository extends JpaRepository<HourRecords, Long> {
}

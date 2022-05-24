package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.HourRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HourRecordRepository extends JpaRepository<HourRecords, Long> {
    List<HourRecords> findByTicketId(Long ticketid);

    List<HourRecords> findByUser_Id(Long userId);
}

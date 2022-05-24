package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.HourRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HourRecordRepository extends JpaRepository<HourRecords, Long> {
    List<HourRecords> findByTicketId(Long ticketid);

    List<HourRecords> findByUser_Id(Long userId);
}

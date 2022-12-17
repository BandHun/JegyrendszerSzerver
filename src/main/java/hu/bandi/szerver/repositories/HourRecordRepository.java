package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface HourRecordRepository extends JpaRepository<HourRecords, Long> {
    List<HourRecords> findByTicketId(Long ticketid);

    List<HourRecords> findByUserAndToDate(User user, Date date);
}

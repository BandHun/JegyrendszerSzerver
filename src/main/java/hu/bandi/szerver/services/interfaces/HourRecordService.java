package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HourRecordService {

    List<HourRecords> getByTicket(Long id);
    HourRecords createHourRecord(User user, Ticket ticket, Date todate, long hours);

    void modifyRecord(Long recordId, long newHours);

    void delteRecord(Long recordId);

    long sumHoursForUser(Long userId);

}

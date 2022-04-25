package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;

public interface HourRecordService {

    void createHourRecord(User user, Ticket ticket, long hours);

    void modifyRecord(Long recordId, long newHours);

    void delteRecord(Long recordId);

}

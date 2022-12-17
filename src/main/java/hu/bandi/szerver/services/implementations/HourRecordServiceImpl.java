package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.HourRecordRepository;
import hu.bandi.szerver.services.interfaces.HourRecordService;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HourRecordServiceImpl implements HourRecordService {

    @Autowired
    HourRecordRepository hourRecordRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;
    @Override
    public List<HourRecords> getByTicket(final Long id) {
        return hourRecordRepository.findByTicketId(id);
    }

    @Override
    public HourRecords createHourRecord(final User user, final Ticket ticket, final Date toDate, long hours) {
        List<HourRecords> records = getByTicket(ticket.getId());
        for(HourRecords h:records){
            hours+=h.getRecordedhours();
        }
        ticketService.setUsedStroyPoints(ticket,hours);

        return hourRecordRepository.save(new HourRecords(user, ticket, toDate, hours));
    }


    @Override
    public void modifyRecord(final Long recordId, final long newHours) {
        final HourRecords toEdit = hourRecordRepository.findById(recordId).orElseThrow(
                () -> new RuntimeException("Hour record not found by id:" + recordId + "."));
        toEdit.setRecordedhours(newHours);
        hourRecordRepository.save(toEdit);
    }

    @Override
    public void delteRecord(final Long recordId) {
        final HourRecords toEdit = hourRecordRepository.findById(recordId).orElseThrow(
                () -> new RuntimeException("Hour record not found by id:" + recordId + "."));
        toEdit.setValid(false);
        hourRecordRepository.save(toEdit);
    }

    @Override
    public long sumHoursForUser(final Long userId, Date toDate) {
        final List<HourRecords> hours = hourRecordRepository.findByUserAndToDate(userService.findById(userId),toDate);
        long sum = 0;
        for (final HourRecords hour : hours) {
            sum += hour.getRecordedhours();
        }
        return sum;
    }

    @Override
    public long getUserWorkedHours(Long userid, Date fro){
        final List<HourRecords> rec = hourRecordRepository.findByUserAndToDate(userService.findById(userid),fro);
        long ret = 0;
        for(HourRecords h:rec){
            if (h.getToDate().after(fro)){
                ret+=h.getRecordedhours();
            }
        }
        return ret;
    }

    @Override
    public Long getTicketUsetStorypoints(long ticketId) {
        final List<HourRecords> hours = hourRecordRepository.findByTicketId(ticketId);
        long sum = 0;
        for (final HourRecords hour : hours) {
            sum += hour.getRecordedhours();
        }
        return sum;
    }
}

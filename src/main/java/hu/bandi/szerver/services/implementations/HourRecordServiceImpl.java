package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.HourRecordRepository;
import hu.bandi.szerver.services.interfaces.HourRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HourRecordServiceImpl implements HourRecordService {

    @Autowired
    HourRecordRepository hourRecordRepository;

    @Override
    public List<HourRecords> getByTicket(Long id) {
        return hourRecordRepository.findByTicketId(id);
    }

    @Override
    public HourRecords createHourRecord(final User user, final Ticket ticket, Date toDate, final long hours) {
        return hourRecordRepository.save(new HourRecords(user, ticket, toDate,hours));
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
    public long sumHoursForUser(Long userId) {
        List<HourRecords> hours = hourRecordRepository.findByUser_Id(userId);
        long sum = 0;
        for (HourRecords hour:hours
             ) {
            sum+=hour.getRecordedhours();
        }
        return sum;
    }
}

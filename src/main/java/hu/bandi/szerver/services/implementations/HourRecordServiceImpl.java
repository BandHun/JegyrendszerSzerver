package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.HourRecordRepository;
import hu.bandi.szerver.services.interfaces.HourRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HourRecordServiceImpl implements HourRecordService {

    @Autowired
    HourRecordRepository hourRecordRepository;

    @Override
    public void createHourRecord(final User user, final Ticket ticket, final long hours) {
        hourRecordRepository.save(new HourRecords(user, ticket, hours));
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
}

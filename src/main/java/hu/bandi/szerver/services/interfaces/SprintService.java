package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.Teams;

import java.sql.Date;


public interface SprintService {

    Sprint createSprint(Date startDate,Date enddate);

    Sprint getTicketById(final Long id);
}

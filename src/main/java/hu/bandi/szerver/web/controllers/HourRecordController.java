package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.repositories.HourRecordRepository;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.interfaces.HourRecordService;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hours")
public class HourRecordController {

    @Autowired
    TicketService ticketService;

    @Autowired
    HourRecordService hourRecordService;

    @Autowired
    UserService userService;

    @Autowired
    HourRecordRepository hourRecordRepository;


    @GetMapping("/getforticket/{ticketid}")
    public ResponseEntity<List<HourRecords>> getAllHoursByTicket(@PathVariable("ticketid") final Long ticketid) {
        return new ResponseEntity<>(hourRecordService.getByTicket(ticketid), HttpStatus.OK);
    }

    @PostMapping("/getforuser/{userid}")
    public ResponseEntity<Long> getHoursByUser(@PathVariable("userid") final Long userid, @RequestBody final Date toDate) {
        return new ResponseEntity<>(hourRecordService.sumHoursForUser(userid, toDate), HttpStatus.OK);
    }

    @GetMapping("/gethoursforuser/{id}")
    public ResponseEntity<Long> r(@PathVariable("id") final long userId, final Date from) {
        return new ResponseEntity<>(hourRecordService.getUserWorkedHours(userId, from), HttpStatus.OK);
    }


    @GetMapping("/gethoursforticket/{id}")
    public ResponseEntity<Long> ticketUsedStorypoints(@PathVariable("id") final long ticketId) {
        return new ResponseEntity<>(hourRecordService.getTicketUsetStorypoints(ticketId), HttpStatus.OK);
    }


    @PostMapping("/loghour")
    public ResponseEntity<HourRecords> addHour(@RequestBody final Map<String, String> body) {
        return new ResponseEntity<HourRecords>(hourRecordService.createHourRecord(CurrentUserService.getCurrentUser(),
                                                                                  ticketService.findById(Long.parseLong(
                                                                                          body.get("ticketId"))),
                                                                                  Date.valueOf(body.get("toDate")),
                                                                                  Long.parseLong(body.get("hours"))),
                                               HttpStatus.CREATED);
    }

}

package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.HourRecords;
import hu.bandi.szerver.repositories.HourRecordRepository;
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
@RequestMapping("/hours")
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

    @GetMapping("/getforuser/{userid}")
    public ResponseEntity<Long> getHoursByUser(@PathVariable("userid") final Long userid) {
        return new ResponseEntity<>(hourRecordService.sumHoursForUser(userid), HttpStatus.OK);
    }

    @PostMapping("/loghour")
    public ResponseEntity<HourRecords> addHour(@RequestBody final Map<String, String> body) {
        return new ResponseEntity<HourRecords>(hourRecordService.createHourRecord(userService.getCurrentUser(),
                                                                                  ticketService.findById(Long.parseLong(
                                                                                          body.get("ticketId"))),
                                                                                  Date.valueOf(body.get("toDate")),
                                                                                  Long.parseLong(body.get("hours"))),
                                               HttpStatus.CREATED);
    }

}

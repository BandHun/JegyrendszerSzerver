package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.TeamsTableService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class TeamsTableController {

    @Autowired
    private final TeamsTableService tableService;

    private final TeamsService teamsService;

    @Autowired
    UserService userService;

    public TeamsTableController(final TeamsTableService tableService, final TeamsService teamsService) {
        this.tableService = tableService;
        this.teamsService = teamsService;
    }

    @GetMapping("/allbycompany")
    public ResponseEntity<List<TeamsTable>> getAllTeams() {
        return new ResponseEntity<>(teamsService.findAllByCompanyIdTeams(
                CurrentUserService.getCurrentUser().getCompany().getId()).stream().map(
                team -> team.getTeamsTable()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamsTable> getTeamsById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(tableService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byteamid/{id}")
    public ResponseEntity<TeamsTable> getTeamsByTeamId(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(teamsService.findTableByTeamId(id), HttpStatus.OK);
    }

    @PostMapping("/createtoteam")
    public ResponseEntity<TeamsTable> addTeams(@RequestBody final Long id) {
        final TeamsTable created = tableService.createTable(teamsService.findById(id));
        teamsService.setTable(id, created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}


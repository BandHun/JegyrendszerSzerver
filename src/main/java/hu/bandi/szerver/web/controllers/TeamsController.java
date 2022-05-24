package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    @Autowired
    private final TeamsService teamsService;

    @Autowired
    UserService userService;

    public TeamsController(final TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping("/allbycompany")
    public ResponseEntity<List<Teams>> getAllTeams() {
        return new ResponseEntity<>(teamsService.findAllByCompanyIdTeams(userService.getCurrentUser().getCompany().getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teams> getTeamsById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(teamsService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Teams> addTeams(@RequestBody final String name) {
        return new ResponseEntity<>(teamsService.addTeam(name), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Teams> updateTeams(@RequestBody final Teams teams) {
        return new ResponseEntity<Teams>(teamsService.updateTeams(teams), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeams(@PathVariable("id") final Long id) {
        teamsService.deleteTeams(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

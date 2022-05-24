package hu.bandi.szerver.web.controllers;


import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TeamsService teamsService;

    @Autowired
    CompanyService companyService;

    @GetMapping("/allbycompany/{companyId}")
    public ResponseEntity<List<User>> getAllUser(@PathVariable("companyId") final Long comapnyId) {
        return new ResponseEntity<>(userService.findAllByCompany(companyService.findById(comapnyId)), HttpStatus.OK);
    }

    @GetMapping("/allatmycompany")
    public ResponseEntity<List<User>> getAllUserAtMyCompany() {
        return new ResponseEntity<>(userService.findAllByCompany(
                companyService.findById(userService.getCurrentUser().getCompany().getId())), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getbyteam/{id}")
    public ResponseEntity<List<User>> getUserByTeamId(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(userService.findAllByTeam(teamsService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody final User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/kickfromteam")
    public ResponseEntity<User> kickUserFromTeam(@RequestBody final Long userId) {
        return new ResponseEntity<>(userService.removeTeam(userService.findById(userId)), HttpStatus.OK);
    }

    @PutMapping("/addcompany")
    public ResponseEntity<User> addcompany(@RequestBody final Long companyId) {
        companyService.addUser(companyId, userService.getCurrentUser());
        return new ResponseEntity<>(userService.addCompany(companyService.findById(companyId)), HttpStatus.OK);
    }

    @PutMapping("/jointeam")
    public ResponseEntity<User> jointeam(@RequestBody final Long teamId) {
        return new ResponseEntity<User>(userService.addTeam(teamsService.findById(teamId)), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package hu.bandi.szerver.web.controllers;


import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.models.UserLevel;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
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

    @GetMapping("/currentuser")
    public ResponseEntity<User> getCurrentUser() {
        return new ResponseEntity<>(
                userService.findByEmailaddrasse(SecurityContextHolder.getContext().getAuthentication().getName()),
                HttpStatus.OK);
    }

    @GetMapping("/allatmycompany")
    public ResponseEntity<List<User>> getAllUserAtMyCompany() {
        return new ResponseEntity<>(userService.findAllByCompany(
                companyService.findById(CurrentUserService.getCurrentUser().getCompany().getId())), HttpStatus.OK);
    }

    @GetMapping("/allatmycompany2")
    public ResponseEntity<List<User>> getAllUserAtMyCompany2() {
        return new ResponseEntity<>(userService.findAllByCompany(
                companyService.findById(CurrentUserService.getCurrentUser().getCompany().getId())), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getbyteam/{id}")
    public ResponseEntity<List<User>> getUserByTeamId(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(userService.findAllByTeam(teamsService.findById(id)), HttpStatus.OK);
    }


    @GetMapping("/getteamem/{id}")
    public ResponseEntity<Teams> getteamem(@PathVariable("id") final Long id) {
        return new ResponseEntity<Teams>(userService.findById(id).getTeams(), HttpStatus.OK);
    }

    @PutMapping("/setlevel/{id}")
    public ResponseEntity<User> setlevel(@PathVariable("id") final Long userId, @RequestBody final UserLevel level) {
        return new ResponseEntity<>(userService.setLevel(userId, level), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody final User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/addtoteambyid/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") final Long id, @RequestBody final User user) {
        return new ResponseEntity<>(userService.addTeam(user.getId(), teamsService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/setadmin/{id}")
    public ResponseEntity<User> setAdmin(@PathVariable("id") final long id) {
        if (CurrentUserService.getCurrentUser().getUserLevel() == UserLevel.ADMIN) {
            return new ResponseEntity<>(userService.setAdmin(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/kickfromteam")
    public ResponseEntity<User> kickUserFromTeam(@RequestBody final Long userId) {
        return new ResponseEntity<>(userService.removeTeam(userService.findById(userId)), HttpStatus.OK);
    }

    @PostMapping("/leavecompany")
    public ResponseEntity<User> leaveCompany(@RequestBody final Long userId) {
        return new ResponseEntity<>(userService.removeTeam(userService.findById(userId)), HttpStatus.OK);
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

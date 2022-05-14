package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping("/userregistration")
    public ResponseEntity<User> addUser(@RequestBody final User user, @RequestBody final String password) {
        return new ResponseEntity<>(userService.registerUser(user, password), HttpStatus.CREATED);
    }
}

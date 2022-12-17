package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.MailSenderService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    UserService userService;

    @Autowired
    MailSenderService mailSenderService;

    @PostMapping("/userregistration")
    public ResponseEntity<User> addUser(@RequestBody final Map<String, String> body) {
        return new ResponseEntity<>(
                userService.registerUser(body.get("name"), body.get("emailaddress"), body.get("password")),
                HttpStatus.CREATED);
    }

    @PostMapping("/forgetpassword")
    public ResponseEntity<?> forgotpasswrd(@RequestBody final String email) {
        final Long userId = userService.findByEmailaddrasse(email).getId();
        final String newPassword = UUID.randomUUID().toString();
        userService.changePassword(userId, newPassword);
        mailSenderService.sendForgetPassword(email, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("BBBBBBBBBBBBBBBBBBBBBB", HttpStatus.CREATED);
    }
}

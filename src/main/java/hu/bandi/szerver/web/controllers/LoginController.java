package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.interfaces.UserService;
import hu.bandi.szerver.services.jwt.JwtUtil;
import hu.bandi.szerver.web.requests.LoginRequest;
import hu.bandi.szerver.web.responses.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
public class LoginController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public ResponseEntity<User> login() {
        return new ResponseEntity<>(CurrentUserService.getCurrentUser(), HttpStatus.OK);
    }


    @PostMapping("/gettoken")
    public ResponseEntity<?> generateToken(@Valid @RequestBody final LoginRequest loginRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        final User user = userService.findByEmailaddrasse(loginRequest.getUsername());
        return ResponseEntity.ok(
                new JwtResponse(jwtUtil.generateToken(user.getEmailaddress()), JwtUtil.getExpirationFromNow()));

    }

}

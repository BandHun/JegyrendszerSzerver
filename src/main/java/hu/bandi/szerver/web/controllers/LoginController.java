package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    UserService userService;
    

    @GetMapping()
    public boolean login() {
        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        logger.info(userService.getCurrentUser().getEmailaddress());
        return true;
    }
}

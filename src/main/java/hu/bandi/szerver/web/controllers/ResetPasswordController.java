package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resetpassword")
public class ResetPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    UserService userService;

    @GetMapping("/1")
    public void getpasswordChangePage() {
        userService.changePassword(1L, "asdf");
        logger.info("Password asdf");
    }
}

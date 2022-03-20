package com.marionete.proto.controller;

import com.marionete.proto.model.UserAccount;
import com.marionete.proto.spi.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marionete")
@Slf4j
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/userAccount")
    public UserAccount getUserAccount(@RequestParam ("username") String username,
                                      @RequestParam ("password") String password)
    {
       return userAccountService.getUserAccount(username,password);
    }
}
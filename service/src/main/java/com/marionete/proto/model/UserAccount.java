package com.marionete.proto.model;

import lombok.Data;

@Data
public class UserAccount {
    private  User user;
    private Account account;
}

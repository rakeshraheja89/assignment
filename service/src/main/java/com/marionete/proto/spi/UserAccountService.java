package com.marionete.proto.spi;

import com.marionete.proto.model.UserAccount;

@FunctionalInterface
public interface UserAccountService {

    /*
        This method will get the token from gRPC and invoke the backend service in async manner
     */
    UserAccount getUserAccount(String userName , String password);
}

package com.marionete.proto.services;

import com.marionete.proto.model.UserAccount;
import com.marionete.proto.spi.UserAccountService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    private final LoginServiceClient loginClient;
    private final RestConnectorService restConnector;

    @Autowired
    public UserAccountServiceImpl (LoginServiceClient loginClient , RestConnectorService restConnector)
    {
        this.loginClient=loginClient;
        this.restConnector=restConnector;
    }

    @SneakyThrows
    @Override
    public UserAccount getUserAccount(String userName, String password) {
        log.info("getUserAccount for username {}",userName);
        String token = loginClient.getToken(userName,password);
        log.info("print token for username {}",token);
        return restConnector.invokeService(token);
    }
}

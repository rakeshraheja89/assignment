package com.marionete.proto.services;

import com.marionete.proto.model.Account;
import com.marionete.proto.model.User;
import com.marionete.proto.model.UserAccount;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RestConnectorService {

public  UserAccount invokeService(String token) throws ExecutionException, InterruptedException {
    CompletableFuture<User> userDetails = getUserDetails(token);
    CompletableFuture<Account> accountDetails = getAccountDetails(token);
    UserAccount userAccount = new UserAccount();
    userAccount.setUser(userDetails.get());
    userAccount.setAccount(accountDetails.get());
    return userAccount;
}

@Async
public CompletableFuture<User> getUserDetails(String token) {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<?> httpEntity = getHttpEntity(token);
    final var userResourceUrl
            = "http://localhost:8899/marionete/user/";
    var response = restTemplate.exchange(userResourceUrl, HttpMethod.GET,
            httpEntity, User.class);
    return CompletableFuture.completedFuture(response.getBody());
}

@Async
public CompletableFuture<Account> getAccountDetails(String token) {
    RestTemplate restTemplate = new RestTemplate();
    final var httpEntity = getHttpEntity(token);
    final var  accountResourceUrl
            = "http://localhost:8899/marionete/account/";
    var response = restTemplate.exchange(accountResourceUrl, HttpMethod.GET,
            httpEntity, Account.class);
    return CompletableFuture.completedFuture(response.getBody());
}

private HttpEntity<?> getHttpEntity(String token) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.set("authorization", token);
    return new HttpEntity<>(requestHeaders);
}
}

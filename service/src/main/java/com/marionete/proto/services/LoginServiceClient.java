package com.marionete.proto.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import services.LoginRequest;
import services.LoginResponse;
import services.LoginServiceGrpc;

@Service
public class LoginServiceClient {

    public String getToken(String userName , String password)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        LoginServiceGrpc.LoginServiceBlockingStub stub
                = LoginServiceGrpc.newBlockingStub(channel);
        LoginResponse loginResponse = stub.login(LoginRequest.newBuilder()
                .setUsername(userName)
                .setPassword(password)
                .build());
        channel.shutdown();
        return loginResponse.getToken();
    }
}

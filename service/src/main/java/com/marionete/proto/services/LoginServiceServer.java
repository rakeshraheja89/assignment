package com.marionete.proto.services;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import services.LoginResponse;
import services.LoginServiceGrpc;

@Slf4j
public class LoginServiceServer extends LoginServiceGrpc.LoginServiceImplBase
{

    @SneakyThrows
    public static void main (String[] arr)
    {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new LoginServiceServer()).build();
        server.start();
        log.info("grpc server started");
        server.awaitTermination();
    }

    @Override
    public void login(services.LoginRequest request,
                      io.grpc.stub.StreamObserver<services.LoginResponse> responseObserver) {
        log.info("in login service");
        responseObserver.onNext(LoginResponse.newBuilder().setToken("2").build());
        responseObserver.onCompleted();
    }
}



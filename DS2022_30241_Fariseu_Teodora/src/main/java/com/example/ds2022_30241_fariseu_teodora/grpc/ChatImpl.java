package com.example.ds2022_30241_fariseu_teodora.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class ChatImpl extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public void sendMsg(ChatMessage request, StreamObserver<Empty> responseObserver) {
        log.info(request.getMsg());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
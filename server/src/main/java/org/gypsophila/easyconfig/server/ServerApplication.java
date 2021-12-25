package org.gypsophila.easyconfig.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.gypsophila.easyconfig.server.service.EasyConfigGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class ServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
    
}

package org.gypsophila.easyconfig.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.gypsophila.easyconfig.server.service.EasyConfigGrpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class ServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(7676).addService(new EasyConfigGrpcService());
        Server server = serverBuilder.build();
        try {
            server.start();
            log.info("grpc server start on port 7676");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

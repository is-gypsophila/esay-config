/*
 * Copyright 2021 Gypsophila open source organization.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gypsophila.easyconfig.server.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author lixiaoshuang
 */
@Service
@Slf4j
public class GrpcServer {
    
    @Autowired
    private EasyConfigGrpcService easyConfigGrpcService;
    
    @PostConstruct
    private void init() {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(7676).addService(easyConfigGrpcService);
        Server server = serverBuilder.build();
        try {
            server.start();
            log.info("grpc server start on port 7676");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

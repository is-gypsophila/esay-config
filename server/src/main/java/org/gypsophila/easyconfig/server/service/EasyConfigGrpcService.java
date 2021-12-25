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

import com.gypsophila.easyconfig.common.grpc.EasyConfigServiceGrpc;
import com.gypsophila.easyconfig.common.grpc.EasyConfigServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.gypsophila.easyconfig.common.model.ConfigInfo;
import org.gypsophila.easyconfig.common.exception.EasyConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaoshuang
 */
@Service
public class EasyConfigGrpcService extends EasyConfigServiceGrpc.EasyConfigServiceImplBase {
    
    @Autowired
    private MysqlStorageService mysqlStorageService;
    
    @Override
    public void publish(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        String namespace = request.getNamespace();
        String configKey = request.getConfigKey();
        String configValue = request.getConfigValue();
        String configType = request.getConfigType();
        
        ConfigInfo configInfo = new ConfigInfo(namespace, configKey, configValue, configType);
        try {
            int i = mysqlStorageService.saveConfig(configInfo);
            System.out.println("保存操作结果：" + i);
        } catch (EasyConfigException e) {
            e.printStackTrace();
        }
        
        EasyConfigServiceOuterClass.response response = EasyConfigServiceOuterClass.response.newBuilder()
                .setConfigKey(configKey).setConfigValue(configValue).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void get(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        String namespace = request.getNamespace();
        String configKey = request.getConfigKey();
        ConfigInfo config = null;
        try {
            config = mysqlStorageService.getConfig(namespace, configKey);
            System.out.println("获取信息：" + config.getConfigValue());
        } catch (EasyConfigException e) {
            e.printStackTrace();
        }
        EasyConfigServiceOuterClass.response response = EasyConfigServiceOuterClass.response.newBuilder()
                .setConfigKey(configKey).setConfigValue(config.getConfigValue()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void delete(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        String namespace = request.getNamespace();
        String configKey = request.getConfigKey();
        
        try {
            int result = mysqlStorageService.deleteConfig(namespace, configKey);
            System.out.println("删除：" + result);
        } catch (EasyConfigException e) {
            e.printStackTrace();
        }
        EasyConfigServiceOuterClass.response response = EasyConfigServiceOuterClass.response.newBuilder()
                .setConfigKey(configKey).setConfigValue("").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

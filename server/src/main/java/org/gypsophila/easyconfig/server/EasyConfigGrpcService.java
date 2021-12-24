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

package org.gypsophila.easyconfig.server;

import com.gypsophila.easyconfig.common.grpc.EasyConfigServiceGrpc;
import com.gypsophila.easyconfig.common.grpc.EasyConfigServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.gypsophila.easyconfig.common.ConfigInfo;

/**
 * @author lixiaoshuang
 */
public class EasyConfigGrpcService extends EasyConfigServiceGrpc.EasyConfigServiceImplBase {
    
    @Override
    public void publish(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        String namespace = request.getNamespace();
        String configKey = request.getConfigKey();
        String configValue = request.getConfigValue();
        String configType = request.getConfigType();
        
        ConfigInfo configInfo = new ConfigInfo(namespace, configKey, configValue, configType);
        
        EasyConfigServiceOuterClass.response response = EasyConfigServiceOuterClass.response.newBuilder()
                .setConfigKey(configKey).setConfigValue(configValue).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void get(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        super.get(request, responseObserver);
    }
    
    @Override
    public void delete(EasyConfigServiceOuterClass.request request,
            StreamObserver<EasyConfigServiceOuterClass.response> responseObserver) {
        super.delete(request, responseObserver);
    }
}

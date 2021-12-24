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

package com.gypsophila.easyconfig.client;

/**
 * @author lixiaoshuang
 */
public interface ConfigService {
    
    /**
     * 发布配置
     *
     * @param namespace   命名空间
     * @param configKey   配置key
     * @param configValue 配置内容
     * @param configType  配置类型
     * @return 配置key
     */
    String publish(String namespace, String configKey, String configValue, String configType);
    
    /**
     * 获取配置
     *
     * @param namespace 命名空间
     * @param configKey 配置key
     * @return 配置内容
     */
    String get(String namespace, String configKey);
    
    /**
     * 删除配置
     *
     * @param namespace 命名空间
     * @param configKey 配置key
     * @return
     */
    void delete(String namespace, String configKey);
}

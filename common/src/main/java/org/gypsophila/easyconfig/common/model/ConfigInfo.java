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

package org.gypsophila.easyconfig.common.model;

/**
 * @author lixiaoshuang
 */
public class ConfigInfo {
    
    /**
     * 命名空间
     */
    private String namespace;
    
    /**
     * 配置的唯一标识
     */
    private String configKey;
    
    /**
     * 配置的内容
     */
    private String configValue;
    
    /**
     * 配置的类型
     */
    private String configType;
    
    public ConfigInfo() {
    
    }
    
    public ConfigInfo(String namespace, String configKey, String configValue, String configType) {
        this.namespace = namespace;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configType = configType;
    }
    
    public String getNamespace() {
        return namespace;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    public String getConfigKey() {
        return configKey;
    }
    
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
    
    public String getConfigValue() {
        return configValue;
    }
    
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    
    public String getConfigType() {
        return configType;
    }
    
    public void setConfigType(String configType) {
        this.configType = configType;
    }
}

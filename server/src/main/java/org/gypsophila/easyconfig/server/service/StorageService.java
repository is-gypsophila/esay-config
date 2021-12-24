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

import org.gypsophila.easyconfig.common.model.ConfigInfo;
import org.gypsophila.easyconfig.common.exception.EasyConfigException;


/**
 * @author lixiaoshuang
 */
public interface StorageService {
    
    /**
     * 保存配置信息
     *
     * @param configInfo 配置信息
     * @return 操作结果数
     */
    int saveConfig(ConfigInfo configInfo) throws EasyConfigException;
    
    /**
     * 查询配置信息
     *
     * @param namespace 命名空间
     * @param configKey 配置唯一标识
     * @return 配置信息
     */
    ConfigInfo getConfig(String namespace, String configKey) throws EasyConfigException;
    
    /**
     * 删除配置信息
     *
     * @param namespace 命名空间
     * @param configKey 配置唯一标识
     * @return 操作结果数
     */
    int deleteConfig(String namespace, String configKey) throws EasyConfigException;
}

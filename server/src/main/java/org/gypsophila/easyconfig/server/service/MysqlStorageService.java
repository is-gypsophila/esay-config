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

import org.apache.commons.lang3.StringUtils;
import org.gypsophila.easyconfig.common.model.ConfigInfo;
import org.gypsophila.easyconfig.common.exception.EasyConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lixiaoshuang
 */
@Service
public class MysqlStorageService implements StorageService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int saveConfig(ConfigInfo configInfo) throws EasyConfigException {
        if (Objects.isNull(configInfo)) {
            throw new EasyConfigException("MysqlStorageService#saveConfig configInfo must not null");
        }
        if (StringUtils.isBlank(configInfo.getNamespace())) {
            throw new EasyConfigException("MysqlStorageService#saveConfig  namespace must not null");
        }
        if (StringUtils.isBlank(configInfo.getConfigKey())) {
            throw new EasyConfigException("MysqlStorageService#saveConfig  configKey must not null");
        }
        if (StringUtils.isBlank(configInfo.getConfigValue())) {
            throw new EasyConfigException("MysqlStorageService#saveConfig  configValue must not null");
        }
        if (StringUtils.isBlank(configInfo.getConfigType())) {
            throw new EasyConfigException("MysqlStorageService#saveConfig  configType must not null");
        }
        String sql = "insert into easy_config_info (namespace_id,config_key,config_value,config_type) values (?,?,?,?)";
        
        return jdbcTemplate.update(sql, configInfo.getNamespace(), configInfo.getConfigKey(),
                configInfo.getConfigValue(), configInfo.getConfigType());
    }
    
    @Override
    public ConfigInfo getConfig(String namespace, String configKey) throws EasyConfigException {
        if (StringUtils.isBlank(namespace)) {
            throw new EasyConfigException("MysqlStorageService#getConfig namespace must not null");
        }
        if (StringUtils.isBlank(configKey)) {
            throw new EasyConfigException("MysqlStorageService#getConfig configKey must not null");
        }
        String sql = "select * from easy_config_info where namespace_id = ? and config_key = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ConfigInfo.class), namespace, configKey);
    }
    
    @Override
    public int deleteConfig(String namespace, String configKey) throws EasyConfigException {
        if (StringUtils.isBlank(namespace)) {
            throw new EasyConfigException("MysqlStorageService#getConfig namespace must not null");
        }
        if (StringUtils.isBlank(configKey)) {
            throw new EasyConfigException("MysqlStorageService#getConfig configKey must not null");
        }
        String sql = "delete from easy_config_info where namespace_id = ? and config_key = ?";
        return jdbcTemplate.update(sql, namespace, configKey);
    }
}

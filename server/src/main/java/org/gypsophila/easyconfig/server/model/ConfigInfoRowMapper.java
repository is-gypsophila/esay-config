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

package org.gypsophila.easyconfig.server.model;

import org.gypsophila.easyconfig.common.model.ConfigInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lixiaoshuang
 */
public class ConfigInfoRowMapper implements RowMapper<ConfigInfo> {
    
    @Override
    public ConfigInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        return new ConfigInfo(rs.getString("namespace_id"), rs.getString("config_key"), rs.getString("configValue"),
                rs.getString("config_type"));
    }
}

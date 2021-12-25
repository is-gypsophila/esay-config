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

package org.gypsophila.easyconfig.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.gypsophila.easyconfig.common.exception.EasyConfigException;
import org.gypsophila.easyconfig.common.model.ConfigInfo;
import org.gypsophila.easyconfig.server.enums.ErrorCode;
import org.gypsophila.easyconfig.server.model.Response;
import org.gypsophila.easyconfig.server.service.MysqlStorageService;
import org.gypsophila.easyconfig.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author lixiaoshuang
 */
@RestController
@RequestMapping(path = "api/v1")
@Slf4j
public class EasyConfigController {
    
    @Autowired
    private MysqlStorageService mysqlStorageService;
    
    @RequestMapping(path = "config/save")
    public Response<Integer> configSave(@RequestBody ConfigInfo configInfo) {
        log.info("EasyConfigController.configSave configInfo:{}", configInfo);
        if (Objects.isNull(configInfo)) {
            return ResponseUtil.fail(ErrorCode.PARAM_ERROR.getCode(), ErrorCode.PARAM_ERROR.getMessage());
        }
        try {
            int i = mysqlStorageService.saveConfig(configInfo);
            return ResponseUtil.success(i);
        } catch (EasyConfigException e) {
        }
        return ResponseUtil.success(null);
    }
    
    @RequestMapping(path = "config/get")
    public Response<ConfigInfo> configGet(@RequestParam("namespace") String namespace,
            @RequestParam("configKey") String configKey) {
        log.info("EasyConfigController.configGet namespace:{},configKey:{}", namespace, configKey);
        if (StringUtils.isBlank(namespace) || StringUtils.isBlank(configKey)) {
            return ResponseUtil.fail(ErrorCode.PARAM_ERROR.getCode(), ErrorCode.PARAM_ERROR.getMessage());
        }
        try {
            ConfigInfo config = mysqlStorageService.getConfig(namespace, configKey);
            return ResponseUtil.success(config);
        } catch (EasyConfigException e) {
        }
        return ResponseUtil.success(null);
    }
    
    @RequestMapping(path = "config/delete")
    public Response<Integer> configDelete(@RequestParam("namespace") String namespace,
            @RequestParam("configKey") String configKey) {
        log.info("EasyConfigController.configDelete namespace:{},configKey:{}", namespace, configKey);
        if (StringUtils.isBlank(namespace) || StringUtils.isBlank(configKey)) {
            return ResponseUtil.fail(ErrorCode.PARAM_ERROR.getCode(), ErrorCode.PARAM_ERROR.getMessage());
        }
        try {
            int i = mysqlStorageService.deleteConfig(namespace, configKey);
            return ResponseUtil.success(i);
        } catch (EasyConfigException e) {
        }
        return ResponseUtil.success(null);
    }
}

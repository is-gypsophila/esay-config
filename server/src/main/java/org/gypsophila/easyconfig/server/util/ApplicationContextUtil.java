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

package org.gypsophila.easyconfig.server.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lixiaoshuang
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
    
    /**
     * 返回指定 bean 的一个实例，该实例可以是共享的，也可以是独立的
     *
     * @param beanName 实例名称
     * @return bean 实例
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
    
    
    /**
     * 返回与给定对象类型唯一匹配的 bean 实例
     *
     * @param clazz 对象类型
     * @param <T>   返回类型
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}

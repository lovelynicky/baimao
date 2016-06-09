package com.rocky.springcontext;

import com.rocky.zookeeper.ZookeeperClientSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by liluoqi on 16/5/28.
 */
@Configuration
public class Context {
    /**
     * zookeeper客户端提供
     *
     * @param environment 环境变量
     * @return
     */
    @Bean
    public ZookeeperClientSupplier zookeeperClientSupplier(Environment environment) {
        return new ZookeeperClientSupplier(environment);
    }
}

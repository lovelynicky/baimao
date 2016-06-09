package com.rocky.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.core.env.Environment;

/**
 * Created by liluoqi on 16/5/28.
 */
public class ZookeeperClientSupplier {

    private static final int SESSION_TIMEOUT_MS = 5000;
    private static final int CONNECTION_TIMEOUT_MS = 3000;

    private String sessionTimeoutMilliSeconds;
    private String connectionTimeoutMilliSeconds;

    public ZookeeperClientSupplier() {

    }

    public ZookeeperClientSupplier(Environment environment) {
    }

    /**
     * new ExponentialBackoffRetry(1000,3)重试策略
     *
     * @param host 主机
     * @param port 端口
     * @return
     */
    public CuratorFramework getZookeeperClient(String host, int port) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(String.format("%s:%s", host, port)).sessionTimeoutMs(SESSION_TIMEOUT_MS)
                .connectionTimeoutMs(CONNECTION_TIMEOUT_MS).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        return curatorFramework;
    }

    public CuratorFramework getZookeeperClient(String host, int port, int sessionTimeoutMs, int connectionTimeoutMs) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(String.format("%s:%s", host, port)).sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        return curatorFramework;
    }

    public CuratorFramework getZookeeperClient(String host, int port, String base) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(String.format("%s:%s", host, port)).sessionTimeoutMs(SESSION_TIMEOUT_MS)
                .connectionTimeoutMs(CONNECTION_TIMEOUT_MS).namespace(base).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        return curatorFramework;
    }

    public CuratorFramework getZookeeperClient(String host, int port, String base, int sessionTimeoutMs, int connectionTimeoutMs) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(String.format("%s:%s", host, port)).sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs).namespace(base).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        return curatorFramework;
    }
}

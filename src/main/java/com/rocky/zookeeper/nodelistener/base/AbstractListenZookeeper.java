package com.rocky.zookeeper.nodelistener.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;


/**
 * Created by liluoqi on 16/6/10.
 */
public abstract class AbstractListenZookeeper implements NodeCacheListener {

    private NodeCache nodeCache;

    private CuratorFramework curatorFramework;
    private String path;
    private boolean isCompressed;

    /**
     * 创建即时记录当前值的NodeCache
     *
     * @param curatorFramework
     * @param path
     * @param isCompressed
     * @return
     */
    public AbstractListenZookeeper nodeCache(CuratorFramework curatorFramework, String path, boolean isCompressed) throws Exception {
        setProperties(curatorFramework, path, isCompressed);
        this.nodeCache = new NodeCache(curatorFramework, path, isCompressed);
        this.nodeCache.getListenable().addListener(this);
        return this;
    }

    public AbstractListenZookeeper addNodeCacheListener(NodeCacheListener nodeCacheListener) {
        this.nodeCache.getListenable().addListener(nodeCacheListener);
        return this;
    }

    public AbstractListenZookeeper start(boolean isRecordNow) throws Exception {
        if (nodeCache == null) {
            throw new RuntimeException();
        }
        this.nodeCache.start(isRecordNow);
        return this;
    }

    public byte[] getNewestData() {
        try {
            return this.curatorFramework.getData().forPath(getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NodeCache getNodeCache() {
        return nodeCache;
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public String getPath() {
        return path;
    }

    public boolean isCompressed() {
        return isCompressed;
    }

    private void setProperties(CuratorFramework curatorFramework, String path, boolean isCompressed) {
        this.curatorFramework = curatorFramework;
        this.path = path;
        this.isCompressed = isCompressed;
    }
}

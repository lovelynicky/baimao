package com.rocky.zookeeper.nodelistener;

import com.rocky.utils.EncodeUtils;
import com.rocky.zookeeper.nodelistener.base.AbstractListenZookeeper;

/**
 * 监听节点
 * Created by liluoqi on 16/6/10.
 */
public class ListenNode extends AbstractListenZookeeper {
    @Override
    public void nodeChanged() throws Exception {
        System.out.println(String.format("path:%s has been updated by data:%s", getPath(),
                new String(getLatestData(), EncodeUtils.UTF8)));
    }
}

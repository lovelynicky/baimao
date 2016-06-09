package main.java;

import com.rocky.zookeeper.ZookeeperClientSupplier;
import main.java.Base.AbstractSpring4TransactionalJunitTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rocky.utils.EncodeUtils.UTF8;

/**
 * Created by liluoqi on 16/6/9.
 */
public class zkClientTest extends AbstractSpring4TransactionalJunitTest {

    private static final String HOST = "localhost";
    private static final int PORT = 2181;

    private String path = "/rocky";

    @Autowired
    private ZookeeperClientSupplier zookeeperClientSupplier;

    @Test
    public void zookeeperClientTest() {
        try {
            CuratorFramework curatorFramework = zookeeperClientSupplier.getZookeeperClient(HOST, PORT);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(path, "".getBytes(UTF8));
            final NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    System.out.println("node data update,new data:" + new String(nodeCache.getCurrentData().getData(), UTF8));
                }
            });
            curatorFramework.setData().forPath(path,"hello world".getBytes(UTF8));
            Thread.sleep(1000);
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

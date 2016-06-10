package main.java;

import com.rocky.zookeeper.ZookeeperClientSupplier;
import com.rocky.zookeeper.nodelistener.ListenNode;
import main.java.Base.AbstractSpring4TransactionalJunitTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rocky.utils.EncodeUtils.UTF8;

/**
 * Created by liluoqi on 16/6/9.
 * zkClient curator test class
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
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(path, "hello world".getBytes(UTF8));
            Thread.sleep(1000);
            new ListenNode().nodeCache(curatorFramework, path, false).start(true);
            curatorFramework.setData().forPath(path, "baimao".getBytes(UTF8));
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePathNode(){
        CuratorFramework curatorFramework = zookeeperClientSupplier.getZookeeperClient(HOST, PORT);
        try {
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

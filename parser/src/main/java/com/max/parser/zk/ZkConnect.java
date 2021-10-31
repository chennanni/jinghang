package com.max.parser.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkConnect {

    private ZooKeeper zookeeper;
    private CountDownLatch connSignal = new CountDownLatch(0);

    public ZooKeeper connect(String host) throws Exception {
        zookeeper = new ZooKeeper(host, 3000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == KeeperState.SyncConnected) {
                    connSignal.countDown();
                }
            }
        });
        connSignal.await();
        return zookeeper;
    }

    public void close() throws InterruptedException {
        zookeeper.close();
    }

    public void createPersistentNode(String path, byte[] data) throws Exception
    {
        zookeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void createEphemeralNode(String path, byte[] data) throws Exception
    {
        zookeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public void createContainerNode(String path, byte[] data) throws Exception
    {
        zookeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.CONTAINER);
    }

    public void updateNode(String path, byte[] data) throws Exception
    {
        zookeeper.setData(path, data, zookeeper.exists(path, true).getVersion());
    }

    public void deleteNode(String path) throws Exception
    {
        zookeeper.delete(path,  zookeeper.exists(path, true).getVersion());
    }

    public ZooKeeper getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

}
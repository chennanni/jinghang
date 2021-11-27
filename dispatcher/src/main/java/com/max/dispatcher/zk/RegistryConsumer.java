package com.max.dispatcher.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// note: not thread safe! careful!
public class RegistryConsumer {

    private String hostname = "localhost";
    private String serviceName;
    private String serviceId;
    private ZooKeeper zooKeeper;
    private final List<String> serviceList = new CopyOnWriteArrayList();

    public RegistryConsumer(String serviceName){
        this.serviceName = serviceName;
    }

    public RegistryConsumer(String serviceName,String serviceId){
        this.serviceName = serviceName;
        this.serviceId = serviceId;
    }

    /**
     * 服务提供方注册
     * @throws Exception
     */
    public  List<String> pullServiceList() throws Exception {
        serviceList.clear();
        ZkConnect zk = new ZkConnect();
        zk.connect(hostname);
        zooKeeper = zk.getZookeeper();
        serviceList.addAll(this.getServerList("/"+serviceName));
        return serviceList;
    }

    /**
     * 服务提供方注册
     * @throws Exception
     */
    public  List<String> pullServiceListWithoutWatcher() throws Exception {
        ZkConnect zk = new ZkConnect();
        zk.connect(hostname);
        zooKeeper = zk.getZookeeper();
        serviceList.addAll(this.getServerListWithoutWatcher("/"+serviceName));
        return serviceList;
    }

    /**
     * 功能描述: <br>
     * 〈获取集群的服务列表〉
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     */
    private List<String> getServerList(String path) {
        try {
            return zooKeeper.getChildren(path, new serverListWatch());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述: <br>
     * 〈获取集群的服务列表〉
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     */
    private List<String> getServerListWithoutWatcher(String path) {
        try {
            return zooKeeper.getChildren(path, false);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    class serverListWatch implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if(watchedEvent.getType()== Event.EventType.NodeChildrenChanged){
                System.out.println("服务列表节点数据产生变化~~~~~~");
                serviceList.clear();
                serviceList.addAll(getServerList(watchedEvent.getPath()));
                System.out.println("最新服务器列表："+serviceList);
            }
        }
    }
}
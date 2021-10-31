package com.max.parser.zk;

import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务提供端注册中心
 */
public class RegistryProvider {

    private static final Logger logger = LoggerFactory.getLogger(RegistryProvider.class);

    private String serviceHostName;
    private String serviceName;
    private String serviceId;
    private int maxInstance;

    public RegistryProvider(String serviceHostName, String serviceName, String serviceId, int maxInstance) {
        this.serviceHostName = serviceHostName;
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.maxInstance = maxInstance;
    }

    /**
     * 服务提供方注册
     * @throws Exception
     */
    public int register() throws Exception {
        ZkConnect zkConnect = new ZkConnect();
        String zkHostName = "localhost";
        zkConnect.connect(zkHostName);
        ZooKeeper zooKeeper = zkConnect.getZookeeper();
        String path1 = "/" + serviceName;
        String path2 = "/" + serviceName + "/" + serviceId;
        String path3 = "/" + serviceName + "/" + serviceId +"/" + serviceHostName;
        int instanceId = Integer.parseInt(serviceId);

        if(zooKeeper.exists(path1,false)==null){
            zkConnect.createPersistentNode(path1,new byte[0]);
        }

        // need to decide the real id
        if (serviceId.equalsIgnoreCase("0")) {
            for (int i=1; i<=maxInstance+1; i++) {
                path2 = "/" + serviceName + "/" + i;
                path3 = "/" + serviceName + "/" + i +"/" + serviceHostName;
                instanceId = i;
                if (i == maxInstance+1) {
                    logger.info("reach max instances: " + maxInstance + ", skip");
                    logger.info("服务提供方注册失败");
                    return -1;
                } else if (zooKeeper.exists(path2,false) == null) {
                    logger.info("instance [" + i + "] does not exist, creating");
                    break;
                } else {
                    logger.info("instance [" + i + "] does exist, try again");
                }
            }
        } else {
            if(zooKeeper.exists(path2,false) != null){
                logger.info("instance [" + serviceId + "] does exist, failed to create");
                logger.info("服务提供方注册失败");
                return -1;
            }
        }

        if(zooKeeper.exists(path2,false) == null){
            zkConnect.createContainerNode(path2,new byte[0]);
        }
        if(zooKeeper.exists(path3,false) == null){
            zkConnect.createEphemeralNode(path3,new byte[0]);
        }
        logger.info("服务提供方注册成功，注册信息为：" + path3);
        return instanceId;

    }
}
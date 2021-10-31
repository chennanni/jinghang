import com.max.parser.zk.ZkConnect;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Date;
import java.util.List;

public class ZkConnectTest {
    public static void main (String args[]) throws Exception
    {
        String hostname = "localhost";
        ZkConnect connector = new ZkConnect();
        ZooKeeper zk = connector.connect(hostname);

        // delete if existing
        String newNode = "/myTestNode" + new Date().getTime();
        Stat stat = zk.exists(newNode, false);
        if (stat != null) {
            System.out.println("0. Delete existing node: " + newNode);
            connector.deleteNode(newNode);
        }
        // create node
        connector.createPersistentNode(newNode, "test data 1".getBytes());
        System.out.println("1. Create node: " + newNode);

        // get all nodes
        System.out.println("2. Get all nodes...");
        List<String> zNodes = zk.getChildren("/", true);
        for (String zNode: zNodes)
        {
            System.out.println("### ChildrenNode: " + zNode);
        }

        // get node data
        byte[] data = zk.getData(newNode, true, zk.exists(newNode, true));
        System.out.print("3. Get node data: ");
        for ( byte dataPoint : data)
        {
            System.out.print ((char)dataPoint);
        }
        System.out.print("\n");

        // change node data
        connector.updateNode(newNode, "test data 2".getBytes());
        data = zk.getData(newNode, true, zk.exists(newNode, true));
        System.out.print("4. Modify node data: ");
        for ( byte dataPoint : data)
        {
            System.out.print ((char)dataPoint);
        }
        System.out.print("\n");
    }
}

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Agent {

    private MBeanServer mbs = null;

    public Agent(AdDisplayer displayer) {

        mbs = ManagementFactory.getPlatformMBeanServer();

        AdDisplayerController billboardController = new AdDisplayerController();
        billboardController.setAdDisplayer(displayer);
        ObjectName name = null;

        try {
            name = new ObjectName("AdDisplayerController:name=AdDisplayerController");
            mbs.registerMBean(billboardController, name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

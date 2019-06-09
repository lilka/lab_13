import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class AdDisplayerController extends NotificationBroadcasterSupport implements AdDisplayerControllerMBean {

    private AdDisplayer adDisplayer;
    private long SEQUENCE_NUMBER = 1;
    private boolean isTurnOn=true;
    private boolean timeWasChanged;
    private boolean textWasChanged;

    public void AdDisplayerController(){

    }

    public void changeTextAdd(String text, int numb) {
        if(adDisplayer != null){
            this.adDisplayer.setAddText(text, numb);
        }
    }


    public void changeTime(int time) {
        this.adDisplayer.changeTime(time);
        this.timeWasChanged=true;
    }

    public void turnDisplayerOff() {
        if(adDisplayer != null){
            this.adDisplayer.turnOffAdDisplayer();
            this.isTurnOn=false;
        }

    }

    public void setAdDisplayer(AdDisplayer displayer) {
        this.adDisplayer = displayer;
    }


    public void turnDisplayerOn() {
        if(adDisplayer != null){
            this.adDisplayer.turnOnAdDisplayer();
            this.isTurnOn=true;
        }
    }

    public void addNewAd(String text) {
        if(adDisplayer != null){
            this.adDisplayer.addNewAd(text);
        }

    }


    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                Notification.class.toString()
        };

        String name = "Ad displayer changed notification";
        String description = "Ad displayer changed in the adds panel controlled by this MBean";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }


}


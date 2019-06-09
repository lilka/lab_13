import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class AdDisplayerController extends NotificationBroadcasterSupport implements AdDisplayerControllerMBean {

    private AdDisplayer adDisplayer;
    private long SEQUENCE_NUMBER = 1;

    public void AdDisplayerController(){

    }

    public void changeTextAdd(String text, int numb) {
        if(adDisplayer != null){
            this.adDisplayer.setAddText(text, numb);
        }
    }



    public void changeTime(int time) {
        this.adDisplayer.changeTime(time);
    }

    public void turnDisplayerOff() {
        if(adDisplayer != null){
            this.adDisplayer.turnOffAdDisplayer();
        }

    }

    public void setAdDisplayer(AdDisplayer displayer) {
        this.adDisplayer = displayer;
        this.adDisplayer.addAdDisplayerChangeEventListener(new AdDisplayerChangeEventListener() {

            public void onDisplayerChangedEvent(AdDisplayerChangeEvent e) {
                createAndSendNotification(e);

            }
        });
    }


    public void turnDisplayerOn() {
        if(adDisplayer != null){
            this.adDisplayer.turnOnAdDisplayer();
        }
    }

    public void addNewAd(String text) {
        if(adDisplayer != null){
            this.adDisplayer.addNewAd(text);
        }

    }


    protected void createAndSendNotification(AdDisplayerChangeEvent e) {
        Notification notification = new Notification(
                Notification.class.toString(),
                e.getSource(),
                SEQUENCE_NUMBER++,
                System.currentTimeMillis(),
                "Ad displayer" + e.getSource() + (e.isWasTurnedOn() ? " was turned on." : " was turned off") +  (e.isTextWasChanged() ? " text was changed." : "") + (e.isTimeWasChanged() ? " time was changed " : ""));
        super.sendNotification(notification);
    }

    public void setDisplayer(AdDisplayer displayer) {
        this.adDisplayer = displayer;
        this.adDisplayer.addAdDisplayerChangeEventListener(new AdDisplayerChangeEventListener() {

            public void onDisplayerChangedEvent(AdDisplayerChangeEvent e) {
                    createAndSendNotification(e);
            }
        });
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


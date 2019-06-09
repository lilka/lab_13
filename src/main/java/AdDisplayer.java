import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AdDisplayer extends JPanel {
    private static final Color off = Color.RED;
    private static final Color on = Color.GREEN;

    private int currentAdd = -1;
    private int TIME = 1000;


    private java.util.List<Ad> addsList;
    private List<AdDisplayerChangeEventListener> adDisplayerChangeEventListeners;
    JLabel textPane;
    Ad ad;
    Timer timer;

    public AdDisplayer() {
        super();
        textPane = new JLabel();
        textPane.setFont(new Font("Serif", Font.PLAIN, 30));

        setLayout(new FlowLayout());
        addsList = new ArrayList<Ad>();
        for (int i = 0; i < 6; i++) {
            addsList.add(new Ad("Ad number " + i));
        }

        for (int i = 0; i < 6; i++) {
            System.out.println(addsList.get(i).getText());
        }

        textPane.setText(addsList.get(0).getText());
        currentAdd = 0;

        adDisplayerChangeEventListeners = new LinkedList<AdDisplayerChangeEventListener>();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(500,100));
        this.setBackground(on);
        this.add(textPane);

         timer = new Timer(TIME, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ad= getRandomAd();
                String textAd=ad.getText();
                textPane.setText(textAd);
                System.out.println(TIME);
                System.out.println(ad.getText());
                System.out.println(addsList.size());


            }
        });


        timer.start();
    }



    public void changeTime(int time) {
        this.TIME = time;
        generateDisplayerChangedEvent(currentAdd, true, false, true);

    }

    public void setAddText(String text, int adNumb) {
        if (currentAdd != -1 && !addsList.isEmpty() && adNumb>-1 && adNumb<addsList.size()) {
            addsList.get(currentAdd).setText(text);
            generateDisplayerChangedEvent(currentAdd, true, true, false);
            textPane.setText(addsList.get(currentAdd).getText());
            revalidate();
        }
    }

    public void turnOnAdDisplayer() {
        Color currentColor = getBackground();
        if (currentColor.equals(off)) {
            setBackground(on);
            textPane.setText(addsList.get(0).getText());
            generateDisplayerChangedEvent(currentAdd, true, false, false);
            timer.start();
        }
    }

    public void turnOffAdDisplayer() {
        Color currentColor = getBackground();
        if (currentColor.equals(on)) {
            setBackground(off);
            textPane.setText(null);
            timer.stop();
            generateDisplayerChangedEvent(currentAdd, false, false, false);
        }
    }

    public void addAdDisplayerChangeEventListener(AdDisplayerChangeEventListener listener) {
        this.adDisplayerChangeEventListeners.add(listener);
    }

    private void generateDisplayerChangedEvent(Integer addNumber, boolean wasTurnedOn, boolean textWasChanged, boolean timeWasChanged) {
        AdDisplayerChangeEvent event = new AdDisplayerChangeEvent(addNumber, wasTurnedOn, textWasChanged, timeWasChanged);
        for (AdDisplayerChangeEventListener listener : adDisplayerChangeEventListeners) {
            listener.onDisplayerChangedEvent(event);
        }
    }

    public void addNewAd(String text){
        Ad ad1=new Ad(text);
        addsList.add(ad1);

    }


    public Ad getRandomAd() {
        int addsNumb = addsList.size();
        Random rand = new Random();
        int randNumb = rand.nextInt(addsNumb);
        Ad ad = addsList.get(randNumb);
        return ad;


    }
}



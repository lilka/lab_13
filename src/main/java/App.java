import javax.swing.*;

public class App implements Runnable
{
    public static void main( String[] args )
    {


        SwingUtilities.invokeLater(new App());

    }

    public void run() {

        JFrame mainFrame = new JFrame("Billboard Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AdDisplayer displayer = new AdDisplayer();

        mainFrame.setContentPane(displayer);
        mainFrame.pack();

        mainFrame.setVisible(true);

        Agent agent = new Agent(displayer);
        System.out.println("Agent is running...");
    }
}

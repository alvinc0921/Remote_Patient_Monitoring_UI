import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
Useful tutorial:
About JList: https://www.youtube.com/watch?v=lRupi3iJmzk&list=PLM-syYolLbszU7ZATekAlWqiQx9O3XSC1&index=5&ab_channel=PaulBaumgarten
About displaying on split pane: https://www.youtube.com/watch?v=KOI1WbkKUpQ&ab_channel=LazicB.
 */

public class EmergencyUIController extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton emergencyButton;
    private JButton reportButton;
    private JButton wardButton;
    private JPanel contentPanel;
    private JLabel urgentLabel;
    private JLabel warningLabel;
    private JPanel urgentDetailsPanel;

    private JList UrgentPatName;
    private DefaultListModel<String> UModel;
    // later need to combine with alertList<Alert>, check out 1st video

    private JPanel warningDetailsPanel;
    private JList WarningPatName;
    private DefaultListModel<String> WModel;

    private JList urgentDetailsList;
    private JList warningDetailsList;

    javax.swing.Timer timerUFlash;
    javax.swing.Timer timerWFlash;

    public EmergencyUIController(ArrayList<Patient> patientList){

        // To be deleted!!
        int duration = patientList.get(0).length;

        // For visual and audio alarming effects on the emergency page
        // Codes Source: https://stackoverflow.com/questions/29371778/improve-my-jlabel-flashing

        // 1. For Urgent box (Flashing red, higher pitch more frequent louder alarming)
        Color[] UListColors = {
                Color.pink,
                new Color (255,69,52)};

        ActionListener timerUFlashAction = new ActionListener () {
            private int counter1 = 0;
            private int counter2 = 1;
            ArrayList<Integer> delayUCount = new ArrayList<Integer>();

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                ++counter1;
                counter1 %= UListColors.length;
                urgentDetailsList.setBackground ( UListColors [ counter1 ] );

                ++counter2;
                counter2 %= UListColors.length;
                urgentDetailsList.setBackground ( UListColors [ counter2 ]);

                try {
                    AudioAlarm.tone(2000,100, 0.3);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

                delayUCount.add(counter1);

                if (delayUCount.size() == 4){
                    timerUFlash.stop();
                    delayUCount.clear();
                }
            }
        };

        // 2. For Warning box (Flashing yellow, lower pitch less frequent softer alarming)
        Color[] WListColors = {
                Color.yellow,
                new Color (255,218,102)};

        ActionListener timerWFlashAction = new ActionListener () {
            private int counter1 = 0;
            private int counter2 = 1;
            ArrayList<Integer> delayWCount = new ArrayList<Integer>();

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                ++counter1;
                counter1 %= WListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter1 ] );

                ++counter2;
                counter2 %= WListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter2 ]);
                try {
                    AudioAlarm.tone(800,100, 0.05);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

                delayWCount.add(counter1);

                if (delayWCount.size() == 3){
                    timerWFlash.stop();
                    delayWCount.clear();
                }

            }
        };
        // The two flashing timers with different flashing rate (Urgent flashing faster)
        timerUFlash = new javax.swing.Timer(200, timerUFlashAction);
        timerWFlash = new javax.swing.Timer(300, timerWFlashAction);


        // Real-time displaying urgent patients and warning patients respectively on the emergency page
        final int[] counter = {0};
        Timer timer = new Timer();
        TimerTask displayAlert = new TimerTask() {
            @Override
            public void run() {

                // To be deleted!!
                System.out.println((counter[0]+1));

                UModel = new DefaultListModel();
                urgentDetailsList.setModel(UModel);

                WModel = new DefaultListModel();
                warningDetailsList.setModel(WModel);

                for (Patient pat:patientList){
                    if (pat.alertStatus == "Urgent"){
                        UModel.addElement("Patient ID: " + pat.patID + "   " + pat.firstname + " " + pat.lastname + ":  " + pat.abnormalDetails + " -- Locate at Floor: " + pat.location.get(0) + ", Room: " + pat.location.get(1) + ", Bed: " + pat.location.get(2));
                        timerUFlash.start();
                    }
                    if (pat.alertStatus == "Warning"){
                        WModel.addElement("Patient ID: " + pat.patID + "   " + pat.firstname + " " + pat.lastname + ":  " + pat.abnormalDetails + " -- Locate at Floor: " + pat.location.get(0) + ", Room: " + pat.location.get(1) + ", Bed: " + pat.location.get(2));
                        timerWFlash.start();
                    }
                    // To be deleted!
                    System.out.print(pat.firstname + " " + pat.lastname + " " + pat.alertStatus+" "+ pat.abnormalDetails+ "\n Temp history:" + pat.alertHistoryTemp+"\n HR history: " + pat.alertHistoryHR+"\n RR history: " +pat.alertHistoryRR+"\n");
                }

                // To be deleted!
                counter[0]++;
                /*
                if (counter[0]==duration){
                    timer.cancel();
                }

                 */
            }
        };
        // Displaying the alert at every second
        timer.schedule(displayAlert, 0, 1000);


        // Action Listeners for switching between Ward page and Report page
        ActionListener switchWard = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientDetailsFrame patientDetailsFrame = new PatientDetailsFrame();
            }
        };
        wardButton.addActionListener(switchWard);

        ActionListener switchReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);
            }
        };
        reportButton.addActionListener(switchReport);

        /*
        ActionListener switchEmergency = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Already on emergency page...");
            }
        };
        emergencyButton.addActionListener(switchEmergency);
         */

        setContentPane(mainPanel);
        setTitle("PatientMed");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

}

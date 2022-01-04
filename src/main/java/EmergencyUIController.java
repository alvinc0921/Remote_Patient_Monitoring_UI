import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;

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

    private JLabel urgentDetailsLabel;
    private JPanel warningDetailsPanel;
    private JList WarningPatName;
    private DefaultListModel<String> WModel;

    private JLabel warningDetailsLabel;
    private JList urgentDetailsList;
    private JList warningDetailsList;

    javax.swing.Timer timerUFlash;
    javax.swing.Timer timerWFlash;

    public EmergencyUIController(ArrayList<Patient> patientList){

        // Get the length of the patient's vital's length (for now):
        int duration = patientList.get(0).length;

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

                delayUCount.add(counter1);

                if (delayUCount.size() == 4){
                    timerUFlash.stop();
                    delayUCount.clear();
                }

            }
        };

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
                counter1 %= UListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter1 ] );

                ++counter2;
                counter2 %= UListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter2 ]);

                delayWCount.add(counter1);

                if (delayWCount.size() == 4){
                    timerWFlash.stop();
                    delayWCount.clear();
                }

            }
        };
        timerUFlash = new javax.swing.Timer(200, timerUFlashAction);
        timerWFlash = new javax.swing.Timer(200, timerWFlashAction);


        final int[] counter = {0};
        Timer timer = new Timer();
        TimerTask displayAlert = new TimerTask() {
            @Override
            public void run() {

                System.out.println((counter[0]+1));

                UModel = new DefaultListModel();
                urgentDetailsList.setModel(UModel);

                WModel = new DefaultListModel();
                warningDetailsList.setModel(WModel);

                for (Patient pat:patientList){
                    if (pat.alertStatus == "Urgent"){
                        UModel.addElement(pat.name + ":  " + pat.abnormalDetails + " -- Locate at " + pat.patLoc);
                       timerUFlash.start();
                    }
                    else if (pat.alertStatus == "Warning"){
                        WModel.addElement(pat.name + ":  " + pat.abnormalDetails + " -- Locate at " + pat.patLoc);
                        timerWFlash.start();
                    }
                    System.out.print(pat.name + " " + pat.alertStatus+" "+ pat.abnormalDetails+ "\n Temp history:" + pat.alertHistoryTemp+"\n HR history: " + pat.alertHistoryHR+"\n RR history: " +pat.alertHistoryRR+"\n");
                }

                counter[0]++;
                if (counter[0]==duration){
                    timer.cancel();
                }
            }
        };
        timer.schedule(displayAlert, 0, 1000);


        ActionListener switchWard = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // uncomment this:
                // mainPanel.setVisible(false);
                PatientDetailsFrame patientDetailsFrame = new PatientDetailsFrame();
            }
        };
        wardButton.addActionListener(switchWard);

        ActionListener switchReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                //mainPanel.setVisible(false);
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);

            }
        };
        reportButton.addActionListener(switchReport);

        ActionListener switchEmergency = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                System.out.println("Already on emergency page...");
            }
        };
        emergencyButton.addActionListener(switchEmergency);

        setContentPane(mainPanel);
        setTitle("PatientMed");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }


}

import javax.swing.*;
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

public class EmergencyUIController extends JFrame{
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

    public EmergencyUIController(ArrayList<Patient> patientList){

        JFrame emUIFrame = new JFrame("PatientMed");
        emUIFrame.setContentPane(mainPanel);
        emUIFrame.setTitle("PatientMed");
        emUIFrame.setSize(1200, 800);
        emUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        emUIFrame.setVisible(true);

        // Get the length of the patient's vital's length (for now):
        int duration = patientList.get(0).length;

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
                    }
                    if (pat.alertStatus == "Warning"){
                        WModel.addElement(pat.name + ":  " + pat.abnormalDetails + " -- Locate at " + pat.patLoc);
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
                //PatientWardFrame patientWardFrame = new PatientWardFrame();
                System.out.println("Switching to Ward page...");
            }
        };
        wardButton.addActionListener(switchWard);

        ActionListener switchReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                // mainPanel.setVisible(false);
                //emUIFrame.setVisible(false);
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



    }


}
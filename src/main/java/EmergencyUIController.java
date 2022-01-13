import com.sendemail.SendEmail;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


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
    private DefaultListModel<String> UModel;
    private JPanel warningDetailsPanel;
    private DefaultListModel<String> WModel;
    private JList urgentDetailsList;
    private JList warningDetailsList;
    private JButton muteButton;

    javax.swing.Timer timerUAlarm;              // Timer for visual flashing in urgentDetailsList
    javax.swing.Timer timerWAlarm;              // Timer for visual flashing in warningDetailsList

    public EmergencyUIController(ArrayList<Patient> patientList){

    // 1. Visual and audio alarms in the emergencyUI
        final int[] soundFlag = {1};            // Initialise soundFlag = 1 (1-not muted, 0-muted) for audio alarm

        // 1a. For Urgent (Visual: flashing in red, Audio: beeping with higher pitch, more frequent, louder)
        Color[] UListColors = {                 // A list of 2 Color objects that the UrgentDetailsList is flashing between
                Color.pink,
                new Color (255,82,75)};
        ActionListener UrgentAlarm = new ActionListener () {    // Urgent visual and audio alarming ActionListener
            private int counter1 = 0;
            private int counter2 = 1;

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                // Switching background color between 2 colors on the UListColors
                ++counter1;
                counter1 %= UListColors.length;
                urgentDetailsList.setBackground ( UListColors [ counter1 ] );

                ++counter2;
                counter2 %= UListColors.length;
                urgentDetailsList.setBackground ( UListColors [ counter2 ]);

                if (soundFlag[0] == 1){         // When sound is not muted
                    try {
                        AudioAlarm.tone(2000,100, 0.2);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        };  // Codes from: https://stackoverflow.com/questions/29371778/improve-my-jlabel-flashing

        // 1b. For Warning (Visual: flashing in yellow, Audio: beeping with lower pitch, less frequent, softer)
        Color[] WListColors = {                 // A list of 2 Color objects that the WarningDetailsList is flashing between
                Color.yellow,
                new Color (255,218,102)};
        ActionListener WarningAlarm = new ActionListener () {   // Warning visual and audio alarming ActionListener
            private int counter1 = 0;
            private int counter2 = 1;

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                // Switching background color between 2 colors on the WListColors
                ++counter1;
                counter1 %= WListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter1 ] );

                ++counter2;
                counter2 %= WListColors.length;
                warningDetailsList.setBackground ( WListColors [ counter2 ]);

                if (soundFlag[0] == 1){         // When sound is not muted
                    try {
                        AudioAlarm.tone(800,100, 0.05);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        timerUAlarm = new javax.swing.Timer(200, UrgentAlarm);  // Timer for urgent alarming (repeat every 200ms - faster)
        timerWAlarm = new javax.swing.Timer(300, WarningAlarm); // Timer for warning alarming (repeat every 300ms)


    /* 2. Real-time display of urgent patients and warning patients on the emergencyUI
          Implement a TimerTask scheduled at every second to:
          - Check if the patient is at urgent/ warning status
          - If so, display their details onto the corresponding JList
          - Start the corresponding alarm timer
          - Send an email alert to the hospital or doctor
     */
        Timer timer = new Timer();
        TimerTask displayAlert = new TimerTask() {
            @Override
            public void run() {
                UModel = new DefaultListModel();
                urgentDetailsList.setModel(UModel);

                WModel = new DefaultListModel();
                warningDetailsList.setModel(WModel);

                for (Patient pat:patientList){      // Check each patient on the patientList
                    if (pat.alertStatus == "Urgent"){
                        // Add the patient onto the UModel and show his ID, name, abnormality and location
                        UModel.addElement("Patient ID: " + pat.patID + "   " + pat.firstname + " " + pat.lastname + ":  "
                                + pat.abnormalDetails
                                + " -- Locate at Floor: " + pat.location.get(0) + ", Room: " + pat.location.get(1) + ", Bed: " + pat.location.get(2));
                    }
                    if (pat.alertStatus == "Warning"){
                        // Add the patient onto the WModel and show his ID, name, abnormality and location
                        WModel.addElement("Patient ID: " + pat.patID + "   " + pat.firstname + " " + pat.lastname + ":  "
                                + pat.abnormalDetails
                                + " -- Locate at Floor: " + pat.location.get(0) + ", Room: " + pat.location.get(1) + ", Bed: " + pat.location.get(2));
                    }
                }

                if (UModel.getSize() != 0){     // Start the urgent alarm if there is any urgent patient
                    timerUAlarm.start();
                }
                if (WModel.getSize() != 0){     // Start the warning alarm if there is any warning patient
                    timerWAlarm.start();
                }
                if (UModel.getSize() == 0){     // Stop the urgent alarm if there is no urgent patient
                    timerUAlarm.stop();
                }
                if (WModel.getSize() == 0){     // Stop the warning alarm if there is no warning patient
                    timerWAlarm.stop();
                }

                emailSending(patientList);      // Send email alert and update emailFlag for every urgent/warning patient
            }
        };

        timer.schedule(displayAlert, 0, 1000);      // TimerTask displayAlert is scheduled without delay and repeated at every 1000ms (1s)

    // 3. Reset pat.emailFlag = 0 every 15min
        Timer timerResetEmailFlag = new Timer();
        TimerTask resetEmailFlag = new TimerTask() {
            @Override
            public void run() {
                for (Patient pat:patientList){
                    pat.emailFlag = 0;
                }
            }
        };
        timerResetEmailFlag.schedule(resetEmailFlag, 0, 15*1000*60);


    // 4. ActionListeners for buttons on the emergencyUI
        // 4a. muteButton - to mute/unmute the audio alarm
        final int[] muteCount = {0};                            // Counter of number of times the muteButton is pressed
        ActionListener mute = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muteCount[0]++;                                 // +1 every time muteButton is pressed
                if ((muteCount[0]%2) == 1){                     // When the muteButton is pressed when sound is on (repeated)
                    soundFlag[0] = 0;                           // change the soundFlag to 0 - muted
                    muteButton.setText("Unmute");               // changing the button text
                }
                else if ((muteCount[0]%2) == 0){                // When the muteButton is pressed when sound is off (repeated)
                    soundFlag[0] = 1;                           // change the soundFlag to 1 - not muted
                    muteButton.setText("Mute");                 // changing the button text
                }
            }
        };
        muteButton.addActionListener(mute);


        // 4b. wardButton - go to patientWardFrame
        ActionListener switchWard = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
            }
        };
        wardButton.addActionListener(switchWard);

        // 4c. reportButton - go to reportSubmenu
        ActionListener switchReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);
            }
        };
        reportButton.addActionListener(switchReport);

    // 5. Set up the panel and to make it visible
        setContentPane(mainPanel);
        setTitle("PatientMed");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);        // The app is closed when the emergencyUI is closed
        setVisible(true);

    }


// A class method to send out an email alert when there is any patient is in urgent/ warning status
    public static void emailSending(ArrayList<Patient> patientList){
        for (Patient pat:patientList){
            if ((pat.alertStatus == "Urgent" | pat.alertStatus == "Warning") && pat.emailFlag == 0){
                String location = "Floor: " + pat.location.get(0) + ", Room: " + pat.location.get(1) + ", Bed: " + pat.location.get(2);
                SendEmail.SendEmail(pat.alertStatus, pat.firstname, pat.lastname, location, pat.abnormalDetails);   // Call the SendEmail class method
                pat.emailFlag = 1;      // change the emailFlag to indicate an email alert has been sent
            }
        }
    }

}

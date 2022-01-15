import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.*;
import java.util.List;

public class PatientDetailsFrame extends JFrame{
    //Menu buttons
    private JPanel patientProfilePanel;
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    //Fields containing patient details
    private JTextField tfName;
    private JTextField tfSex;
    private JTextField tfAge;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JTextField tfBloodType;
    private JTextField tfHospitalized;
    private JTextField tfEmergency;
    //Button to go back to the Ward window
    public JButton xButton;
    //Needed to implement the plotting for n seconds, where n is variable
    private JSlider slider;
    private JTextField tfPlotTime;
    //Panels in which the plots are created
    private JPanel bodyTempPanel;                         //Body Temperature
    private JPanel heartRatePanel;                        //For Heart Rate
    private JPanel respiratoryRatePanel;                  //For Respiratory Rate
    private JPanel bloodPressurePanel;                    //For Blood Pressure
    private JPanel ecgPanel;                              //ECG
    //Button to mute the heartbeat of the patient
    private JButton beatMuteButton;
    //Used in passing the vital values from the database into the plots
    private List<DrawGraph> graphs;
    private Patient pat;
    private int signalIndex1;
    private int signalIndex2;
    private int signalIndex3;

    public PatientDetailsFrame(ArrayList<Patient> patientList, int patientIndex) {
        setContentPane(patientProfilePanel);
        setBounds(1200,0,1200,800);              //Value for windows on the right
        setTitle("Patient's data");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        pat = patientList.get(patientIndex);                        //Getting the index of the patient the user is currently looking at
        signalIndex1 = 0;
        signalIndex2 = 0;
        signalIndex3 = 0;

        tfName.setText(pat.firstname + " " + pat.lastname);        //Passing and displaying the information about the patient in the corresponding text fields
        tfSex.setText("f");
        tfAge.setText(String.valueOf(pat.age));
        tfHeight.setText("1.71 m");
        tfWeight.setText("67 kg");
        tfBloodType.setText(pat.bloodType);
        tfHospitalized.setText("Floor " + pat.location.get(0) + ", Room " + pat.location.get(1) + ", Bed " + pat.location.get(2));
        tfEmergency.setText("none");


        slider.addChangeListener(e -> {
            setGraphDuration(slider.getValue() * 1000);            //Converting from seconds to milliseconds
            tfPlotTime.setText(String.valueOf(slider.getValue())); //Displaying the number of seconds chosen by user when plotting
        });

        //HeartBeat indication
        final double[] heartRate = {0};
        final int[] counter = {0};
        final int[] soundFlag = {1};

        ActionListener heartBeat = new ActionListener () {

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                if (soundFlag[0] == 1){                                     // When sound is on, produce the beeping sound
                    try {
                        AudioAlarm.tone(300,100, 0.1);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        java.util.Timer hrTimer = new java.util.Timer();
        TimerTask heartRateCount = new TimerTask() {
            @Override
            public void run() {
                heartRate[0] = pat.hrSig.get(counter[0]).longValue();
                Timer heartBeatTimer = new Timer((int) (60*1000/heartRate[0]), heartBeat);      // Set the heartBeatTimer repeating according to the heartRate
                heartBeatTimer.start();
                counter[0] = counter[0] + 5;                                                    // Get the Heart Rate value every 5 seconds

                ActionListener soundEnd = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        heartBeatTimer.stop();  // Stop the heartBeat when any of the exit button is pressed
                    }
                };
                xButton.addActionListener(soundEnd);
                emergencyButton.addActionListener(soundEnd);
                reportButton.addActionListener(soundEnd);
                wardButton.addActionListener(soundEnd);
            }
        };
        hrTimer.schedule(heartRateCount, 0, 5*1000);        // Updating the new heart rate at every 5 seconds


        //Action listeners for the buttons in the menu
        ActionListener alertAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTimer.cancel();
                setVisible(false);
                //EmergencyUIController emUIController = new EmergencyUIController(patientList);
            }
        };
        emergencyButton.addActionListener(alertAL);

        ActionListener reportAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTimer.cancel();
                setVisible(false);
                Reportmenu reportmenu = new Reportmenu(patientList);
                //go_ward_menu();
            }
        };
        reportButton.addActionListener(reportAL);

        ActionListener wardAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTimer.cancel();
                setVisible(false);
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
                //go_ward_menu();
            }
        };
        wardButton.addActionListener(wardAL);

        //Action listener for the xButton: closing the Patient Details window and reopening Ward
        ActionListener detailsClose = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTimer.cancel();
                setVisible(false);
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
                //go_ward_menu();
            }
        };
        xButton.addActionListener(detailsClose);

        final int[] muteCount = {0};                            // Counter of number of times the muteButton is pressed
        ActionListener mute = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muteCount[0]++;                                 // +1 every time muteButton is pressed
                if ((muteCount[0]%2) == 1){                     // When the muteButton is pressed when sound is on (repeated)
                    soundFlag[0] = 0;                           // change the soundFlag to 0 - muted
                    beatMuteButton.setText("Unmute");           // changing the button text
                }
                else if ((muteCount[0]%2) == 0){                // When the muteButton is pressed when sound is off (repeated)
                    soundFlag[0] = 1;                           // change the soundFlag to 1 - not muted
                    beatMuteButton.setText("Mute");             // changing the button text
                }
            }
        };
        beatMuteButton.addActionListener(mute);

    }

/*
    //Creating the main method
    public static void main(String[] args) {
        PatientDetailsFrame patientProfileFrame = new PatientDetailsFrame();
    }
 */


    private void setGraphDuration(int duration) {
        for (DrawGraph graph : graphs) {
            graph.setPlotDuration(duration);
        }
    }

    //Creating an ArrayList with all our graphs, their y plotting range and their colours
    private void createUIComponents() {
        graphs = new ArrayList<>();
        graphs.add(new DrawGraph(36, 37, Color.RED, 1000));
        graphs.add(new DrawGraph(60, 120, Color.ORANGE, 80));
        graphs.add(new DrawGraph(65, 85, Color.GREEN, 1000));
        graphs.add(new DrawGraph(10, 20, Color.BLUE, 1000));
        graphs.add(new DrawGraph(800, 1400, Color.BLACK, 50));

        bodyTempPanel = graphs.get(0);
        heartRatePanel = graphs.get(1);
        respiratoryRatePanel = graphs.get(2);
        bloodPressurePanel = graphs.get(3);
        ecgPanel = graphs.get(4);

        //Plotting signals with 1 value/second
        Timer timer1 = new Timer(1000, e -> {
                graphs.get(0).addPlotValue(pat.tempSig.get(signalIndex1).doubleValue());
                graphs.get(2).addPlotValue(pat.hrSig.get(signalIndex1).doubleValue());
                graphs.get(3).addPlotValue(pat.rrSig.get(signalIndex1).doubleValue());
                signalIndex1++;
            graphs.get(0).updateUI();
            graphs.get(2).updateUI();
            graphs.get(3).updateUI();
        });

        //Plotting signal with 12.5 values/second
        Timer timer2 = new Timer(80, e -> {
                graphs.get(1).addPlotValue(pat.bpSig.get(signalIndex2).doubleValue());
                signalIndex2++;
            graphs.get(1).updateUI();
        });

        //Plotting signal with 20 values/second
        Timer timer3 = new Timer(50, e -> {
                graphs.get(4).addPlotValue(pat.ecgSig.get(signalIndex3).doubleValue());
                signalIndex3++;
            graphs.get(4).updateUI();
        });

        timer1.start();
        timer2.start();
        timer3.start();
    }
}


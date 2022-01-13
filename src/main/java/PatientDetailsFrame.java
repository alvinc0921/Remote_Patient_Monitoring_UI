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
    private JTextField tfName;
    //String nameVar = tfName.getText();
    private JTextField tfSex;
    //String sexVar = tfSex.getText();
    private JTextField tfAge;
    //String ageVar = tfAge.getText();
    private JTextField tfHeight;
    //String heightVar = tfHeight.getText();
    private JTextField tfWeight;
    //String weightVar = tfWeight.getText();
    private JTextField tfBloodType;
    //String bloodTypeVar = tfBloodType.getText();
    private JTextField tfHospitalized;
    //String hospitalizedVar = tfHospitalized.getText();
    private JTextField tfEmergency;
    //String emergencyVar = tfEmergency.getText();
    public JButton xButton;
    private JPanel patientProfilePanel;
    private JScrollBar scrollBar1;
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    private JTextField tfPlotTime;

    //Body Temperature
    private JPanel bodyTempPanel;
    //For Heart Rate
    private JPanel heartRatePanel;
    //For Respiratory Rate
    private JPanel respiratoryRatePanel;
    //For Blood Pressure
    private JPanel bloodPressurePanel;
    //ECG
    private JPanel ecgPanel;
    private JSlider slider;

    private List<DrawGraph> graphs;
    private Patient pat;
    private int signalIndex1;
    private int signalIndex2;
    private int signalIndex3;

    public PatientDetailsFrame(ArrayList<Patient> patientList, int patientIndex) {
        setContentPane(patientProfilePanel);
        setBounds(1200,0,1200,800); //value for windows on the right
        setTitle("Patient's data");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        pat = patientList.get(patientIndex);
        signalIndex1 = 0;
        signalIndex2 = 0;
        signalIndex3 = 0;

        tfName.setText(pat.firstname + " " + pat.lastname);
        tfSex.setText("f");
        tfAge.setText(String.valueOf(pat.age));
        tfHeight.setText("1.71 m");
        tfWeight.setText("67 kg");
        tfBloodType.setText(pat.bloodType);
        tfHospitalized.setText("Floor " + pat.location.get(0) + ", Room " + pat.location.get(1) + ", Bed " + pat.location.get(2));
        tfEmergency.setText("none");


        slider.addChangeListener(e -> {
            setGraphDuration(slider.getValue() * 1000); // converts from seconds to milliseconds
            //String x = JTextField.getText();
            tfPlotTime.setText(String.valueOf(slider.getValue()));
        });

        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hide PatientDetailsFrame
                AddPatientFrame patientDetails = new AddPatientFrame(patientList);//1. Create the frame.
                patientDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                patientDetails.setTitle("Patient Details");//3. Set title for new frame
                patientDetails.setSize(1200,800);//4. Size the frame.
                patientDetails.setVisible(false);//5. Hide it.
            }
        });


        // HeartBeat indication
        final double[] heartRate = {0};
        final int[] counter = {0};
        int patIndex = 0;   // Need to be updated later -

        ActionListener heartBeat = new ActionListener () {

            @Override
            public void actionPerformed ( ActionEvent ae ) {
                try {
                    AudioAlarm.tone(300,100, 0.1);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        };

        java.util.Timer hrTimer = new java.util.Timer();
        TimerTask heartRateCount = new TimerTask() {
            @Override
            public void run() {
                heartRate[0] = patientList.get(patIndex).hrSig.get(counter[0]).longValue();
                // To be deleted - heartRate[0] = hrTrial.get(counter[0]).longValue();
                System.out.println(heartRate[0]);
                Timer heartBeatTimer = new Timer((int) (60*1000/heartRate[0]), heartBeat);
                heartBeatTimer.start();
                counter[0] = counter[0] + 5;   // HeartBeatSound changing every 10 seconds

                ActionListener soundEnd = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        heartBeatTimer.stop();
                    }
                };
                xButton.addActionListener(soundEnd);
                emergencyButton.addActionListener(soundEnd);
                reportButton.addActionListener(soundEnd);
                wardButton.addActionListener(soundEnd);
            }
        };
        hrTimer.schedule(heartRateCount, 0, 5*1000);


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
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);
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

    }
/*
    //Creating the main method
    public static void main(String[] args) {
        PatientDetailsFrame patientProfileFrame = new PatientDetailsFrame();
    }

 */

    private void setGraphDuration(double duration) {
        for (DrawGraph graph : graphs) {
            graph.setPlotDuration(duration);
        }
    }

    private void createUIComponents() {
        graphs = new ArrayList<>();
        graphs.add(new DrawGraph(36, 37, Color.RED, 100));
        graphs.add(new DrawGraph(60, 120, Color.ORANGE, 100));
        graphs.add(new DrawGraph(59, 85, Color.YELLOW, 100));
        graphs.add(new DrawGraph(10, 20, Color.GREEN, 100));
        graphs.add(new DrawGraph(80, 130, Color.BLUE, 100));

        bodyTempPanel = graphs.get(0);
        heartRatePanel = graphs.get(1);
        respiratoryRatePanel = graphs.get(2);
        bloodPressurePanel = graphs.get(3);
        ecgPanel = graphs.get(4);

        // Clock clock = Clock.systemDefaultZone();

        //Patient patient = new Patient(1, "Petko", "Adello", 35, "0+");
            //List<BigDecimal> list = patient.getTempList();

            //for(int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i));
            //}

        Timer timer1 = new Timer(1000, e -> {
                graphs.get(0).addPlotValue(pat.tempSig.get(signalIndex1).doubleValue());
                graphs.get(2).addPlotValue(pat.hrSig.get(signalIndex1).doubleValue());
                graphs.get(3).addPlotValue(pat.rrSig.get(signalIndex1).doubleValue());
                signalIndex1++;
            graphs.get(0).updateUI();
            graphs.get(2).updateUI();
            graphs.get(3).updateUI();
        });

        Timer timer2 = new Timer(80, e -> {
                graphs.get(1).addPlotValue(pat.bpSig.get(signalIndex2).doubleValue());
                signalIndex2++;
            graphs.get(1).updateUI();
        });

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


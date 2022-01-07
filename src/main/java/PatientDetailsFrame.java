import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    public PatientDetailsFrame(ArrayList<Patient> patientList) {
        setContentPane(patientProfilePanel);
        setBounds(1200,0,1200,800); //value for windows on the right
        setTitle("Patient's data");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        /*
        String name, String sex, String age, String height, String weight, String bloodType, String hospitalized, String emergency
          this.nameVar = name;
        this.sexVar = sex;
        this.ageVar = age;
        this.heightVar = height;
        this.weightVar = weight;
        this.bloodTypeVar = bloodType;
        this.hospitalizedVar = hospitalized;
        this.emergencyVar = emergency;
         */

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

        ActionListener alertAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //EmergencyUIController emUIController = new EmergencyUIController(patientList);
            }
        };
        emergencyButton.addActionListener(alertAL);

        ActionListener reportAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);
                //go_ward_menu();
            }
        };
        reportButton.addActionListener(reportAL);

        ActionListener wardAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
                //go_ward_menu();
            }
        };
        wardButton.addActionListener(wardAL);

        /*
        ActionListener detailsClose = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
                //go_ward_menu();
            }
        };
        xButton.addActionListener(detailsClose);
         */

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
        graphs.add(new DrawGraph(0, 4, Color.RED, 100));
        graphs.add(new DrawGraph(0, 4, Color.ORANGE, 100));
        graphs.add(new DrawGraph(0, 4, Color.YELLOW, 100));
        graphs.add(new DrawGraph(0, 4, Color.GREEN, 100));
        graphs.add(new DrawGraph(0, 4, Color.BLUE, 100));

        bodyTempPanel = graphs.get(0);
        heartRatePanel = graphs.get(1);
        respiratoryRatePanel = graphs.get(2);
        bloodPressurePanel = graphs.get(3);
        ecgPanel = graphs.get(4);

        Clock clock = Clock.systemDefaultZone();

        Timer timer = new Timer(3, e -> {
            for (int i = 0; i < graphs.size(); i++) {
                DrawGraph graph = graphs.get(i);
                graph.addPlotValue(Math.sin((i + 1) * clock.millis() / 100.0) + 2);
                graph.updateUI();
            }
        });

        timer.start();
    }
}


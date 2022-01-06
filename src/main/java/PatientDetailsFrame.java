import javax.swing.*;
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
    private JTextField tfAge;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JTextField tfBloodType;
    private JTextField tfHospitalized;
    private JTextField tfEmergency;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton xButton;
    private JPanel patientProfilePanel;
    private JScrollBar scrollBar1;
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;

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

    public PatientDetailsFrame() {
        setContentPane(patientProfilePanel);
        setTitle("Patient's data");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //get patient data and plot in the Panels

    //Creating the main method
    public static void main(String[] args) {
        PatientDetailsFrame patientProfileFrame = new PatientDetailsFrame();
        //DrawGraphBodyTemp
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        List<DrawGraph> graphs = new ArrayList<>();
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
//change

/*
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
 */


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        List<Integer> scores = new ArrayList<>();
        scores.add(1);
        scores.add(2);
        scores.add(0);
        bodyTempPanel.add(new DrawGraph(scores));
    }
}
//change

/*
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
 */

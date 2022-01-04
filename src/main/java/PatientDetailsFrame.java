import javax.swing.*;

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
    private JPanel BodyTempPanel;
    //For Heart Rate
    private JPanel HeartRatePanel;
    //For Respiratory Rate
    private JPanel RespiratoryRatePanel;
    //For Blood Pressure
    private JPanel BloodPressurePanel;
    //ECG
    private JPanel ECGPanel;

    public PatientDetailsFrame() {
        setContentPane(patientProfilePanel);
        setTitle("Patient's data");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //get patient data and plot in the Panels

    //Creating the main method
    public static void main(String[] args) {
        PatientDetailsFrame patientProfileFrame = new PatientDetailsFrame();
    }
}
//change

/*
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
 */

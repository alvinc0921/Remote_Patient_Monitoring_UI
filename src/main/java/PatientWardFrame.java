import javax.swing.*;

public class PatientWardFrame extends JFrame{
    private JScrollBar scrollBar1;
    private JTextField tfName1;
    private JTextField tfAge1;
    private JTextField tfHeight1;
    private JTextField tfWeight1;
    private JTextField tfBloodType1;
    private JTextField tfHospitalized1;
    private JTextField tfEmergency1;
    private JTextField tfName2;
    private JTextField tfAge2;
    private JTextField tfHeight2;
    private JTextField tfBloodType2;
    private JTextField tfWeight2;
    private JTextField tfHospitalized2;
    private JTextField tfEmergency2;
    private JTextField tfName3;
    private JTextField tfHeight3;
    private JTextField tfWeight3;
    private JTextField tfAge3;
    private JTextField tfBloodType3;
    private JTextField tfHospitalized3;
    private JTextField tfEmergency3;
    private JTextField tfName4;
    private JTextField tfAge4;
    private JTextField tfHeight4;
    private JTextField tfWeight4;
    private JTextField tfBloodType4;
    private JTextField tfHospitalized4;
    private JTextField tfEmergency4;
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    private JButton addPatientButton;
    private JPanel patientWardPanel;

    public PatientWardFrame() {
        setContentPane(patientWardPanel);
        setTitle("Patient Ward");
        //setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        PatientWardFrame patientListFrame = new PatientWardFrame();
    }
}

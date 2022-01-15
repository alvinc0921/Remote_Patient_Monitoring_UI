import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddPatientFrame extends JFrame{
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfEmail;
    private JTextField tfAge;
    private JButton createProfileButton;
    private JButton closeButton;
    private JTextField tfPhoneNumber;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JTextField tfBloodType;
    private JTextField tfHospitalized;
    private JPanel addPatientPanel;

    public AddPatientFrame(ArrayList<Patient> patientList) {
        setContentPane(addPatientPanel);
        setBounds(1200, 0, 1200, 800); //value for windows on the right
        setTitle("Add Patient");
        setSize(1200, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ActionListener addPatient = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close AddPatientFrame
                setVisible(false);

                //create a String list with all the information collected
                List<String> newPatientInformation = new ArrayList<>();
                newPatientInformation.add(tfFirstName.getText());
                newPatientInformation.add(tfLastName.getText());
                newPatientInformation.add(tfEmail.getText());
                newPatientInformation.add(tfPhoneNumber.getText());
                newPatientInformation.add(tfAge.getText());
                newPatientInformation.add(tfHeight.getText());
                newPatientInformation.add(tfWeight.getText());
                newPatientInformation.add(tfBloodType.getText());
                newPatientInformation.add(tfHospitalized.getText());

                //pass the information from the list into the database

                //reopen the Ward instead
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);

                for (int i = 0; i < newPatientInformation.size(); i++) {
                    System.out.println(newPatientInformation.get(i));
                }
            }
        };
        createProfileButton.addActionListener(addPatient);

        ActionListener close = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close AddPatientFrame
                setVisible(false);
                //reopen the Ward instead
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
            }
        };
        closeButton.addActionListener(close);

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
                Reportmenu reportmenu = new Reportmenu(patientList);
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
    }


/*
    public static void main(String[] args) {
        AddPatientFrame newPatientFrame = new AddPatientFrame();
    }
 */
}

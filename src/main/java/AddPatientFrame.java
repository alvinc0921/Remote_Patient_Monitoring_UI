import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddPatientFrame extends JFrame{
    //The main Panel
    private JPanel addPatientPanel;
    //Menu buttons
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    //Fields containing the new patient's details
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfEmail;
    private JTextField tfAge;
    private JTextField tfPhoneNumber;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JTextField tfBloodType;
    private JTextField tfHospitalized;
    //Buttons to add patient to Ward or close window and open Ward without adding pstient
    private JButton createProfileButton;
    private JButton closeButton;

    public AddPatientFrame(ArrayList<Patient> patientList) {
        setContentPane(addPatientPanel);
        setBounds(1200, 0, 1200, 800); //value for windows on the right
        setTitle("Add Patient");
        setSize(1200, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Action listener for the createProfileButton
        ActionListener addPatient = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create a String list with all the information collected
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

                //Pass the information from the list into the database

                //Close AddPatientFrame
                setVisible(false);

                //Reopen the Ward instead
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);

                //Display the new patient's data into the terminal - just for testing purposes
                for (int i = 0; i < newPatientInformation.size(); i++) {
                    System.out.println(newPatientInformation.get(i));
                }
            }
        };
        createProfileButton.addActionListener(addPatient);

        //Action listener for the closeButton
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

        //Action listeners for the buttons in the menu
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
    //Creating the main method
    public static void main(String[] args) {
        AddPatientFrame newPatientFrame = new AddPatientFrame();
    }
 */
}

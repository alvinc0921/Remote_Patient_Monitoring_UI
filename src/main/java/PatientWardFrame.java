import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientWardFrame extends JFrame{
    private JScrollBar scrollBar1;
    private JTextField tfName1;
    private JTextField tfSex1;
    private JTextField tfAge1;
    private JTextField tfHeight1;
    private JTextField tfWeight1;
    private JTextField tfBloodType1;
    private JTextField tfHospitalized1;
    private JTextField tfEmergency1;
    private JTextField tfName2;
    private JTextField tfSex2;
    private JTextField tfAge2;
    private JTextField tfHeight2;
    private JTextField tfBloodType2;
    private JTextField tfWeight2;
    private JTextField tfHospitalized2;
    private JTextField tfEmergency2;
    private JTextField tfName3;
    private JTextField tfSex3;
    private JTextField tfHeight3;
    private JTextField tfWeight3;
    private JTextField tfAge3;
    private JTextField tfBloodType3;
    private JTextField tfHospitalized3;
    private JTextField tfEmergency3;
    private JTextField tfName4;
    private JTextField tfSex4;
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
    private JButton seePatientDetailsButton1;
    private JButton seePatientDetailsButton2;
    private JButton seePatientDetailsButton3;
    private JButton seePatientDetailsButton4;


    public PatientWardFrame(ArrayList<Patient> patientList) {
        setContentPane(patientWardPanel);
        setBounds(1200,0,1200,800); //value for windows on the right
        setTitle("Patient Ward");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        Patient pat1 = patientList.get(0);
        Patient pat2 = patientList.get(0);
        Patient pat3 = patientList.get(0);
        Patient pat4 = patientList.get(0);


        tfName1.setText(pat1.firstname + " " + pat1.lastname);
        tfSex1.setText("f");
        tfAge1.setText(String.valueOf(pat1.age));
        tfHeight1.setText("1.71 m");
        tfWeight1.setText("67 kg");
        tfBloodType1.setText(pat1.bloodType);
        tfHospitalized1.setText("Floor " + pat1.location.get(0) + ", Room " + pat1.location.get(1) + ", Bed " + pat1.location.get(2));
        tfEmergency1.setText("none");

        tfName2.setText(pat2.firstname + " " + pat2.lastname);
        tfSex2.setText("m");
        tfAge2.setText(String.valueOf(pat2.age));
        tfHeight2.setText("1.75 m");
        tfWeight2.setText("71 kg");
        tfBloodType2.setText(pat2.bloodType);
        tfHospitalized2.setText("Floor " + pat2.location.get(0) + ", Room " + pat2.location.get(1) + ", Bed " + pat2.location.get(2));
        tfEmergency2.setText("none");

        tfName3.setText(pat3.firstname + " " + pat3.lastname);
        tfSex3.setText("m");
        tfAge3.setText(String.valueOf(pat3.age));
        tfHeight3.setText("1.68 m");
        tfWeight3.setText("60 kg");
        tfBloodType3.setText(pat3.bloodType);
        tfHospitalized3.setText("Floor " + pat3.location.get(0) + ", Room " + pat3.location.get(1) + ", Bed " + pat3.location.get(2));
        tfEmergency3.setText("none");

        tfName4.setText(pat4.firstname + " " + pat4.lastname);
        tfSex4.setText("f");
        tfAge4.setText(String.valueOf(pat4.age));
        tfHeight4.setText("1.63 m");
        tfWeight4.setText("58 kg");
        tfBloodType4.setText(pat4.bloodType);
        tfHospitalized4.setText("Floor " + pat4.location.get(0) + ", Room " + pat4.location.get(1) + ", Bed " + pat4.location.get(2));
        tfEmergency4.setText("none");

        seePatientDetailsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient1 = new PatientDetailsFrame(patientList, 0);//1. Create the frame.
                setVisible(false);
                framePatient1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient1.setTitle("Patient's data");//3. Set title for new frame
                framePatient1.setSize(1200,800);//4. Size the frame.
                framePatient1.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });

        seePatientDetailsButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient2 = new PatientDetailsFrame(patientList, 1);//1. Create the frame.
                setVisible(false);
                framePatient2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient2.setTitle("Patient's data");//3. Set title for new frame
                framePatient2.setSize(1200,800);//4. Size the frame.
                framePatient2.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });

        seePatientDetailsButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient3 = new PatientDetailsFrame(patientList, 2);//1. Create the frame.
                setVisible(false);
                framePatient3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient3.setTitle("Patient's data");//3. Set title for new frame
                framePatient3.setSize(1200,800);//4. Size the frame.
                framePatient3.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });

        seePatientDetailsButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient4 = new PatientDetailsFrame(patientList, 3);//1. Create the frame.
                setVisible(false);
                framePatient4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient4.setTitle("Patient's data");//3. Set title for new frame
                framePatient4.setSize(1200,800);//4. Size the frame.
                framePatient4.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });

        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set AddPatientFrame visibility to true
                AddPatientFrame addPatient = new AddPatientFrame(patientList);//1. Create the frame.
                setVisible(false);
                addPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                addPatient.setTitle("Add Patient");//3. Set title for new frame
                addPatient.setSize(1200,800);//4. Size the frame.
                addPatient.setVisible(true);//5. Show it.
                //store the patient details somewhere
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

        ActionListener wardAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Reportsubmenu reportsubmenu = new Reportsubmenu(patientList);
                //go_ward_menu();
            }
        };
        reportButton.addActionListener(wardAL);

    }
/*
    public static void main(String[] args) {PatientWardFrame patientListFrame = new PatientWardFrame();}
 */
}

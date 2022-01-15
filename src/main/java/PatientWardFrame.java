import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class PatientWardFrame extends JFrame{
    //The main Panel
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    //Fields containing the patient's details
    private JTextField tfName1;
    private JTextField tfAge1;
    private JTextField tfBloodType1;
    private JTextField tfHospitalized1;
    private JTextField tfEmergency1;
    private JTextField tfName2;
    private JTextField tfAge2;
    private JTextField tfBloodType2;
    private JTextField tfHospitalized2;
    private JTextField tfEmergency2;
    private JTextField tfName3;
    private JTextField tfAge3;
    private JTextField tfBloodType3;
    private JTextField tfHospitalized3;
    private JTextField tfEmergency3;
    private JTextField tfName4;
    private JTextField tfAge4;
    private JTextField tfBloodType4;
    private JTextField tfHospitalized4;
    private JTextField tfEmergency4;
    //private JButton addPatientButton;
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

        //Accessing from the database and displaying the information for each of the patients currently hospitalized
        Patient pat1 = patientList.get(0);
        Patient pat2 = patientList.get(1);
        Patient pat3 = patientList.get(2);
        Patient pat4 = patientList.get(3);

        tfName1.setText(pat1.firstname + " " + pat1.lastname);
        tfAge1.setText(String.valueOf(pat1.age));
        tfBloodType1.setText(pat1.bloodType);
        tfHospitalized1.setText("Floor " + pat1.location.get(0) + ", Room " + pat1.location.get(1) + ", Bed " + pat1.location.get(2));

        TimerTask readStatus1 = new TimerTask() {   // TimerTask to read the alertStatus of patient every second
            @Override
            public void run() {
                tfEmergency1.setText(pat1.alertStatus);
            }
        };
        java.util.Timer emTimer1 = new java.util.Timer();
        emTimer1.schedule(readStatus1, 0, 1000);

        tfName2.setText(pat2.firstname + " " + pat2.lastname);
        tfAge2.setText(String.valueOf(pat2.age));
        tfBloodType2.setText(pat2.bloodType);
        tfHospitalized2.setText("Floor " + pat2.location.get(0) + ", Room " + pat2.location.get(1) + ", Bed " + pat2.location.get(2));

        TimerTask readStatus2 = new TimerTask() {
            @Override
            public void run() {
                tfEmergency2.setText(pat2.alertStatus);
            }
        };
        java.util.Timer emTimer2 = new java.util.Timer();
        emTimer2.schedule(readStatus2, 0, 1000);

        tfName3.setText(pat3.firstname + " " + pat3.lastname);
        tfAge3.setText(String.valueOf(pat3.age));
        tfBloodType3.setText(pat3.bloodType);
        tfHospitalized3.setText("Floor " + pat3.location.get(0) + ", Room " + pat3.location.get(1) + ", Bed " + pat3.location.get(2));
        tfEmergency3.setText("none");

        TimerTask readStatus3 = new TimerTask() {   // TimerTask to read the alertStatus of patient every second
            @Override
            public void run() {
                tfEmergency3.setText(pat3.alertStatus);
            }
        };
        java.util.Timer emTimer3 = new java.util.Timer();
        emTimer3.schedule(readStatus3, 0, 1000);


        tfName4.setText(pat4.firstname + " " + pat4.lastname);
        tfAge4.setText(String.valueOf(pat4.age));
        tfBloodType4.setText(pat4.bloodType);
        tfHospitalized4.setText("Floor " + pat4.location.get(0) + ", Room " + pat4.location.get(1) + ", Bed " + pat4.location.get(2));
        tfEmergency4.setText("none");

        TimerTask readStatus4 = new TimerTask() {   // TimerTask to read the alertStatus of patient every second
            @Override
            public void run() {
                tfEmergency4.setText(pat4.alertStatus);
            }
        };
        java.util.Timer emTimer4 = new java.util.Timer();
        emTimer4.schedule(readStatus4, 0, 1000);


        //Creating ActionListeners for the seePatientDetailsButtons
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
                PatientDetailsFrame framePatient2 = new PatientDetailsFrame(patientList, 2);//1. Create the frame.
                setVisible(false);
                framePatient2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient2.setTitle("Patient's data");//3. Set title for new frame
                framePatient2.setSize(1200,800);//4. Size the frame.
                framePatient2.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });

        seePatientDetailsButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient2 = new PatientDetailsFrame(patientList, 3);//1. Create the frame.
                setVisible(false);
                framePatient2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient2.setTitle("Patient's data");//3. Set title for new frame
                framePatient2.setSize(1200,800);//4. Size the frame.
                framePatient2.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
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
                Reportmenu reportmenu = new Reportmenu(patientList);
                //go_ward_menu();
            }
        };
        reportButton.addActionListener(wardAL);

        /*
        //We implemented an "Add Patient" button that would access the AddPatient class, but gave up on the functionality
        //because we did not have time to understand how the newly inputted values would be added to the database
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
        });*/

    }
/*
    //Creating the main method
    public static void main(String[] args) {PatientWardFrame patientListFrame = new PatientWardFrame();}
 */
}

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class PatientWardFrame extends JFrame{
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
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    //private JButton addPatientButton;
    private JPanel patientWardPanel;
    private JButton seePatientDetailsButton1;
    private JButton seePatientDetailsButton2;


    public PatientWardFrame(ArrayList<Patient> patientList) {
        setContentPane(patientWardPanel);
        setBounds(1200,0,1200,800); //value for windows on the right
        setTitle("Patient Ward");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        Patient pat1 = patientList.get(0);
        Patient pat2 = patientList.get(1);


        tfName1.setText(pat1.firstname + " " + pat1.lastname);
        tfSex1.setText("f");
        tfAge1.setText(String.valueOf(pat1.age));
        tfHeight1.setText("1.71 m");
        tfWeight1.setText("67 kg");
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
        tfSex2.setText("m");
        tfAge2.setText(String.valueOf(pat2.age));
        tfHeight2.setText("1.75 m");
        tfWeight2.setText("71 kg");
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

        /*
        tfName3.setText(pat3.firstname + " " + pat3.lastname);
        tfSex3.setText("m");
        tfAge3.setText(String.valueOf(pat3.age));
        tfHeight3.setText("1.68 m");
        tfWeight3.setText("60 kg");
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
        tfSex4.setText("f");
        tfAge4.setText(String.valueOf(pat4.age));
        tfHeight4.setText("1.63 m");
        tfWeight4.setText("58 kg");
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
    */

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

        /*addPatientButton.addActionListener(new ActionListener() {
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

    }
/*
    public static void main(String[] args) {PatientWardFrame patientListFrame = new PatientWardFrame();}
 */
}

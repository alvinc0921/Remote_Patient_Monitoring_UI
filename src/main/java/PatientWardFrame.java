import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public PatientWardFrame() {
        setContentPane(patientWardPanel);
        setTitle("Patient Ward");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        tfName1.setText("Layla Francene");
        tfSex1.setText("f");
        tfAge1.setText("36");
        tfHeight1.setText("1.71 m");
        tfWeight1.setText("67 kg");
        tfBloodType1.setText("A+");
        tfHospitalized1.setText("Floor 4, Room 43, Bed 7");
        tfEmergency1.setText("none");

        tfName2.setText("Petko Adello");
        tfSex2.setText("m");
        tfAge2.setText("18");
        tfHeight2.setText("1.75 m");
        tfWeight2.setText("71 kg");
        tfBloodType2.setText("AB+");
        tfHospitalized2.setText("Floor 2, Room 21, Bed 18");
        tfEmergency2.setText("none");

        tfName3.setText("Franjo Erna");
        tfSex3.setText("m");
        tfAge3.setText("21");
        tfHeight3.setText("1.68 m");
        tfWeight3.setText("60 kg");
        tfBloodType3.setText("0+");
        tfHospitalized3.setText("Floor 6, Room 67, Bed 24");
        tfEmergency3.setText("none");

        tfName4.setText("Amelia Brandee");
        tfSex4.setText("f");
        tfAge4.setText("43");
        tfHeight4.setText("1.63 m");
        tfWeight4.setText("58 kg");
        tfBloodType4.setText("0-");
        tfHospitalized4.setText("Floor 4, Room 43, Bed 9");
        tfEmergency4.setText("none");

        seePatientDetailsButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientWardFrame visibility to false
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient1 = new PatientDetailsFrame();//1. Create the frame.
                framePatient1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient1.setTitle("Patient's data");//3. Set title for new frame
                framePatient1.setSize(1200,800);//4. Size the frame.
                framePatient1.setVisible(true);//5. Show it.

                //Implement an ActionListener for the JButton that appears together with the PatientDetailsFrame
                /*xButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //close this frame
                        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    }
                });*/

                //set patient details to suit the patient we are interested in
            }
        });

        seePatientDetailsButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //set PatientWardFrame visibility to false
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient2 = new PatientDetailsFrame();//1. Create the frame.
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
                //set PatientWardFrame visibility to false
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient3 = new PatientDetailsFrame();//1. Create the frame.
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
                //set PatientWardFrame visibility to false
                //set PatientDetailsFrame visibility to true
                PatientDetailsFrame framePatient4 = new PatientDetailsFrame();//1. Create the frame.
                framePatient4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                framePatient4.setTitle("Patient's data");//3. Set title for new frame
                framePatient4.setSize(1200,800);//4. Size the frame.
                framePatient4.setVisible(true);//5. Show it.
                //set patient details to suit the patient we are interested in
            }
        });
    }

    public static void main(String[] args) {PatientWardFrame patientListFrame = new PatientWardFrame();}
}

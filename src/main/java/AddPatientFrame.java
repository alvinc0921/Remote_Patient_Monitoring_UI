import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientFrame extends JFrame{
    private JButton emergencyButton;
    private JButton wardButton;
    private JButton reportButton;
    private JScrollBar scrollBar1;
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

    public AddPatientFrame() {
        setContentPane(addPatientPanel);
        setTitle("Add Patient");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close AddPatientFrame
                AddPatientFrame addPatient = new AddPatientFrame();//1. Create the frame.
                addPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2. Optional: What happens when the frame closes?
                addPatient.setTitle("Add Patient");//3. Set title for new frame
                addPatient.setSize(1200,800);//4. Size the frame.
                addPatient.setVisible(false);//5. Hide it.
            }
        });
    }

    public static void main(String[] args) {
        AddPatientFrame newPatientFrame = new AddPatientFrame();
    }
}

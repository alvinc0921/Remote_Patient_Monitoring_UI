import javax.swing.*;

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
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        AddPatientFrame newPatientFrame = new AddPatientFrame();
    }
}

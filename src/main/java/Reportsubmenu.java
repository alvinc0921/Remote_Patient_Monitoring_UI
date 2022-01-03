import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;





public class Reportsubmenu extends JFrame {

    private patient current_pat;

    public Reportsubmenu(){//should be (patient_list after merge)

        JFrame reportsubmenu = new JFrame("patientMed");//frame
        reportsubmenu.setBounds(400,300,1200,800); //values temp.
        JPanel mainPanel = new JPanel();
        JPanel menuselect = new JPanel();
        JPanel patselect = new JPanel();
        JPanel funcselect = new JPanel();
        reportsubmenu.getContentPane().add(mainPanel);
        //reportsubmenu.setLayout(new FlowLayout());
        mainPanel.setLayout(new GridLayout(1,3,100,100));
        //mainPanel.setLayout(new FlowLayout());
        mainPanel.add(menuselect);
        mainPanel.add(patselect);
        mainPanel.add(funcselect);



        JButton alert = new JButton("Alert");//menuselect menu
        JButton ward = new JButton("Ward");
        JButton report = new JButton("Report");
        //menuselect.setLayout(new FlowLayout());
        menuselect.setLayout(new GridLayout(3,1,200,200));
        menuselect.add(alert);
        menuselect.add(ward);
        menuselect.add(report);

        ActionListener alertAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportsubmenu.setVisible(false);
                EmergencyUIController emUIController = new EmergencyUIController(patientList);
            }
        };
        alert.addActionListener(alertAL);

        ActionListener wardAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportsubmenu.setVisible(false);
                //go_ward_menu();
            }
        };
        ward.addActionListener(wardAL);

        ActionListener reportAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("where the hell u wanna go man?");
            }
        };
        report.addActionListener(reportAL);



        JList patlist = new JList();
        DefaultListModel patlistmodel = new DefaultListModel();
        patlist.setModel(patlistmodel);
        ArrayList<patient> patient_list = new ArrayList<patient>();
        //patient list (array of patient should be fed when call this function so to be changed when merge
        for (patient pat:patient_list){
            patlistmodel.addElement(pat.get());//the name of the patient object should be the name of the patient
        }
        patselect.setLayout(new FlowLayout());
        patselect.add(patlist);
        patlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                current_pat = (patient) patlist.getSelectedValue();
            }
        });



        JButton genereport = new JButton("Generate Report");
        JButton seepastreport = new JButton("See past reports");
        //funcselect.setLayout(new FlowLayout());
        funcselect.setLayout(new GridLayout(2,1,250,200));
        funcselect.add(genereport);
        funcselect.add(seepastreport);

        ActionListener generptAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generate_report(current_pat);
            }
        };
        report.addActionListener(generptAL);

        ActionListener seepastreportAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seepast_report(current_pat);
            }
        };
        report.addActionListener(seepastreportAL);



        reportsubmenu.setVisible(true);
        reportsubmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void generate_report(patient current_patient){
        //to be written when details of the contents of the reports are decided
        //also a class Reports maybe added

    }

    public void seepast_report(patient current_pat){
        //to be written later, same as above
    }
}
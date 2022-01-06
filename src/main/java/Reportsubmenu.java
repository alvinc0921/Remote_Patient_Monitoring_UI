import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class Reportsubmenu extends JFrame {

    private String current_pat_name;
    private Patient current_pat;

    public Reportsubmenu(ArrayList<Patient> patientList){//should be (patient_list after merge)

        JFrame reportsubmenu = new JFrame("patientMed");//frame
        reportsubmenu.setBounds(0,0,1200,800); //values temp.
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





        JButton alert = new JButton("Emergency");//menuselect menu
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
                //EmergencyUIController emUIController = new EmergencyUIController(patientList);
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
        //ArrayList<Patient> patient_list = new ArrayList<Patient>();
        //patient list (array of patient should be fed when call this function so to be changed when merge
        for (Patient pat:patientList){
            patlistmodel.addElement(pat.firstname + " " + pat.lastname);//the name of the patient object should be the name of the patient
        }
        patselect.setLayout(new FlowLayout());
        patselect.add(patlist);
        patlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                current_pat_name = (String) patlist.getSelectedValue();
                for (Patient pat2:patientList){
                    if ((pat2.firstname + " " + pat2.lastname) == current_pat_name){
                        current_pat = pat2;
                    }
                }
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
        genereport.addActionListener(generptAL);



        ActionListener seepastreportAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seepast_report(current_pat);
            }
        };
        seepastreport.addActionListener(seepastreportAL);



        reportsubmenu.setVisible(true);
        reportsubmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void generate_report(Patient current_pat){
        //Date?
        // Change the file_path below to change your desired directory
        String file_path = "/Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt"; //now only obe file is created
        //String content;
        //Path path = Paths.get(file_path);
        FileWriter fw = null;
        try{
            File file = new File(file_path);
            if(!file.exists()){
                file.createNewFile();
            }

            fw = new FileWriter(file_path);
            fw.write("Status Report for " + current_pat.firstname + " " + current_pat.lastname + " on date DD/MM/YYYY"+"\r\n");
            fw.write("\r\n");
            fw.write("Patient name:" + current_pat.firstname + " " + current_pat.lastname + "\r\n");
            fw.write("Patient location:" + current_pat.patLoc + "\r\n");
            fw.write("Patient's current status:" + current_pat.alertStatus + "\r\n");
            fw.write("Patient alert history:" + "\r\n");
            System.out.println(current_pat.alertHistoryTemp.get(0));
            int hisleng = current_pat.alertHistoryTemp.size();
            for (int i=0; i < hisleng; i++){
                fw.write(current_pat.alertHistoryTemp.get(i) + "\r\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                fw.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        try{
            // The following command is different between Mac and Window
            Process process = Runtime.getRuntime().exec("open -a TextEdit /Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void seepast_report(Patient current_pat){//now only have one file and one dir, can be added later
        try{
            // The following command is different between Mac and Window
            Process process = Runtime.getRuntime().exec("open -a TextEdit /Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
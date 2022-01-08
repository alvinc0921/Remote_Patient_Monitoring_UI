import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Reportsubmenu extends JFrame {

    private String current_pat_name;
    private Patient current_pat;
    private int current_pat_num = 0;

    public Reportsubmenu(ArrayList<Patient> patientList){//should be (patient_list after merge)

        JFrame reportsubmenu = new JFrame("patientMed");//frame
        reportsubmenu.setBounds(1200,0,1200,800); //value for windows on the right
        JPanel mainPanel = new JPanel();
        JPanel menuselect = new JPanel();
        JPanel patselect = new JPanel();
        JPanel funcselect = new JPanel();
        reportsubmenu.getContentPane().add(mainPanel);
        //reportsubmenu.setLayout(new FlowLayout());
        mainPanel.setLayout(new GridLayout(1,3,200,100));
        //mainPanel.setLayout(new FlowLayout());
        mainPanel.add(menuselect);
        mainPanel.add(patselect);
        mainPanel.add(funcselect);



        JLabel label = new JLabel("PadMed");
        JButton alert = new JButton("Emergency");//menuselect menu
        alert.setPreferredSize(new Dimension(25,5));
        JButton ward = new JButton("Ward");
        ward.setPreferredSize(new Dimension(30,5));
        JButton report = new JButton("Report");
        report.setPreferredSize(new Dimension(35,5));
        //menuselect.setLayout(new FlowLayout());
        menuselect.setLayout(new GridLayout(5,1,200,90));
        menuselect.add(label);
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
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
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
        JLabel patlabel = new JLabel("Please select a patient above");
        DefaultListModel patlistmodel = new DefaultListModel();
        patlist.setModel(patlistmodel);
        //ArrayList<Patient> patient_list = new ArrayList<Patient>();
        //patient list (array of patient should be fed when call this function so to be changed when merge
        for (Patient pat:patientList){
            patlistmodel.addElement(pat.firstname +" "+ pat.lastname);//the name of the patient object should be the name of the patient
        }
        patselect.setLayout(new GridLayout(2,1,100,200));
        patselect.add(patlist);
        patselect.add(patlabel);
        patlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
/*
                current_pat_name = (String) patlist.getSelectedValue();
                for (Patient pat2:patientList){
                    if ((pat2.firstname + " " + pat2.lastname) == current_pat_name){
                        current_pat = pat2;
                    }
                }
*/
                //current_pat_name = (String) patlist.getSelectedValue();
                current_pat_num = patlist.getSelectedIndex();
                current_pat = patientList.get(current_pat_num);
                //for (Patient pat2:patientList){
                    //if ((pat2.firstname + " " + pat2.lastname) == current_pat_name){
                        // = pat2;
                    //}
                //}
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
        reportsubmenu.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static void generate_report(Patient current_pat){
        //Date?
        // Change the file_path below to change your desired directory
        //String file_path = "/Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt"; //now only obe file is created
        //String file_path = "D:\\RPM_test\\PatMed.txt";
        //String content;
        //Path path = Paths.get(file_path);
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";
        System.out.println(file_path);
        FileWriter fw = null;
        for(int i=0;i<3;i++) {
            if(i==2) file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID();
            if(i==1) file_path=file_path + System.getProperty("file.separator") + "Reports";
            try {
                Files.createDirectories(Paths.get(file_path));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String current_time = sdf.format(date);


        file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID() + "_" + current_time + ".txt";
        File file = new File(file_path);

        try{
            if(!file.exists()){
                file.createNewFile();
            }

            fw = new FileWriter(file_path);
            fw.write("Status Report for " + current_pat.firstname + " " + current_pat.lastname + " " + current_time);
            fw.write("\r\n");
            fw.write("Patient name: " + current_pat.firstname + " " + current_pat.lastname + "\r\n");
            fw.write("Patient ID: " + current_pat.patID + "\r\n");
            fw.write("Patient age: " + current_pat.age + "\r\n");
            fw.write("Patient blood type: " + current_pat.bloodtype + "\r\n");
            int localength = current_pat.location.size();
            fw.write("Patient location: Floor " + current_pat.location.get(0) + ", Room " + current_pat.location.get(1) + ", Bed " + current_pat.location.get(2));


            fw.write("\r\n"+"Patient current status: " + current_pat.alertStatus + "\r\n");

            BigDecimal sum = new BigDecimal("0");
            int tempdataleng = current_pat.tempSig.size();
            BigDecimal tempdatalength = new BigDecimal(tempdataleng);
            for(int a = 0; a < tempdataleng; a++){
                sum = sum.add(current_pat.tempSig.get(a));
            }
            BigDecimal tempavg = sum.divide(tempdatalength,2, RoundingMode.HALF_UP);
            fw.write("\r\n" + "Patient average temperature over last 24h: " + tempavg + "\r\n");
            fw.write("Patient temperature alert history:" + "\r\n");
            int templeng = current_pat.alertHistoryTemp.size();
            if (templeng == 0){
                fw.write("N/A"+"\r\n");
            }
            for (int i=0; i < templeng; i++){
                fw.write(current_pat.alertHistoryTemp.get(i) + "\r\n");
            }

            sum.equals(0);
            int hrdataleng = current_pat.hrSig.size();
            BigDecimal hrdatalength = new BigDecimal(hrdataleng);
            for(int b = 0; b < hrdataleng; b++){
                sum = sum.add(current_pat.hrSig.get(b));
            }
            BigDecimal hravg = sum.divide(hrdatalength,2,RoundingMode.HALF_UP);
            fw.write("\r\n" + "Patient average heart rate over last 24h: " + hravg + "\r\n");
            fw.write("Patient heart rate alert history:" + "\r\n");
            int HRleng = current_pat.alertHistoryHR.size();
            if (HRleng == 0){
                fw.write("N/A"+"\r\n");
            }
            for (int j=0; j < HRleng; j++){
                fw.write(current_pat.alertHistoryHR.get(j) + "\r\n");
            }

            sum.equals(0);
            int rrdataleng = current_pat.rrSig.size();
            BigDecimal rrdatalength = new BigDecimal(rrdataleng);
            for(int c = 0; c < rrdataleng; c++){
                sum = sum.add(current_pat.rrSig.get(c));
            }
            BigDecimal rravg = sum.divide(rrdatalength,2,RoundingMode.HALF_UP);
            fw.write("\r\n" + "Patient average respiratory rate over last 24h: " + rravg + "\r\n");
            fw.write("Patient respiratory rate alert history:" + "\r\n");
            int RRleng = current_pat.alertHistoryRR.size();
            if (RRleng == 0){
                fw.write("N/A"+"\r\n");
            }
            for (int k=0; k < RRleng; k++){
                fw.write(current_pat.alertHistoryRR.get(k) + "\r\n");
            }

            /*sum.equals(0);
            int ecgdataleng = current_pat.ecgSig.size();
            BigDecimal ecgdatalength = new BigDecimal(ecgdataleng);
            for(int c = 0; c < ecgdataleng; c++){
                sum = sum.add(current_pat.ecgSig.get(c));
            }
            BigDecimal ecgavg = sum.divide(ecgdatalength);
            fw.write("\r\n" + "Patient average ECG value over last 24h: " + ecgavg + "\r\n");
            fw.write("Patient ECG alert history:" + "\r\n");
            int RRleng = current_pat.alertHistoryRR.size();
            if (RRleng == 0){
                fw.write("N/A"+"\r\n");
            }
            for (int k=0; k < RRleng; k++){
                fw.write(current_pat.alertHistoryRR.get(k) + "\r\n");
            }
            */




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
            //Process process = Runtime.getRuntime().exec("open -a TextEdit /Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt");
            //Process process = Runtime.getRuntime().exec("notepad " + file_path);
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()){
                desktop.open(file);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void seepast_report(Patient current_pat){//now only have one file and one dir, can be added later
        try{
            // The following command is different between Mac and Window
            //Process process = Runtime.getRuntime().exec("open -a TextEdit /Users/chengdorothy/Documents/Prg3/FinalProject/PatMed.txt");
            //Process process = Runtime.getRuntime().exec("notepad D:/RPM_test/PatMed.txt");
            String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";
            System.out.println(file_path);
            FileWriter fw = null;
            for(int i=0;i<3;i++) {
                if(i==2) file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID();
                if(i==1) file_path = file_path + System.getProperty("file.separator") + "Reports";
            }



            //file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID() + "_" + current_time + ".txt";
            File file = new File(file_path);
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()){
                desktop.open(file);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
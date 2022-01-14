import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reportmenu extends JFrame{
    private JPanel menuselect;
    private JButton emergencyBut;
    private JButton reportBut;
    private JButton wardBut;
    private JPanel contentPanel;
    private JLabel patientlistLabel;
    private JLabel functionLabel;
    private JPanel patientselectPanel;
    private JList patList;
    private JPanel functionsPanel;
    private JButton generatereport;
    private JButton seepastreport;
    private JPanel big;
    private String current_pat_name;
    private Patient current_pat;
    private int current_pat_num = 0;

    public Reportmenu (ArrayList<Patient> patientList){//when the reportmenu function is called, the arraylist of patients are fed and report submenu winsow is opened

        ActionListener alertAL = new ActionListener() {//when the Emergency button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);//the report submenu is hid and the emergency window is shown again
            }
        };
        emergencyBut.addActionListener(alertAL);

        ActionListener wardAL = new ActionListener() {//same for ward button
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PatientWardFrame patientWardFrame = new PatientWardFrame(patientList);
                //go_ward_menu();
            }
        };
        wardBut.addActionListener(wardAL);

        DefaultListModel patlistmodel = new DefaultListModel();//set the modal of the Jlist
        patList.setModel(patlistmodel);
        for (Patient pat:patientList){//for the whole patient list:
            patlistmodel.addElement(pat.firstname +" "+ pat.lastname);//Each element on the Jlist is name of the patient
        }

        patList.addListSelectionListener(new ListSelectionListener() {//when a patient is selected
            @Override
            public void valueChanged(ListSelectionEvent e) {
                current_pat_num = patList.getSelectedIndex();//The number of element chosen in the Jlist is returned and used to identify current patient
                current_pat = patientList.get(current_pat_num);
            }
        });

        ActionListener generptAL = new ActionListener() {//when generate report button is pressed, a report of the current patient is generated
            @Override
            public void actionPerformed(ActionEvent e) {
                generate_report(current_pat);
            }
        };
        generatereport.addActionListener(generptAL);

        ActionListener seepastreportAL = new ActionListener() {//when see pat report button is pressed, report folder of the current patient is opened
            @Override
            public void actionPerformed(ActionEvent e) {
                seepast_report(current_pat);
            }
        };
        seepastreport.addActionListener(seepastreportAL);

        setContentPane(big);                            //Set basic properties of the main frame
        setBounds(1200,0,1200,800); //value for windows on the right
        setTitle("PatientMed");
        setSize(1200, 800);
        setDefaultCloseOperation(HIDE_ON_CLOSE);        // The app is closed when the emergencyUI is closed
        setVisible(true);
    }

    public static void generate_report(Patient current_pat){
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";//generate file directory in the user/RPM_DATA folder
        System.out.println(file_path);
        FileWriter fw = null;//set file writer
        for(int i=0;i<3;i++) {//In the PRM_DATA folder, generate file directory of the current patient:
            if(i==2) file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID();
            if(i==1) file_path=file_path + System.getProperty("file.separator") + "Reports";
            try {
                Files.createDirectories(Paths.get(file_path));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }

        Calendar calendar = Calendar.getInstance();//get time (date)
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String current_time = sdf.format(date);


        file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID() + "_" + current_time + ".txt";//create file with patient name and date as file name
        File file = new File(file_path);

        try{
            if(!file.exists()){//check is the file is already created
                file.createNewFile();
            }

            fw = new FileWriter(file_path);
            //Writing the report according to the info included in the patient class:
            fw.write("Status Report for " + current_pat.firstname + " " + current_pat.lastname + " " + current_time);
            fw.write("\r\n");
            fw.write("Patient name: " + current_pat.firstname + " " + current_pat.lastname + "\r\n");
            fw.write("Patient ID: " + current_pat.patID + "\r\n");
            fw.write("Patient age: " + current_pat.age + "\r\n");
            fw.write("Patient blood type: " + current_pat.bloodType + "\r\n");
            int localength = current_pat.location.size();
            fw.write("Patient location: Floor " + current_pat.location.get(0) + ", Room " + current_pat.location.get(1) + ", Bed " + current_pat.location.get(2));

            //Writing the patient health details by the info included in the patient class:
            //including mean values and past alert histories
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
            Desktop desktop = Desktop.getDesktop();//get desktop properties
            if (file.exists()){
                desktop.open(file);//open the file created
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void seepast_report(Patient current_pat){
        try{
            String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";//Again create the directory
            System.out.println(file_path);
            FileWriter fw = null;
            for(int i=0;i<3;i++) {
                if(i==2) file_path = file_path + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID();
                if(i==1) file_path = file_path + System.getProperty("file.separator") + "Reports";//this directory only leads to the reports folder where each patient has a seperate subfolder containing their past reports
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

import java.io.File;
/* THIS CLASS IS USED TO CREATE A RECORD OF AVERAGES OF THE LAST 24H DATA FOR EACH PATIENT*/
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

public class Records {
    public static void recordwriter(Patient patient) throws IOException { //METHOD THAT WILL WRITE THE RECORD
        List<BigDecimal> temp24h = new ArrayList<>(); //GETS THE 24H SIGNALS FROM THE PATIENT
        temp24h = patient.getTempSig();
        List<BigDecimal> hr24h = new ArrayList<>();
        hr24h = patient.getHrSig();
        List<BigDecimal> rr24h = new ArrayList<>();
        rr24h = patient.getRrSig();
        ArrayList<String> tempalerts = new ArrayList<String>(); //GETS THE ALERT HISTORY FOR THE SIGNALS OF THE PATIENT
        tempalerts = patient.alertHistoryTemp;
        //tempalerts.add("Warning Start: 2021-12-29T11:58:52.24722>, Warning ends: 2021-12-29T11:58:57.235198Z");
        //tempalerts.add("Urgent starts: 2021-12-29T11:58:57.235198Z, Urgent ends: 2021-12-29T11:58:59.236708Z");
        ArrayList<String> hralerts = new ArrayList<String>();
        hralerts = patient.alertHistoryHR;
        //hralerts.add("Warning Start: 2021-12-29T11:58:52.24722>, Warning ends: 2021-12-29T11:58:57.235198Z");
        //hralerts.add("Urgent starts: 2021-12-29T11:58:57.235198Z, Urgent ends:2021-12-29T11:58:59.236708Z");
        ArrayList<String> rralerts = new ArrayList<String>();
        rralerts = patient.alertHistoryRR;
        //rralerts.add("Warning Start: 2021-12-29T11:58:52.24722>, Warning ends: 2021-12-29T11:58:57.235198Z");
        //rralerts.add("Urgent starts: 2021-12-29T11:58:57.235198Z, Urgent ends:2021-12-29T11:58:59.236708Z");
        ArrayList<Double> tempavg = new ArrayList<Double>(); //THIS IS WHERE THE AVERAGES WILL BE KEPT FOR EACH MINUTE
        ArrayList<Double> hravg = new ArrayList<Double>();
        ArrayList<Double> rravg = new ArrayList<Double>();
        int i;
        double temporary_avg = 0;
        for (i = 0; i < temp24h.size(); i++) { //CREATES THE AVERAGES FOR EACH SIGNAL BY TAKING FIRST 60 SAMPLES AND DOING A MEAN OF THAT UNTIL THE SIGNAL HAS BEEN FULLY READ
            if (i % 60 + 1 == 1) {
                tempavg.add(temporary_avg / 60);
                temporary_avg = 0;
            }
            temporary_avg = temporary_avg + temp24h.get(i).doubleValue();
            if (i == temp24h.size() - 1 && i % 60 != 0) {
                tempavg.add(temporary_avg / (i % 60));
                temporary_avg = 0;
            }
        }
        for (i = 0; i < hr24h.size(); i++) {
            if (i % 60 + 1 == 1) {
                hravg.add(temporary_avg / 60);
                temporary_avg = 0;
            }
            temporary_avg = temporary_avg + hr24h.get(i).doubleValue();
            if (i == hr24h.size() - 1 && i % 60 != 0) {
                hravg.add(temporary_avg / (i % 60));
                temporary_avg = 0;
            }
        }
        for (i = 0; i < rr24h.size(); i++) {
            if (i % 60 + 1 == 1) {
                rravg.add(temporary_avg / 60);
                temporary_avg = 0;
            }
            temporary_avg = temporary_avg + rr24h.get(i).doubleValue();
            if (i == rr24h.size() - 1 && i % 60 != 0) {
                rravg.add(temporary_avg / (i % 60));
                temporary_avg = 0;
            }
        }
        //FOLLOWING PART IS FOR CREATING/FINDING DIRECTORY
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";
        System.out.println(file_path);
        FileWriter fw = null;
        for (i = 0; i < 3; i++) {
            if (i == 2)
                file_path = file_path + System.getProperty("file.separator") + patient.getLastname() + "_" + patient.getFirstname() + "_" + patient.getPatID();
            if (i == 1) file_path = file_path + System.getProperty("file.separator") + "PAST_RECORDS";
            try {
                Files.createDirectories(Paths.get(file_path));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        file_path = file_path + System.getProperty("file.separator") + dtf.format(now) + ".csv";
        fw = new FileWriter(file_path);
        String line = "Time elapsed,Heart Rate,Body Temperature,Respiratory Rate,,Time of alert BT,AlertBT,,Time of alert HR,Alert HR,,Time of alert RR, Alert RR\n";
        fw.write(line);
        String zero, zero2;
        int nexti;
        String alert_typeT = "";
        String alert_typeH = "";
        String alert_typeR = "";
        String[] alert_timeT = new String[2];
        String[] alert_timeH = new String[2];
        String[] alert_timeR = new String[2];
        for (i = 0; i < hravg.size(); i++) {
            if (i < 10) zero = "0";
            else zero = "";
            if (i < 9) zero2 = "0";
            else zero2 = "";
            nexti = i + 1;
            if (i < tempalerts.size()) {
                alert_typeT = tempalerts.get(i).split(":", 2)[0].split(" ", 2)[0];
                alert_timeT[0] = tempalerts.get(i).split("T", 2)[1].split("\\.", 2)[0];
                if (tempalerts.get(i).contains(","))
                    alert_timeT[1] = tempalerts.get(i).split("T", 3)[2].split("\\.", 2)[0];
            }
            if (i < hralerts.size()) {
                alert_typeH = hralerts.get(i).split(":", 2)[0].split(" ", 2)[0];
                alert_timeH[0] = hralerts.get(i).split("T", 2)[1].split("\\.", 2)[0];
                if (hralerts.get(i).contains(",")) alert_timeH[1] = hralerts.get(i).split("T", 3)[2].split("\\.", 2)[0];
            }
            if (i < rralerts.size()) {
                alert_typeR = rralerts.get(i).split(":", 2)[0].split(" ", 2)[0];
                alert_timeR[0] = rralerts.get(i).split("T", 2)[1].split("\\.", 2)[0];
                if (rralerts.get(i).contains(",")) alert_timeR[1] = rralerts.get(i).split("T", 3)[2].split("\\.", 2)[0];
            }
            line = zero + i + ":00 - " + zero2 + nexti + ":00," + hravg.get(i) + "," + tempavg.get(i) + "," + rravg.get(i) + ",," + alert_timeT[0] + " - " + alert_timeT[1] + "," + alert_typeT + ",," + alert_timeH[0] + " - " + alert_timeH[1] + "," + alert_typeH + ",," + alert_timeR[0] + " - " + alert_timeR[1] + "," + alert_typeR + "\n";
            fw.write(line);
            alert_typeT = alert_typeH = alert_typeR = "";
            Arrays.fill(alert_timeH, null);
            Arrays.fill(alert_timeR, null);
            Arrays.fill(alert_timeT, null);
        }
        fw.close();
    }
}

/*    public static void main(String[] args) throws IOException {
        DBConnection db1 = new DBConnection();
        db1.connectToDB();
        BigDecimal bd = new BigDecimal("12345");
        BigDecimal bd2 = new BigDecimal("0.0001");
        List<BigDecimal> l1 = new ArrayList<>();
        List<BigDecimal> l2 = new ArrayList<>();
        int i=0;
        for(i=0;i<15;i++) l1.add(bd);
        for(i=0;i<15;i++) l2.add(bd2);
        Patient patient1 = new Patient(12334,"Nicolas","Calvo",19,"A+",l1,l2,l1,l2,l1,l2);
        Records record = new Records();
        record.recordwriter(patient1);
    }
}*/

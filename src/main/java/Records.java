import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
public class Records {
    public void recordwriter(Patient patient) throws IOException {
        List<BigDecimal> temp24h = new ArrayList<>();
        temp24h = patient.getTempSig();
        List<BigDecimal> hr24h = new ArrayList<>();
        hr24h = patient.getHrSig();
        List<BigDecimal> rr24h = new ArrayList<>();
        rr24h = patient.getRrSig();
        ArrayList<String> tempalerts = new ArrayList<String>();
        tempalerts = patient.getAlertHistoryTemp();
        ArrayList<String> hralerts = new ArrayList<String>();
        hralerts = patient.getAlertHistoryHR();
        ArrayList<String> rralerts = new ArrayList<String>();
        hralerts = patient.getAlertHistoryRR();
        ArrayList<Double> tempavg = new ArrayList<Double>();
        ArrayList<Double> hravg = new ArrayList<Double>();
        ArrayList<Double> rravg = new ArrayList<Double>();
        int i;
        double temporary_avg=0;
        for(i=0;i < temp24h.size();i++){
            if(i%60+1==1){
                tempavg.add(temporary_avg/60);
                temporary_avg=0;
            }
            temporary_avg=temporary_avg+temp24h.get(i).doubleValue();
            if(i==temp24h.size()-1 && i%60!=0) {
                tempavg.add(temporary_avg/(i%60));
                temporary_avg=0;
            }
        }
        for(i=0;i < hr24h.size();i++){
            if(i%60+1==1){
                hravg.add(temporary_avg/60);
                temporary_avg=0;
            }
            temporary_avg=temporary_avg+hr24h.get(i).doubleValue();
            if(i==hr24h.size()-1 && i%60!=0) {
                hravg.add(temporary_avg/(i%60));
                temporary_avg=0;
            }
        }
        for(i=0;i < rr24h.size();i++){
            if(i%60+1==1){
                rravg.add(temporary_avg/60);
                temporary_avg=0;
            }
            temporary_avg=temporary_avg+rr24h.get(i).doubleValue();
            if(i==rr24h.size()-1 && i%60!=0) {
                rravg.add(temporary_avg/(i%60));
                temporary_avg=0;
            }
        }
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA";
        System.out.println(file_path);
        FileWriter fw = null;
        for(i=0;i<3;i++) {
            if(i==2) file_path = file_path + System.getProperty("file.separator") + patient.getLastname() + "_" + patient.getFirstname() + "_" + patient.getPatID();
            if(i==1) file_path=file_path + System.getProperty("file.separator") + "PAST_RECORDS";
            try {
                Files.createDirectories(Paths.get(file_path));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        file_path=file_path + System.getProperty("file.separator") + dtf.format(now) + ".csv";
        fw = new FileWriter(file_path);
        String line="time_elapsed,signal1,signal2,signal3,,time_of_alert,alert\n";
        fw.write(line);
        String zero, zero2;
        int nexti;
        for(i=0;i<patient.getHrSig().size();i++){
            if(i<10) zero="0";
            else zero="";
            if(i<9) zero2="0";
            else zero2="";
            nexti=i+1;
            line=zero + i+ ":00 - " + zero2 + nexti + ":00," + patient.getHrSig().get(i)+ "," +patient.getTempSig().get(i)+ "," +patient.getRrSig().get(i)+ "," + "\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
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
}

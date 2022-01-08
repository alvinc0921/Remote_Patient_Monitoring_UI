// Where everything runs here


import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import com.google.gson.Gson;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws SQLException{
        Records daily_record = new Records();
        //CODE ADDED TO IMPLEMENT DATABASE

        //String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

        //DB CONNECTION PART
        DBConnection herokuconn = new DBConnection();
        Connection conn = herokuconn.connectToDB();

        //END OF DB CONNECTION PART
        // Making an array for the temp, hr and rr
      
      /*
        List<BigDecimal> pat1_temp = new ArrayList<>();
        List<BigDecimal> pat1_ecg = new ArrayList<>();
        List<BigDecimal> pat1_hr = new ArrayList<>();
        List<BigDecimal> pat1_bp = new ArrayList<>();
        List<BigDecimal> pat1_rr = new ArrayList<>();

        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(35.8));    // Low temperature
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(37));
        pat1_temp.add(new BigDecimal(37));

        pat1_hr.add(new BigDecimal(100));
        pat1_hr.add(new BigDecimal(100));
        pat1_hr.add(new BigDecimal(114));       // High hr
        pat1_hr.add(new BigDecimal(114));
        pat1_hr.add(new BigDecimal(140));       // very high hr
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));

        pat1_rr.add(new BigDecimal(22));        // high rr
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));

        pat1_ecg = pat1_temp;
        pat1_bp = pat1_temp;

        List<BigDecimal> pat2_temp = new ArrayList<>();
        List<BigDecimal> pat2_ecg = new ArrayList<>();
        List<BigDecimal> pat2_hr = new ArrayList<>();
        List<BigDecimal> pat2_bp = new ArrayList<>();
        List<BigDecimal> pat2_rr = new ArrayList<>();

        pat2_temp.add(new BigDecimal(35.8));
        pat2_temp.add(new BigDecimal(35.8));
        pat2_temp.add(new BigDecimal(36.0));
        pat2_temp.add(new BigDecimal(36.0));
        pat2_temp.add(new BigDecimal(35.8));    // Low temperature
        pat2_temp.add(new BigDecimal(35.8));
        pat2_temp.add(new BigDecimal(37));
        pat2_temp.add(new BigDecimal(37));

        pat2_hr.add(new BigDecimal(100));
        pat2_hr.add(new BigDecimal(100));
        pat2_hr.add(new BigDecimal(100));       // High hr
        pat2_hr.add(new BigDecimal(100));
        pat2_hr.add(new BigDecimal(100));       // very high hr
        pat2_hr.add(new BigDecimal(120));
        pat2_hr.add(new BigDecimal(120));       // very high hr
        pat2_hr.add(new BigDecimal(120));

        pat2_rr.add(new BigDecimal(22));        // high rr
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(22));
        pat2_rr.add(new BigDecimal(18));

        pat2_ecg = pat1_temp;
        pat2_bp = pat1_temp;

        int pat1ID = 1;
        int pat2ID = 2;
        int pat3ID = 3;

        String pat1_bloodtype = "A";
        String pat2_bloodtype = "B";
        String pat3_bloodtype = "O";

        List<BigDecimal> pat1_location = new ArrayList<>();
        pat1_location.add(new BigDecimal(3));       // Floor
        pat1_location.add(new BigDecimal(50));      // Room
        pat1_location.add(new BigDecimal(2));       // Bed

        List<BigDecimal> pat2_location = new ArrayList<>();
        pat2_location.add(new BigDecimal(6));       // Floor
        pat2_location.add(new BigDecimal(44));      // Room
        pat2_location.add(new BigDecimal(8));       // Bed

        // instantiate the patient list here:
        Patient pat1 = new Patient(pat1ID, "Amy", "Smith", 65, pat1_bloodtype, pat1_location, pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);
        Patient pat2 = new Patient(pat1ID, "Bob", "Bills", 80, pat2_bloodtype, pat2_location, pat2_ecg, pat2_bp, pat2_hr, pat2_rr, pat2_temp);
        //Patient pat3 = new Patient(pat3ID, "Carina", "Smith", 40, pat3_bloodtype, pat3_ecg, pat3_bp, pat3_hr, pat3_rr, pat3_temp);

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        patientList.add(pat2);
        //patientList.add(pat3);
        
        */

        int pat1id=1; //dummy values
        String pat1firstname="Nettles"; //dummy values, in honour
        String pat1lastname="Holloway"; //dummy values, in honour
        int pat1age=7;
        String pat1bloodtype="O+";
        List<BigDecimal> pat1_loc_list = new ArrayList<>();
        List<BigDecimal> pat1_ecg_list = new ArrayList<>();
        List<BigDecimal> pat1_bp_list = new ArrayList<>();
        List<BigDecimal> pat1_hr_list = new ArrayList<>();
        List<BigDecimal> pat1_rr_list = new ArrayList<>();
        List<BigDecimal> pat1_temp_list= new ArrayList<>();

        try {
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT id,firstname,lastname,age,bloodtype,location,heartrate,respiratoryrate,temperature FROM vitalsigns where id=1;";
            ResultSet resset=s2.executeQuery(sqlStr);
            while(resset.next()){
                pat1id=resset.getInt("id");
                pat1firstname=resset.getString("firstname");
                pat1lastname=resset.getString("lastname");
                pat1age=resset.getInt("age");
                pat1bloodtype=resset.getString("bloodtype");
                Array pat1_loc=resset.getArray("location");
                Array pat1_hr=resset.getArray("heartrate");
                Array pat1_rr=resset.getArray("respiratoryrate");
                Array pat1_temp=resset.getArray("temperature");

              // CODE FROM  https://stackoverflow.com/questions/23277777/java-sql-array-to-arrayliststring-oraclecallablestatement

                //location list
                for (Object obj : (Object[])pat1_loc.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_loc_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }


                //heartrate list
                for (Object obj : (Object[])pat1_hr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_hr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //respiratory rate list
                for (Object obj : (Object[])pat1_rr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_rr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
              
                //temperature list
                for (Object obj : (Object[])pat1_temp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_temp_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

            }

            resset.close();
            s2.close();
            //conn.close();
        }
        catch (Exception e){
            System.out.println("I'M IN CATCH");
            System.out.println(e.getMessage());
        }

        try {  //CODE TO GET ECG AND BP, ONE AT A TIME NEEDED
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT ecg FROM vitalsigns where id=1;";
            ResultSet resset=s2.executeQuery(sqlStr);
            Statement s3=conn.createStatement();
            String sqlStr3= "SELECT bloodpressure FROM vitalsigns where id=1;";
            ResultSet resset3=s3.executeQuery(sqlStr3);
            while(resset.next()){
                Array pat1_ecg=resset.getArray("ecg");
                for (Object obj : (Object[])pat1_ecg.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_ecg_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
                // converting to double[] THIS WAS WRONG,
                //double[] pat1_ecgArray = (double[])pat1_ecg.getArray();
            }
            while(resset3.next()){
                Array pat1_bp=resset3.getArray("bloodpressure");
                for (Object obj : (Object[])pat1_bp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat1_bp_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
            }
            resset.close();
            s2.close();
            //conn.close();
        }
        catch (Exception e){
            System.out.println("I'M IN 2nd CATCH");
        }


        int pat2id=2; //dummy values
        String pat2firstname="Nettles"; //dummy values, in honour
        String pat2lastname="Holloway"; //dummy values, in honour
        int pat2age=7;
        String pat2bloodtype="O+";
        List<BigDecimal> pat2_loc_list = new ArrayList<>();
        List<BigDecimal> pat2_ecg_list = new ArrayList<>();
        List<BigDecimal> pat2_bp_list = new ArrayList<>();
        List<BigDecimal> pat2_hr_list = new ArrayList<>();
        List<BigDecimal> pat2_rr_list = new ArrayList<>();
        List<BigDecimal> pat2_temp_list= new ArrayList<>();

        try {
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT id,firstname,lastname,age,bloodtype,location,heartrate,respiratoryrate,temperature FROM vitalsigns where id=2;";
            ResultSet resset=s2.executeQuery(sqlStr);
            while(resset.next()){
                pat2id=resset.getInt("id");
                pat2firstname=resset.getString("firstname");
                pat2lastname=resset.getString("lastname");
                pat2age=resset.getInt("age");
                pat2bloodtype=resset.getString("bloodtype");
                Array pat2_loc=resset.getArray("location");
                Array pat2_hr=resset.getArray("heartrate");
                Array pat2_rr=resset.getArray("respiratoryrate");
                Array pat2_temp=resset.getArray("temperature");

                // CODE FROM  https://stackoverflow.com/questions/23277777/java-sql-array-to-arrayliststring-oraclecallablestatement

                //location list
                for (Object obj : (Object[])pat2_loc.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_loc_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }


                //heartrate list
                for (Object obj : (Object[])pat2_hr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_hr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //respiratory rate list
                for (Object obj : (Object[])pat2_rr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_rr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //temperature list
                for (Object obj : (Object[])pat2_temp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_temp_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

            }

            resset.close();
            s2.close();
            //conn.close();
        }
        catch (Exception e){
            System.out.println("I'M IN CATCH");
            System.out.println(e.getMessage());
        }

        try {  //CODE TO GET ECG AND BP, ONE AT A TIME NEEDED
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT ecg FROM vitalsigns where id=2;";
            ResultSet resset=s2.executeQuery(sqlStr);
            Statement s3=conn.createStatement();
            String sqlStr3= "SELECT bloodpressure FROM vitalsigns where id=2;";
            ResultSet resset3=s3.executeQuery(sqlStr3);
            while(resset.next()){
                Array pat2_ecg=resset.getArray("ecg");
                for (Object obj : (Object[])pat2_ecg.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_ecg_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
                // converting to double[] THIS WAS WRONG,
                //double[] pat1_ecgArray = (double[])pat1_ecg.getArray();
            }
            while(resset3.next()){
                Array pat2_bp=resset3.getArray("bloodpressure");
                for (Object obj : (Object[])pat2_bp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat2_bp_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
            }
            resset.close();
            s2.close();
            conn.close();
        }
        catch (Exception e){
            System.out.println("I'M IN 2nd CATCH");
        }

        // instantiate the patient list here, this is the code that was present to start the app in main before i worked in database:

        Patient pat1 = new Patient(pat1id,pat1firstname,pat1lastname,pat1age,pat1bloodtype,pat1_loc_list,pat1_ecg_list,pat1_bp_list, pat1_hr_list, pat1_rr_list, pat1_temp_list);
        Patient pat2 = new Patient(pat2id,pat2firstname,pat2lastname,pat2age,pat2bloodtype,pat2_loc_list,pat2_ecg_list,pat2_bp_list, pat2_hr_list, pat2_rr_list, pat2_temp_list);
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        patientList.add(pat2);
        mainMenu.realTimeAlertChecker(patientList);
        EmergencyUIController emUIController = new EmergencyUIController(patientList);


        // scheduled task on generating permanent record at 12am everyday
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Long midnight=LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES);

        scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < patientList.size(); i++) {
                        try {
                            Records.recordwriter(patientList.get(i));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        }, midnight, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES);

    }
}


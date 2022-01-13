// Where everything runs here


import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.sql.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws SQLException{
        Records daily_record = new Records();
        //CODE ADDED TO IMPLEMENT DATABASE


        //DB CONNECTION PART
        DBConnection herokuconn = new DBConnection();
        Connection conn = herokuconn.connectToDB();

        //END OF DB CONNECTION PART

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
                //The code transforms each java.sql array datatype to a List<BigDecimal> (done 6 times, for location list and for 5 vital signs)

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
        }
        catch (Exception e){
            System.out.println("I'M IN CATCH"); //debug
            System.out.println(e.getMessage());
        }

        try {  //CODE TO GET ECG AND BP(same idea as previously), ONE AT A TIME IMPROVES PERFORMANCE
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
        }
        catch (Exception e){
            System.out.println("I'M IN 2nd CATCH");
        }

        //Code above, repeated for 2nd patient

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

        // instantiate the ArrayList<Patient> here, and initiate UI

        // instantiate the patient list here, this is the code that was present to start the app in main before i worked in database:
        Patient pat1 = new Patient(pat1id,pat1firstname,pat1lastname,pat1age,pat1bloodtype,pat1_loc_list,pat1_ecg_list,pat1_bp_list, pat1_hr_list, pat1_rr_list, pat1_temp_list);
        Patient pat2 = new Patient(pat2id,pat2firstname,pat2lastname,pat2age,pat2bloodtype,pat2_loc_list,pat2_ecg_list,pat2_bp_list, pat2_hr_list, pat2_rr_list, pat2_temp_list);
        Patient pat3 = new Patient(3,"Amelia","Brandee",43,"0-",List.of(new BigDecimal(5), new BigDecimal(56), new BigDecimal(6)),pat1_ecg_list,pat1_bp_list, pat1_hr_list, pat1_rr_list, pat1_temp_list);
        Patient pat4 = new Patient(4,"Lesly","Gauvain",87,"AB+",List.of(new BigDecimal(4), new BigDecimal(43), new BigDecimal(9)),pat2_ecg_list,pat2_bp_list, pat2_hr_list, pat2_rr_list, pat2_temp_list);



        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        patientList.add(pat2);
        patientList.add(pat3);
        patientList.add(pat4);
        AlertCheck.realTimeAlertChecker(patientList);
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


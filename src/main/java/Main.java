
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
        }
        catch (Exception e){
            System.out.println("I'M IN 2nd CATCH");
        }


        //Code above, repeated for 3rd patient

        int pat3id=2; //dummy values
        String pat3firstname="Nettles"; //dummy values, in honour
        String pat3lastname="Holloway"; //dummy values, in honour
        int pat3age=7;
        String pat3bloodtype="O+";
        List<BigDecimal> pat3_loc_list = new ArrayList<>();
        List<BigDecimal> pat3_ecg_list = new ArrayList<>();
        List<BigDecimal> pat3_bp_list = new ArrayList<>();
        List<BigDecimal> pat3_hr_list = new ArrayList<>();
        List<BigDecimal> pat3_rr_list = new ArrayList<>();
        List<BigDecimal> pat3_temp_list= new ArrayList<>();

        try {
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT id,firstname,lastname,age,bloodtype,location,heartrate,respiratoryrate,temperature FROM vitalsigns where id=3;";
            ResultSet resset=s2.executeQuery(sqlStr);
            while(resset.next()){
                pat3id=resset.getInt("id");
                pat3firstname=resset.getString("firstname");
                pat3lastname=resset.getString("lastname");
                pat3age=resset.getInt("age");
                pat3bloodtype=resset.getString("bloodtype");
                Array pat3_loc=resset.getArray("location");
                Array pat3_hr=resset.getArray("heartrate");
                Array pat3_rr=resset.getArray("respiratoryrate");
                Array pat3_temp=resset.getArray("temperature");

                // CODE FROM  https://stackoverflow.com/questions/23277777/java-sql-array-to-arrayliststring-oraclecallablestatement

                //location list
                for (Object obj : (Object[])pat3_loc.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_loc_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }


                //heartrate list
                for (Object obj : (Object[])pat3_hr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_hr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //respiratory rate list
                for (Object obj : (Object[])pat3_rr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_rr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //temperature list
                for (Object obj : (Object[])pat3_temp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_temp_list.add(arr);
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
            String sqlStr= "SELECT ecg FROM vitalsigns where id=3;";
            ResultSet resset=s2.executeQuery(sqlStr);
            Statement s3=conn.createStatement();
            String sqlStr3= "SELECT bloodpressure FROM vitalsigns where id=3;";
            ResultSet resset3=s3.executeQuery(sqlStr3);
            while(resset.next()){
                Array pat3_ecg=resset.getArray("ecg");
                for (Object obj : (Object[])pat3_ecg.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_ecg_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
            }
            while(resset3.next()){
                Array pat3_bp=resset3.getArray("bloodpressure");
                for (Object obj : (Object[])pat3_bp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat3_bp_list.add(arr);
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

        //Code above, repeated for 4th patient

        int pat4id=2; //dummy values
        String pat4firstname="Nettles"; //dummy values, in honour
        String pat4lastname="Holloway"; //dummy values, in honour
        int pat4age=7;
        String pat4bloodtype="O+";
        List<BigDecimal> pat4_loc_list = new ArrayList<>();
        List<BigDecimal> pat4_ecg_list = new ArrayList<>();
        List<BigDecimal> pat4_bp_list = new ArrayList<>();
        List<BigDecimal> pat4_hr_list = new ArrayList<>();
        List<BigDecimal> pat4_rr_list = new ArrayList<>();
        List<BigDecimal> pat4_temp_list= new ArrayList<>();

        try {
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT id,firstname,lastname,age,bloodtype,location,heartrate,respiratoryrate,temperature FROM vitalsigns where id=4;";
            ResultSet resset=s2.executeQuery(sqlStr);
            while(resset.next()){
                pat4id=resset.getInt("id");
                pat4firstname=resset.getString("firstname");
                pat4lastname=resset.getString("lastname");
                pat4age=resset.getInt("age");
                pat4bloodtype=resset.getString("bloodtype");
                Array pat4_loc=resset.getArray("location");
                Array pat4_hr=resset.getArray("heartrate");
                Array pat4_rr=resset.getArray("respiratoryrate");
                Array pat4_temp=resset.getArray("temperature");

                // CODE FROM  https://stackoverflow.com/questions/23277777/java-sql-array-to-arrayliststring-oraclecallablestatement

                //location list
                for (Object obj : (Object[])pat4_loc.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_loc_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }


                //heartrate list
                for (Object obj : (Object[])pat4_hr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_hr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //respiratory rate list
                for (Object obj : (Object[])pat4_rr.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_rr_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }

                //temperature list
                for (Object obj : (Object[])pat4_temp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_temp_list.add(arr);
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
            String sqlStr= "SELECT ecg FROM vitalsigns where id=4;";
            ResultSet resset=s2.executeQuery(sqlStr);
            Statement s3=conn.createStatement();
            String sqlStr3= "SELECT bloodpressure FROM vitalsigns where id=4;";
            ResultSet resset3=s3.executeQuery(sqlStr3);
            while(resset.next()){
                Array pat4_ecg=resset.getArray("ecg");
                for (Object obj : (Object[])pat4_ecg.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_ecg_list.add(arr);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a BigDecimal");
                        e.printStackTrace();
                    }
                }
            }
            while(resset3.next()){
                Array pat4_bp=resset3.getArray("bloodpressure");
                for (Object obj : (Object[])pat4_bp.getArray()) {
                    try {
                        BigDecimal arr = (BigDecimal) obj;
                        pat4_bp_list.add(arr);
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
        Patient pat3 = new Patient(pat3id,pat3firstname,pat3lastname,pat3age,pat3bloodtype,pat3_loc_list,pat3_ecg_list,pat3_bp_list, pat3_hr_list, pat3_rr_list, pat3_temp_list);
        Patient pat4 = new Patient(pat4id,pat4firstname,pat4lastname,pat4age,pat4bloodtype,pat4_loc_list,pat4_ecg_list,pat4_bp_list, pat4_hr_list, pat4_rr_list, pat4_temp_list);


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


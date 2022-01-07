// Where everything runs here


import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import com.google.gson.Gson;

import java.sql.*;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException{

        //CODE ADDED TO IMPLEMENT DATABASE

        //String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

        // WEEEEEEEEEEE

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
            conn.close();
        }
        catch (Exception e){
            System.out.println("I'M IN 2nd CATCH");
        }


        // instantiate the patient list here, this is the code that was present to start the app in main before i worked in database:

        //Patient pat1 = new Patient(pat1id,pat1firstname,pat1lastname,pat1age,pat1bloodtype,pat1_ecgArray,pat1_bpArray, pat1_hrArray, pat1_rrArray, pat1_tempArray);
        //ArrayList<Patient> patientList = new ArrayList<Patient>();
        //patientList.add(pat1);
        //mainMenu.realTimeAlertChecker(patientList);
        //EmergencyUIController emUIController = new EmergencyUIController(patientList);

    }
}


// Where everything runs here


import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import com.google.gson.Gson;

import java.sql.*;
public class Main {

    public static void main(String[] args) throws SQLException{

        //CODE ADDED TO IMPLEMENT DATABASE

        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
        Connection conn= DriverManager.getConnection(dbUrl, "postgres", "remotepatientmonitoring");

        try {
            //System.out.println("It reaches here");
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT id,firstname,lastname,age,bloodtype,heartrate,respiratoryrate,temperature FROM vitalsigns where id=2;";
            ResultSet resset=s2.executeQuery(sqlStr);
            while(resset.next()){
                int pat1id=resset.getInt("id");
                String pat1firstname=resset.getString("firstname");
                String pat1lastname=resset.getString("lastname");
                int pat1age=resset.getInt("age");
                String pat1bloodtype=resset.getString("bloodtype");
                Array pat1_hr=resset.getArray("heartrate");
                Array pat1_rr=resset.getArray("respiratoryrate");
                Array pat1_temp=resset.getArray("temperature");
                System.out.println(pat1_temp);
            }
            resset.close();
            s2.close();
            //conn.close();
        }
        catch (Exception e){
        }

        try {
            //System.out.println("It reaches here");
            Statement s2=conn.createStatement();
            String sqlStr= "SELECT ecg FROM vitalsigns where id=2;";
            ResultSet resset=s2.executeQuery(sqlStr);
            Statement s3=conn.createStatement();
            String sqlStr3= "SELECT heartrate FROM vitalsigns where id=2;";
            ResultSet resset3=s3.executeQuery(sqlStr3);
            while(resset.next()){
                Array pat1_ecg=resset.getArray("ecg");
            }
            while(resset3.next()){
                Array pat1_bp=resset3.getArray("bloodpressure");
            }
            resset.close();
            s2.close();
            conn.close();
        }
        catch (Exception e){
        }

        // instantiate the patient list here:

        //Patient pat1 = new Patient(pat1id,pat1firstname,pat1lastname,pat1age,pat1bloodtype,pat1_ecg,pat1_bp, pat1_hr, pat1_rr, pat1_temp);
        //ArrayList<Patient> patientList = new ArrayList<Patient>();
        ///patientList.add(pat1);
        //mainMenu.realTimeAlertChecker(patientList);
        //EmergencyUIController emUIController = new EmergencyUIController(patientList);

    }

}


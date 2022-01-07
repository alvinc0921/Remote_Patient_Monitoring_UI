import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class AlertCheckingTest {

    // There are still errors!

    @org.junit.Test
    public void run(){

        List<BigDecimal> pat1_temp = new ArrayList<>();
        List<BigDecimal> pat1_ecg = new ArrayList<>();
        List<BigDecimal> pat1_hr = new ArrayList<>();
        List<BigDecimal> pat1_bp = new ArrayList<>();
        List<BigDecimal> pat1_rr = new ArrayList<>();

        // Low temperature
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));

        // Very High Heart Rate
        /*
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
         */

        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));

        // High Respiratory Rate
        pat1_rr.add(new BigDecimal(22));        // high rr
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));        // high rr
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));        // high rr
        pat1_rr.add(new BigDecimal(22));

        pat1_ecg = pat1_temp;
        pat1_bp = pat1_temp;

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


        // instantiate the patient list here:
        Patient pat1 = new Patient(pat1ID, "Amy", "Smith", 65, pat1_bloodtype, pat1_location, pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);
        //Patient pat2 = new Patient(pat2ID, "Bob", "Smith", 50, pat2_bloodtype, pat2_ecg, pat2_bp, pat2_hr, pat2_rr, pat2_temp);
        //Patient pat3 = new Patient(pat3ID, "Carina", "Smith", 40, pat3_bloodtype, pat3_ecg, pat3_bp, pat3_hr, pat3_rr, pat3_temp);

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        //patientList.add(pat2);
        //patientList.add(pat3);

        mainMenu.realTimeAlertChecker(patientList);

        ArrayList<String> pat1Details = new ArrayList<String>();
        pat1Details.add("Low Temperature");
        //pat1Details.add("Very High Heart Rate");
        pat1Details.add("High Respiratory Rate");

        /*
        System.out.println(pat1.length);
        System.out.println(pat1.tempFlag);
        System.out.println(pat1.hrFlag);
        System.out.println(pat1.rrFlag);
        System.out.println(pat1.abnormalDetails);
        System.out.println(pat1.alertStatus);
         */

        //assertEquals("Urgent", pat1.alertStatus);
        //assertEquals(pat1Details, pat1.abnormalDetails);
        assertLinesMatch(pat1Details, pat1.abnormalDetails);

    }

    /*
    Old version of unit testing:

    double[] pat1_temp = new double[] {40, 40, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 40, 40, 40, 40, 40, 40};
    double[] pat2_temp = new double[] {39, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 38.7, 38.6, 38.5, 39, 39, 39, 39};
    double[] pat3_temp = new double[] {36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 39, 39, 39, 39, 39, 36, 36, 36, 36, 36};

    double[] pat1_hr = new double[] {40, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 40, 40, 40, 40, 40, 40};
    double[] pat2_hr = new double[] {120, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 120, 120, 120, 120};
    double[] pat3_hr = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

    double[] pat1_rr = new double[] {6, 6, 6, 4, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 6};
    double[] pat2_rr = new double[] {18, 18, 22, 22, 22, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};
    double[] pat3_rr = new double[] {12, 12, 12, 12, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 6, 6, 6, 12, 12};

    double[] pat1_ecg = new double[] {36, 36, 10, 36, 36, 36, 36, 36, 36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 36, 36};
    double[] pat2_ecg = new double[] {36, 36, 30, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36};
    double[] pat3_ecg = new double[] {88, 100, 36, 41, 41, 41, 41, 41, 41, 41, 39, 39, 39, 39, 39, 36, 36, 36, 36, 36};

    double[] pat1_bp = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 200, 200, 200, 100, 100, 100};
    double[] pat2_bp = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    double[] pat3_bp = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

    ArrayList<Patient> patientList = new ArrayList<Patient>();

    @org.junit.Test
    public void run(){

        // Pat1: Very high temp, low heart rate, low respiratory rate - Urgent
        Patient pat1 = new Patient(1, "Amy", "Smith", 65, "A", pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);
        // Pat2: high temperature, high bp - warning
        Patient pat2 = new Patient(2, "Bob", "Smith", 50, "B", pat2_ecg, pat2_bp, pat2_hr, pat2_rr, pat2_temp);
        // Pat3: Healthy
        Patient pat3 = new Patient(3, "Carina", "Smith", 40, "O", pat3_ecg, pat3_bp, pat3_hr, pat3_rr, pat3_temp);

        patientList.add(pat1);
        patientList.add(pat2);
        patientList.add(pat3);

        mainMenu.realTimeAlertChecker(patientList);

        List<String> pat1Details = new ArrayList<String>();
        pat1Details.add("Very High Temperature");
        pat1Details.add("Very Low Heart Rate");
        pat1Details.add("Low Respiratory Rate");

        List<String> pat2Details = new ArrayList<String>();
        pat2Details.add("High Temperature");
        pat2Details.add("High Blood Pressure");

        ArrayList<String> pat3Details = new ArrayList<String>();

     */
/*
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                assertLinesMatch(pat1Details, pat1.abnormalDetails);
            }
        };
        timer.schedule(task, 20*1000);

 */

        //assertEquals(pat1Details, pat1.abnormalDetails);
        //assertEquals(pat2Details, pat2.abnormalDetails);
        //assertEquals(pat3Details, pat3.abnormalDetails);
        //assertEquals("Urgent", pat1.alertStatus);
        //assertEquals("Warning", pat2.alertStatus);
        //assertEquals("Healthy", pat3.alertStatus);





}
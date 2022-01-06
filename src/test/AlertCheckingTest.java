import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class AlertCheckingTest {

    // NOT YET DONE! Have errors!!!

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

        assertEquals(pat1Details, pat1.abnormalDetails);
        //assertEquals(pat2Details, pat2.abnormalDetails);
        //assertEquals(pat3Details, pat3.abnormalDetails);
        //assertEquals("Urgent", pat1.alertStatus);
        //assertEquals("Warning", pat2.alertStatus);
        //assertEquals("Healthy", pat3.alertStatus);


    }

}
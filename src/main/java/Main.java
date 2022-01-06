// Where everything runs here


import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Making an array for the temp, hr and rr
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

        mainMenu.realTimeAlertChecker(patientList);

        EmergencyUIController emUIController = new EmergencyUIController(patientList);

    }

}


// Where everything runs here


import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Making an array for the temp, hr and rr
        double[] pat1_temp = new double[] {36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 40, 40, 40, 40, 40, 40};
        double[] pat2_temp = new double[] {36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 38.7, 38.6, 38.5, 39, 39, 39, 39};
        double[] pat3_temp = new double[] {36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 39, 39, 39, 39, 39, 36, 36, 36, 36, 36};

        double[] pat1_hr = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 40, 40, 40, 40, 40, 40};
        double[] pat2_hr = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 120, 120, 120, 120};
        double[] pat3_hr = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

        double[] pat1_rr = new double[] {4, 4, 4, 4, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 6};
        double[] pat2_rr = new double[] {18, 18, 22, 22, 22, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};
        double[] pat3_rr = new double[] {6, 6, 6, 6, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 6, 6, 6, 6, 6};

        double[] pat1_ecg = new double[] {10, 10, 10, 36, 36, 36, 36, 36, 36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 36, 36};
        double[] pat2_ecg = new double[] {20, 40, 30, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36};
        double[] pat3_ecg = new double[] {88, 100, 36, 41, 41, 41, 41, 41, 41, 41, 39, 39, 39, 39, 39, 36, 36, 36, 36, 36};

        double[] pat1_bp = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 200, 200, 200, 100, 100, 100};
        double[] pat2_bp = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        double[] pat3_bp = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

        int pat1ID = 0001;
        int pat2ID = 0002;
        int pat3ID = 0003;

        String pat1_bloodtype = "A";
        String pat2_bloodtype = "B";
        String pat3_bloodtype = "O";


        // instantiate the patient list here:
        Patient pat1 = new Patient(pat1ID, "Amy", "Smith", 65, pat1_bloodtype, pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);
        Patient pat2 = new Patient(pat2ID, "Bob", "Smith", 50, pat2_bloodtype, pat2_ecg, pat2_bp, pat2_hr, pat2_rr, pat2_temp);
        Patient pat3 = new Patient(pat3ID, "Carina", "Smith", 40, pat3_bloodtype, pat3_ecg, pat3_bp, pat3_hr, pat3_rr, pat3_temp);

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        patientList.add(pat2);
        patientList.add(pat3);

        mainMenu.realTimeAlertChecker(patientList);

        EmergencyUIController emUIController = new EmergencyUIController(patientList);


    }

}


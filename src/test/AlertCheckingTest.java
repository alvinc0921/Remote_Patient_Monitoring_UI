import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlertCheckingTest {

    @org.junit.Test
    public void run(){

        List<BigDecimal> pat1_temp = new ArrayList<>();
        List<BigDecimal> pat1_ecg;
        List<BigDecimal> pat1_hr = new ArrayList<>();
        List<BigDecimal> pat1_bp;
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


        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));
        pat1_hr.add(new BigDecimal(90));

        // High Respiratory Rate
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));

        pat1_ecg = pat1_temp;
        pat1_bp = pat1_temp;

        int pat1ID = 1;

        String pat1_bloodtype = "A";

        List<BigDecimal> pat1_location = new ArrayList<>();
        pat1_location.add(new BigDecimal(3));       // Floor
        pat1_location.add(new BigDecimal(50));      // Room
        pat1_location.add(new BigDecimal(2));       // Bed


        Patient pat1 = new Patient(pat1ID, "Amy", "Smith", 65, pat1_bloodtype, pat1_location, pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);


        AlertCheck.realTimeAlertChecker(patientList);

        ArrayList<String> pat1Details = new ArrayList<String>();
        pat1Details.add("Low Temperature");
        pat1Details.add("High Respiratory Rate");

        assertLinesMatch(pat1Details, pat1.abnormalDetails);

    }

}
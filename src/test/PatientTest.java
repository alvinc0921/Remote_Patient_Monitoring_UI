import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @org.junit.Test
    public void run(){

        List<BigDecimal> pat1_temp = new ArrayList<>();
        List<BigDecimal> pat1_ecg = new ArrayList<>();
        List<BigDecimal> pat1_hr = new ArrayList<>();
        List<BigDecimal> pat1_bp = new ArrayList<>();
        List<BigDecimal> pat1_rr = new ArrayList<>();

        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(36.0));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));
        pat1_temp.add(new BigDecimal(35.8));

        pat1_hr.add(new BigDecimal(100));
        pat1_hr.add(new BigDecimal(100));
        pat1_hr.add(new BigDecimal(114));
        pat1_hr.add(new BigDecimal(114));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));
        pat1_hr.add(new BigDecimal(140));

        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(22));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));
        pat1_rr.add(new BigDecimal(18));

        pat1_ecg = pat1_temp;
        pat1_bp = pat1_temp;

        int pat1ID = 1;
        String pat1_bloodtype = "A";

        List<BigDecimal> pat1_location = new ArrayList<>();
        pat1_location.add(new BigDecimal(3));       // Floor
        pat1_location.add(new BigDecimal(50));      // Room
        pat1_location.add(new BigDecimal(2));       // Bed

        Patient pat1 = new Patient(pat1ID, "Amy", "Smith", 65, pat1_bloodtype, pat1_location, pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);

        assertEquals(pat1.getFirstname(), "Amy");                       // firstname
        assertEquals(pat1.getLastname(), "Smith");                      // lastname
        assertEquals(pat1.age, 65);                                     // age
        assertEquals(pat1.getTempSig().get(4).doubleValue(), 35.8);     // temperature value
        assertEquals(pat1.location.get(1).doubleValue(), 50);           // Room number

    }

}
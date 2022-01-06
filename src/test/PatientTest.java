import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @org.junit.Test
    public void run(){
        double[] pat1_temp = new double[] {36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 36, 36};
        double[] pat1_hr = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 200, 200, 200, 100, 100, 100};
        double[] pat1_rr = new double[] {4, 4, 4, 4, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};
        double[] pat1_ecg = new double[] {10, 10, 10, 36, 36, 36, 36, 36, 36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 36, 36};
        double[] pat1_bp = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 200, 200, 200, 100, 100, 100};

        Patient pat1 = new Patient(1, "Amy", "Smith", 65, "A", pat1_ecg, pat1_bp, pat1_hr, pat1_rr, pat1_temp);

        assertEquals(pat1.firstname, "Amy");
        assertEquals(pat1.lastname, "Smith");
        assertEquals(pat1.age, 65);
    }

}
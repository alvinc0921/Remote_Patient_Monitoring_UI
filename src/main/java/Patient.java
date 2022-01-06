import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    // which has variables to store temp, BP, HR, RR, ECG, alertStatus, report, other patient data

    int patID;
    int length = 20; // for now
    String firstname;
    String lastname;
    int age;
    String bloodtype;
    String alertStatus;
    String patLoc = "(to be done)";

    //int length;     // now temporarily store the length of the array
    double[] temp;
    double[] bp;
    double[] hr;
    double[] rr;
    double[] ecg;

    ArrayList<String> abnormalDetails = new ArrayList<String>();

    String tempFlag = new String();
    String hrFlag = new String();
    String rrFlag = new String();

    ArrayList<String> alertHistoryTemp = new ArrayList<>();
    ArrayList<String> alertHistoryHR = new ArrayList<>();
    ArrayList<String> alertHistoryRR = new ArrayList<>();

    public Patient(int patID, String firstname, String lastname, int age, String bloodtype, double[] ecg, double[] bp, double[] hr, double[] rr, double[] temp){
        this.patID = patID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.bloodtype = bloodtype;
        this.ecg = ecg;
        this.bp = bp;
        this.hr = hr;
        this.rr = rr;
        this.temp = temp;
    }

    public Patient get() {
        return this;
    }

}
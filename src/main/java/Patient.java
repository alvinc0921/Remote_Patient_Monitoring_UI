import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    // which has variables to store temp, BP, HR, RR, ECG, alertStatus, report, other patient data

    String name;
    String alertStatus;
    String patLoc;

    int length;     // now temporarily store the length of the array
    double[] temp = new double[length];
    double[] bp = new double[length];
    double[] hr = new double[length];
    double[] rr = new double[length];
    double[] ecg = new double[length];

    List<String> abnormalDetails = new ArrayList<String>();

    String tempFlag = new String();
    String hrFlag = new String();
    String rrFlag = new String();

    ArrayList<String> alertHistoryTemp = new ArrayList<>();
    ArrayList<String> alertHistoryHR = new ArrayList<>();
    ArrayList<String> alertHistoryRR = new ArrayList<>();

    public Patient(String name, String alertStatus, String patLoc, int length, double[] temp, double[] hr, double[] rr){
        this.name = name;
        this.alertStatus = alertStatus;
        this.patLoc = patLoc;
        this.length = length;
        this.temp = temp;
        //this.bp = bp;
        this.hr = hr;
        this.rr = rr;
    }

    public Patient get() {
        return this;
    }

}
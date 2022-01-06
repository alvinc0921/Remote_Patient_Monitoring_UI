import java.lang.reflect.Array;
import java.math.BigDecimal;
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

    // To be deleted!
    double[] temp;
    double[] bp;
    double[] hr;
    double[] rr;
    double[] ecg;

    List<BigDecimal> tempSig = new ArrayList<>();
    List<BigDecimal> bpSig = new ArrayList<>();
    List<BigDecimal> hrSig = new ArrayList<>();
    List<BigDecimal> rrSig = new ArrayList<>();
    List<BigDecimal> ecgSig = new ArrayList<>();

    ArrayList<String> abnormalDetails = new ArrayList<String>();

    String tempFlag = new String();
    String hrFlag = new String();
    String rrFlag = new String();

    ArrayList<String> alertHistoryTemp = new ArrayList<>();
    ArrayList<String> alertHistoryHR = new ArrayList<>();
    ArrayList<String> alertHistoryRR = new ArrayList<>();

    public Patient(int patID, String firstname, String lastname, int age, String bloodtype, List<BigDecimal> ecgSig, List<BigDecimal> bpSig, List<BigDecimal> hrSig, List<BigDecimal> rrSig, List<BigDecimal> tempSig){
        this.patID = patID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.bloodtype = bloodtype;
        this.ecgSig = ecgSig;
        this.bpSig = bpSig;
        this.hrSig = hrSig;
        this.rrSig = rrSig;
        this.tempSig = tempSig;
    }


}
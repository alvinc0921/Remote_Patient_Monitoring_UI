import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    int patID;
    String firstname;
    String lastname;
    int age;
    String bloodtype;
    /*
    String alertStatus;
    String patLoc = "(to be done)";

    //int length;     // now temporarily store the length of the array
    double[] temp;
    double[] bp;
    double[] hr;
    double[] rr;
    double[] ecg;

    List<String> abnormalDetails = new ArrayList<String>();
    */
  
    List<BigDecimal> location = new ArrayList<>();

    // Lists to store 5 Signals
    List<BigDecimal> tempSig = new ArrayList<>();
    List<BigDecimal> bpSig = new ArrayList<>();
    List<BigDecimal> hrSig = new ArrayList<>();
    List<BigDecimal> rrSig = new ArrayList<>();
    List<BigDecimal> ecgSig = new ArrayList<>();

    // For real-time alert checking
    String alertStatus;
    ArrayList<String> abnormalDetails = new ArrayList<String>();

    String tempFlag = new String();
    String hrFlag = new String();
    String rrFlag = new String();

    // For saving alert history of temperature, heart rate and respiratory rate
    ArrayList<String> alertHistoryTemp = new ArrayList<>();
    ArrayList<String> alertHistoryHR = new ArrayList<>();
    ArrayList<String> alertHistoryRR = new ArrayList<>();


public Patient(int patID, String firstname, String lastname, int age, String bloodtype, List<BigDecimal> location, List<BigDecimal> ecgSig, List<BigDecimal> bpSig, List<BigDecimal> hrSig, List<BigDecimal> rrSig, List<BigDecimal> tempSig){
        this.patID = patID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.bloodtype = bloodtype;
        this.location = location;
        this.ecgSig = ecgSig;
        this.bpSig = bpSig;
        this.hrSig = hrSig;
        this.rrSig = rrSig;
        this.tempSig = tempSig;
    }

    public List<BigDecimal> getTempSig() {
        return tempSig;
    }

    public List<BigDecimal> getHrSig() {
        return hrSig;
    }

    public List<BigDecimal> getRrSig() {
        return rrSig;
    }

    public ArrayList<String> getAlertHistoryTemp() {
        return alertHistoryTemp;
    }

    public ArrayList<String> getAlertHistoryHR() {
        return alertHistoryHR;
    }

    public ArrayList<String> getAlertHistoryRR() {
        return alertHistoryRR;
    }

    public int getPatID() {
        return patID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
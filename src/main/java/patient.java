public class patient {
    // this class i basically stole from dorothy ;) and i put functions for names
    // which has variables to store temp, BP, HR, RR, ECG, alertStatus, report, other patient data

    public patient(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }


    String name;
    String alertStatus;
    String patLoc;

    // should not be double - how?
    double temp;
    double bp;
    double hr;
    double rr;

    public patient get() {
        return this;
    }
    // Account for ECG also



}
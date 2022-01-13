import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public interface AlertCheck {

    /* Real-time checking the temperature, heart rate and respiratory rate of each patient on the patientList
       Implement a TimerTask scheduled at every second to:
       - Update patients' statuses (urgent/ warning/ healthy)
       - Update patients' abnormality details (e.g. High Temperature, Very High Heart Rate etc.)
       - Update the tempFlag/ hrFlag/ rrFlag to indicate last second's vital statuses for the next second
       -  (if any) Save the period of time (start time and end time using Clock variables) of the urgent/
          warning status of each vital signal in the day to the corresponding alertHistory
     */
    static void realTimeAlertChecker(ArrayList<Patient> patientList) {

        // Threshold values (Very Low, Low, High, Very High) for temperature (temp), heart rate (hr), respiratory rate (rr)
        double tempHighUThres = 40.0;               // temp
        double tempHighWThres = 37.6;
        double tempLowWThres = 35.9;
        double tempLowUThres = 35.0;

        double hrHighUThres = 130.0;                // hr
        double hrHighWThres = 101.0;
        double hrLowWThres = 59.0;
        double hrLowUThres = 40.0;

        double rrHighUThres = 24.0;                 // rr
        double rrHighWThres = 19.9;
        double rrLowWThres = 11.9;
        double rrLowUThres = 11.0;

        for (Patient pat : patientList) {           // Initialise all flags to be Healthy "H"
            pat.tempFlag = "H";
            pat.hrFlag = "H";
            pat.rrFlag = "H";
        }

        final int[] i = {0};                        // Counter of the vital signal in TimerTask alertCheck

        Clock clock = Clock.systemDefaultZone();
        Timer timer = new Timer();
        TimerTask alertCheck = new TimerTask() {
            @Override
            public void run() {

                for (Patient pat : patientList) {

                    pat.alertStatus = "Healthy";        // Reset the previous alertStatus
                    pat.abnormalDetails.clear();        // clear the previous abnormality details

                    // 1. Checking TEMPERATURE: with 3 cases
                    if (pat.tempFlag == "H") {                      //A. If patient is having healthy temp previously
                        // Get the current data point (using counter i[0]) of tempSig
                        // Compare with different thresholds to determine the status and abnormality
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighWThres && pat.tempSig.get(i[0]).doubleValue() < tempHighUThres) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);          // save the current abnormality details
                            pat.tempFlag = "W";                     // change the flag
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning Start: " + instant.toString());   // save the start time of the warning period
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighUThres) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent Start: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowWThres && pat.tempSig.get(i[0]).doubleValue() > tempLowUThres) {
                            String temp = "Low Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning Start: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowUThres) {
                            String temp = "Very Low Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.tempFlag == "W") {               // B. If patient is having warning temp previously
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighWThres && pat.tempSig.get(i[0]).doubleValue() < tempHighUThres) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            // Stays at "warning" - no change to the flag
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighUThres) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: " + instant.toString());        // save the end time of the previous warning period
                            pat.alertHistoryTemp.add("Urgent starts: " + instant.toString());       // save the start time of the urgent period
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() < tempHighWThres && pat.tempSig.get(i[0]).doubleValue() > tempLowWThres) {
                            pat.tempFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowWThres && pat.tempSig.get(i[0]).doubleValue() > tempLowUThres) {
                            String statement = "Low Temperature";
                            pat.abnormalDetails.add(statement);
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowUThres) {
                            String statement = "Very Low Temperature";
                            pat.abnormalDetails.add(statement);
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: " + instant.toString());
                            pat.alertHistoryTemp.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.tempFlag == "U") {               // C. If patient is having urgent temp previously
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighWThres && pat.tempSig.get(i[0]).doubleValue() < tempHighUThres) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryTemp.add("Warning starts: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighUThres) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            // no change to the flag
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() < tempHighWThres && pat.tempSig.get(i[0]).doubleValue() > tempLowWThres) {
                            pat.tempFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent ends: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowWThres && pat.tempSig.get(i[0]).doubleValue() > tempLowUThres) {
                            String statement = "Low Temperature";
                            pat.abnormalDetails.add(statement);
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryTemp.add("Warning starts: " + instant.toString());
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() <= tempLowUThres) {
                            String statement = "Very Low Temperature";
                            pat.abnormalDetails.add(statement);
                        }
                    }

                    // 2. Checking HEART RATE
                    if (pat.hrFlag == "H") {                        // A. If patient is having healthy hr previously
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighWThres && pat.hrSig.get(i[0]).doubleValue() < hrHighUThres) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning Start: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighUThres) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent Start: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowWThres && pat.hrSig.get(i[0]).doubleValue() > hrLowUThres) {
                            String hr = "Low Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning Start: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowUThres) {
                            String temp = "Very Low Heart Rate";
                            pat.abnormalDetails.add(temp);
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.hrFlag == "W") {                     // B. If patient is having warning hr previously
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighWThres && pat.hrSig.get(i[0]).doubleValue() < hrHighUThres) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighUThres) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning ends: " + instant.toString());
                            pat.alertHistoryHR.add("Urgent starts: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() < hrHighWThres && pat.hrSig.get(i[0]).doubleValue() > hrLowWThres) {
                            pat.hrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning ends: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowWThres && pat.hrSig.get(i[0]).doubleValue() > hrLowUThres) {
                            String statement = "Low Heart Rate";
                            pat.abnormalDetails.add(statement);
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowUThres) {
                            String statement = "Very Low Heart Rate";
                            pat.abnormalDetails.add(statement);
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning ends: " + instant.toString());
                            pat.alertHistoryHR.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.hrFlag == "U") {                     // C. If patient is having urgent hr previously
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighWThres && pat.hrSig.get(i[0]).doubleValue() < hrHighUThres) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryHR.add("Warning starts: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() >= hrHighUThres) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() < hrHighWThres && pat.hrSig.get(i[0]).doubleValue() > hrLowWThres) {
                            pat.hrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent ends: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowWThres && pat.hrSig.get(i[0]).doubleValue() > hrLowUThres) {
                            String statement = "Low Heart Rate";
                            pat.abnormalDetails.add(statement);
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryHR.add("Warning starts: " + instant.toString());
                        }
                        if (pat.hrSig.get(i[0]).doubleValue() <= hrLowUThres) {
                            String statement = "Very Low Heart Rate";
                            pat.abnormalDetails.add(statement);
                        }
                    }


                    // 3. Checking RESPIRATORY RATE
                    if (pat.rrFlag == "H") {                            // A. If patient is having healthy rr previously
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighWThres && pat.rrSig.get(i[0]).doubleValue() < rrHighUThres) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning Start: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighUThres) {
                            String rr = "Very High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent Start: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowWThres && pat.rrSig.get(i[0]).doubleValue() > rrLowUThres) {
                            String hr = "Low Respiratory Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning Start: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowUThres) {
                            String temp = "Very Low Respiratory Rate";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.rrFlag == "W") {                     // B. If patient is having warning rr previously
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighWThres && pat.rrSig.get(i[0]).doubleValue() < rrHighUThres) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighUThres) {
                            String rr = "Very High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning ends: " + instant.toString());
                            pat.alertHistoryRR.add("Urgent starts: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() < rrHighWThres && pat.rrSig.get(i[0]).doubleValue() > rrLowWThres) {
                            pat.alertStatus = "Healthy";
                            pat.rrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning ends: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowWThres && pat.rrSig.get(i[0]).doubleValue() > rrLowUThres) {
                            String statement = "Low Respiratory Rate";
                            pat.abnormalDetails.add(statement);
                            pat.alertStatus = "Warning";
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowUThres) {
                            String statement = "Very Low Respiratory Rate";
                            pat.abnormalDetails.add(statement);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning ends: " + instant.toString());
                            pat.alertHistoryRR.add("Urgent Start: " + instant.toString());
                        }

                    } else if (pat.rrFlag == "U") {                     // C. If patient is having urgent rr previously
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighWThres && pat.rrSig.get(i[0]).doubleValue() < rrHighUThres) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryRR.add("Warning starts: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() >= rrHighUThres) {
                            String rr = "Very High Heart Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() < rrHighWThres && pat.rrSig.get(i[0]).doubleValue() > rrLowWThres) {
                            pat.alertStatus = "Healthy";
                            pat.rrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent ends: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowWThres && pat.rrSig.get(i[0]).doubleValue() > rrLowUThres) {
                            String statement = "Low Respiratory Rate";
                            pat.abnormalDetails.add(statement);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent ends: " + instant.toString());
                            pat.alertHistoryRR.add("Warning starts: " + instant.toString());
                        }
                        if (pat.rrSig.get(i[0]).doubleValue() <= rrLowUThres) {
                            String statement = "Very Low Respiratory Rate";
                            pat.abnormalDetails.add(statement);
                            pat.alertStatus = "Urgent";
                        }
                    }
                }

                // Update the alertStatus of each patient
                for (Patient pat:patientList){
                    if (pat.tempFlag == "U" | pat.hrFlag == "U" | pat.rrFlag == "U"){
                        // Urgent if there is any "U" flag
                        pat.alertStatus = "Urgent";
                    }
                    if (pat.tempFlag != "U" && pat.hrFlag != "U" && pat.rrFlag != "U" && (pat.tempFlag == "W" | pat.hrFlag == "W" | pat.rrFlag == "W")){
                        // Warning if there is no "U" flag but any "W" flag
                        pat.alertStatus = "Warning";
                    }
                    // If both cases are not satisfied, the patient will remain "Healthy" status, as reset at the start of the method
                }

                i[0]++;     // counter++ to get the next data point of the signal next time
            }
        };

        timer.schedule(alertCheck, 0, 1000);    // TimerTask alertCheck is scheduled without delay and repeated at every 1000ms (1s)
    }

}

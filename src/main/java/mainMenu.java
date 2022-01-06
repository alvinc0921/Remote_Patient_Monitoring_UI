import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public interface mainMenu {
    
    static void realTimeAlertChecker(ArrayList<Patient> patientList) {
        // Setting the threshold values
        double tempHighUThres = 40.0;
        double tempHighWThres = 37.6;
        double tempLowWThres = 35.9;
        double tempLowUThres = 35.0;

        double hrHighUThres = 130.0;
        double hrHighWThres = 101.0;
        double hrLowWThres = 59.0;
        double hrLowUThres = 40.0;

        double rrHighUThres = 24.0;
        double rrHighWThres = 19.9;
        double rrLowWThres = 11.9;
        double rrLowUThres = 11.0;


        int duration = patientList.get(0).length;       // need this cuz now the data is small, if 24h data do not need to cancel the timer cuz it will keep looping
        final int[] i = {0};

        for (Patient pat : patientList) {
            pat.tempFlag = "H";
            pat.hrFlag = "H";
            pat.rrFlag = "H";
        }

        Clock clock = Clock.systemDefaultZone();

        Timer timer = new Timer();
        TimerTask alertCheck = new TimerTask() {

            @Override
            public void run() {

                for (Patient pat : patientList) {

                    pat.alertStatus = "Healthy";
                    pat.abnormalDetails.clear();

                    // TEMPERATURE
                    if (pat.tempFlag == "H") {
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighWThres && pat.tempSig.get(i[0]).doubleValue() < tempHighUThres) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning Start: " + instant.toString());
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
                    } else if (pat.tempFlag == "W") {
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighWThres && pat.tempSig.get(i[0]).doubleValue() < tempHighUThres) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                        }
                        if (pat.tempSig.get(i[0]).doubleValue() >= tempHighUThres) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: " + instant.toString());
                            pat.alertHistoryTemp.add("Urgent starts: " + instant.toString());
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
                    } else if (pat.tempFlag == "U") {
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


                    // HEART RATE
                    if (pat.hrFlag == "H") {
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
                    } else if (pat.hrFlag == "W") {
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
                    } else if (pat.hrFlag == "U") {
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


                    // RESPIRATORY RATE
                    if (pat.rrFlag == "H") {
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

                    } else if (pat.rrFlag == "W") {
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
                    } else if (pat.rrFlag == "U") {
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

                for (Patient pat:patientList){
                    if (pat.tempFlag == "U" | pat.hrFlag == "U" | pat.rrFlag == "U"){
                        pat.alertStatus = "Urgent";
                    }
                    if (pat.tempFlag != "U" && pat.hrFlag != "U" && pat.rrFlag != "U" && (pat.tempFlag == "W" | pat.hrFlag == "W" | pat.rrFlag == "W")){
                        pat.alertStatus = "Warning";
                    }
                }
                i[0]++;

                // Do not need the following if condition for 24h data:
                if (i[0] == duration-1) {
                    timer.cancel();
                }

            }
        };
        timer.schedule(alertCheck, 0, 1000);

    }


/*
Trying to write a function for that but problem:
It can only read the first second, cannot update patient's flag for some reason

    static void realTimeAlertChecker(ArrayList<Patient> patientList) {
        // Setting the threshold values
        double tempHighUThres = 40.0;
        double hrHighUThres = 130.0;
        double rrHighUThres = 25.0;

        double tempHighWThres = 38.0;
        double hrHighWThres = 110.0;
        double rrHighWThres = 20.0;

        double tempLowUThres = 34.0;
        double hrLowUThres = 50.0;
        double rrLowUThres = 5.0;

        double tempLowWThres = 35.0;
        double hrLowWThres = 60.0;
        double rrLowWThres = 8.0;

        int duration = patientList.get(0).length;       // need this cuz now the data is small, if 24h data do not need to cancel the timer cuz it will keep looping
        final int[] i = {0};

        for (Patient pat:patientList){
            pat.tempFlag = "H";

            pat.hrFlag = "H";

            pat.rrFlag = "H";
        }

        Clock clock = Clock.systemDefaultZone();

        Timer timer = new Timer();
        TimerTask alertCheck = new TimerTask(){

            @Override
            public void run() {

                for (Patient pat:patientList) {

                    pat.alertStatus = "Healthy";
                    pat.abnormalDetails.clear();


                    String flagCheckTemp = thresholdCheck(tempHighWThres, tempHighUThres, tempLowWThres, tempLowUThres,
                            pat, i, "Temperature", clock);
                    System.out.println(flagCheckTemp);
                    pat.tempFlag = flagCheckTemp;

                    String flagCheckHR = thresholdCheck(hrHighWThres, hrHighUThres, hrLowWThres, hrLowUThres,
                            pat, i, "Heart Rate", clock);
                    pat.hrFlag = flagCheckHR;

                    String flagCheckRR = thresholdCheck(rrHighWThres, rrHighUThres, rrLowWThres, rrLowUThres,
                            pat, i, "Respiratory Rate", clock);
                    pat.rrFlag = flagCheckRR;

                }
                i[0]++;

                // Do not need the following if condition for 24h data:
                if (i[0] == duration){
                    timer.cancel();
                }

            }
        };
        timer.schedule(alertCheck, 0,1000);

    }


    static String thresholdCheck(double highW, double highU, double lowW, double lowU,
                                  Patient pat, final int[] i, String vital, Clock clock){

        String patFlag = new String();
        String patFlagAfter = new String();
        double patSignal = pat.temp[i[0]];
        ArrayList<String> patAlertHist = new ArrayList<>();

        if (vital == "Temperature"){
            patFlag = pat.tempFlag;
            patSignal = pat.temp[i[0]];
            patAlertHist = pat.alertHistoryTemp;
        }
        else if (vital == "Heart Rate"){
            patFlag = pat.hrFlag;
            patSignal = pat.hr[i[0]];
            patAlertHist = pat.alertHistoryHR;
        }
        else if (vital == "Respiratory Rate"){
            patFlag = pat.rrFlag;
            patSignal = pat.rr[i[0]];
            patAlertHist = pat.alertHistoryRR;
        }

        if (patFlag == "H"){
            if (patSignal >= highW && patSignal <= highU) {
                String statement = "High " + vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
                patFlagAfter = "W";
                Instant instant = clock.instant();
                patAlertHist.add("Warning Start: "+ instant.toString());
            }
            if (patSignal >= highU) {
                String statement = "Very High "+ vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
                patFlagAfter = "U";
                Instant instant = clock.instant();
                patAlertHist.add("Urgent Start: "+instant.toString());
            }
            if (patSignal <= lowW && patSignal >= lowU) {
                String statement = "Low "+ vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
                patFlagAfter = "W";
                Instant instant = clock.instant();
                patAlertHist.add("Warning Start: "+instant.toString());
            }
            if (patSignal <= lowU) {
                String statement = "Very Low "+vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
                patFlagAfter = "U";
                Instant instant = clock.instant();
                patAlertHist.add("Urgent Start: "+instant.toString());
            }
        }

        else if (patFlag == "W"){
            if (patSignal >= highW && patSignal <= highU) {
                String statement = "High "+vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
            }
            if (patSignal >= highU) {
                String statement = "Very High "+vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
                patFlagAfter = "U";
                Instant instant = clock.instant();
                patAlertHist.add("Warning ends: "+ instant.toString());
                patAlertHist.add("Urgent starts: "+ instant.toString());
            }
            if (patSignal <= highW && patSignal >= lowW) {
                pat.alertStatus = "Healthy";
                patFlagAfter = "H";
                Instant instant = clock.instant();
                patAlertHist.add("Warning ends: " +instant.toString());
            }
            if (patSignal <= lowW && patSignal >= lowU) {
                String statement = "Low "+ vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
            }
            if (patSignal <= lowU) {
                String statement = "Very Low "+vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
                patFlagAfter = "U";
                Instant instant = clock.instant();
                patAlertHist.add("Warning ends: "+ instant.toString());
                patAlertHist.add("Urgent Start: "+instant.toString());
            }
        }
        else if (patFlag == "U"){
            if (patSignal >= highW && patSignal <= highU) {
                String statement = "High "+ vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
                patFlagAfter = "W";
                Instant instant = clock.instant();
                patAlertHist.add("Urgent ends: "+ instant.toString());
                patAlertHist.add("Warning starts: "+ instant.toString());

            }
            if (patSignal >= highU) {
                String statement = "Very High " + vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
            }
            if (patSignal <= highW && patSignal >= lowW) {
                pat.alertStatus = "Healthy";
                patFlagAfter = "H";
                Instant instant = clock.instant();
                patAlertHist.add("Urgent ends: " +instant.toString());
            }
            if (patSignal <= lowW && patSignal >= lowU) {
                String statement = "Low "+ vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Warning";
                patFlagAfter = "W";
                Instant instant = clock.instant();
                patAlertHist.add("Urgent ends: "+ instant.toString());
                patAlertHist.add("Warning starts: "+ instant.toString());

            }
            if (patSignal <= lowU) {
                String statement = "Very Low "+vital;
                pat.abnormalDetails.add(statement);
                pat.alertStatus = "Urgent";
            }
        }

        return patFlagAfter;

    }

    static void updateFlag(String flag, String vital, Patient pat){
        if (vital == "Temperature"){
            pat.tempFlag = flag;
        }
        else if (vital == "Heart Rate"){
            pat.hrFlag = flag;
        }
        else if (vital == "Respiratory Rate"){
            pat.rrFlag = flag;
        }
    }

 */

}

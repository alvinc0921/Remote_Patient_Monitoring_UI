# Remote Patient Monitoring App Project

This desktop Java application was developed by Andreea Cojocea, Alvin Chen, Dorothy Cheng, Nicolas Calvo Peiro and Samuel Oliveira as part of the Programming 3 course in the Department of Bioengineering at Imperial College London. 

The project is titled "Remote Patient Monitoring". The main aim was to create an application that displays patients’ vital signs in real time, so that a busy doctor can easily access them remotely. The app focuses on 5 vital signs: ECG, blood pressure, temperature, heart rate and respiratory rate. 

The front-end was mainly built using SWING. The patient data and vital signs were stored in a PostgreSQL database hosted on Heroku. 

https://data.heroku.com/dataclips/bfljwdrcpssefebonywlxfskssvx 

We recommend cloning the code to IntelliJ IDEA and running the main class on it. Please give 10 to 20 seconds for the application to open. Notice how there is a warning alert for the first 5 seconds, and suddenly a new urgent alert appears (due to patient Marjorie).

Current version: 

v1.3 : Small UI Updates. Final version.

Past versions: 

v1.2 : 3rd and 4th Patients added. Matlab code added to repository.

v1.1 : Small UI updates.

v1.0 : First version meeting all client requirements.

In our current database, we have 4 patients. Patient 1 (Petko Adello) and 4 (Evelyn Brookes) are healthy, and thus do not trigger any warnings. Patient 2 (Marjorie Finlay) has normal vital signs for the first 5 seconds that the app is open, but then suffers from ventricular fibrillation, triggering multiple urgent alerts. Patient 3 (Nils Sjoberg) triggers a warning alert from the beginning of the app due to high body temperature, but more vital signs turn abnormal within 10/15 seconds(this patient suffers from atrial flutter, represented in his ECG). These two last patients show our app reacts to abnormal vital signs, differentiating between warning and urgent alerts.

## Application Flow 

The app has 3 main windows. When the app is opened, the emergency window is displayed. At all times, on the left side, the user can choose between the 3 windows. When the app starts, a clock is initiated that reads through the data to simulate real-time data collection. The ultimate goal of our app is to take in this data, and alert the doctor if anything in the data shows the patient might be unwell. 

### Emergency Window 

This window shows the doctor which patients require attention, with the severity of their situation being divided between “warning” and “urgent” cases. There are slight flashes, a beeping sound and an email alarm (sent to the doctor) whenever a patient is determined to be one of these cases. 

The app has built-in threshold values for body temperature, heart rate and respiratory rate that trigger either “warning” or “urgent” alerts. 

### Ward Window 

This window displays the different patients in the ward, with their details. When the doctor selects a specific patient, a window opens with the 5 vital signs plotted for this patient. As required by the client, the doctor can select the period of time for the signals to be plotted over. 

### Report Generation Window 

This window displays a list of the patients in the ward. When the doctor selects a patient, they are given the choice to either generate a detailed report with the average vital sign values (per minute) for the last 24h(as well as any alerts related to the patient). The doctor is also given the choice to check past reports generated for that patient. 


## Data

The data for ECG was obtained from  https://data.mendeley.com/datasets/7dybx7wyfn/3

The data for Radial Blood Pressure was obtained from http://haemod.uk/original

Due to the high frequencies at which these signals are recorded, we reduced their number of points per second so as to ease storage of data for 24h of signal(see MATLAB folder in this repository). While this reduced the quality of the signal, we believe we found a good compromise between storage and quality of the signal.

As for the other vital signs (heart rate, respiratory rate and body temperature), we entirely simulated these for 24h, both for healthy and unhealthy patients.

%%PROGRAM TO SIMULATE BLOODPRESSURE DATA OF A HEALTHY PATIENT FOR 24H
close all %clears everything
clear all
clc
%% LOADING AND CREATING THE DATA

load('Radial_physio.mat') %taken from 
        %format of loaded data contains the blood pressure data and their timepoints
time1=RADIAL_PHYSIO{1,2}.MORE_CYCLES(:,1);   %dummy to preallocate space
loopnumber=4;       %determines how many times the blood pressure is appended
appended_time=zeros(numel(time1)*(loopnumber),1); 
appended_pressure=zeros(numel(time1)*(loopnumber),1);

for i=1:loopnumber %creates base sample bloodpressure data with loopnumber being the amount of samples taking from Radial_physio.mat
temptime=[];
temppressure=[];
if i==1 % for indexing purposes later
    u=1;
else
    u=0;
end
temptime=RADIAL_PHYSIO{1,i+1}.MORE_CYCLES(:,1);  %temporarily stores the time
temppressure=RADIAL_PHYSIO{1,i+1}.MORE_CYCLES(:,2); %temporarily stores the pressure

tcycle=numel(temptime);

appended_time((i-1)*tcycle+1:(i-1)*tcycle+tcycle,1)=temptime+appended_time((i-1)*tcycle+u); % appends the time to the last one with an offset to account for the already elapsed time

appended_pressure((i-1)*tcycle+1:(i-1)*tcycle+tcycle,1)=temppressure-(temppressure(1)-appended_pressure((i-1)*tcycle+u)); %adds the pressure to the last one with an offset to account for the previous pressure's offset

end

%% EXTENDING BASE PRESSURE

loopnumber2=2;
for j=1:loopnumber2
    last_length = length(appended_pressure);
 appended_pressure(last_length+1:2*last_length)=appended_pressure; %extends the pressure from before by repeating it twice

 appended_time(last_length+1:2*last_length)=appended_time(last_length)+appended_time; %same with time
end

%% REDUCING THE DENSITY OF THE DATA FOR EASIER HANDLING

densityreductionfactor=8;  %This factor has the biggest impact, scales higher than linearly, arbitrary value
timepointsperloop = 40000; %This factor has impact but relative impact decreases as this factor is increased, arbitrary value
for p=1:densityreductionfactor

temptimepoints=[];
temptimepoints=randi([1 numel(appended_pressure)],1,timepointsperloop); %creates random timepoints to remove the data

timepoints = [];
n=1;
for i=1:numel(temptimepoints) %removes duplicate timepoints
    if ~(ismember(temptimepoints(i),timepoints))
        timepoints(n)=temptimepoints(i);
        n=n+1;
    end
end

timepoints=sort(timepoints); %sorts timepoints in chronological order

for j=1:numel(timepoints) %removal of the data at the randomly created timepoints
    if timepoints(j)<numel(appended_pressure) %checks that the timepoint selected is defined in the array (after creating the timepoints, we delete part of the array so its possible that some timepoints are outside the range of it now
        appended_pressure=appended_pressure([1:timepoints(j)-1,timepoints(j)+1:end]); %deletes timepoint's associated data
        appended_time=appended_time([1:timepoints(j)-1,timepoints(j)+1:end]); %deletes timepoint
   end
end

end

%% PLOTTING AND ADJUSTING
appended_pressure=transpose(appended_pressure); %for plotting purposes
appended_pressure=0.00750062*appended_pressure+100; %rescaling to have values within physiological range
appended_time=transpose(appended_time); %for plotting purposes
figure;
plot(appended_time,appended_pressure); %plot to check the sample is correct

%% EXTENDING FOR 24h    
number_of_reps=24*3600/appended_time(end);%number of times we need to repeat to make it last 24h
pressure24h=[];
time24h=[];
for k=1:round(number_of_reps) %extends it for 24h
    pressure24h=[pressure24h,appended_pressure];
end
figure %plots pressure
pressure24h=round(pressure24h,1);
plot(pressure24h)
%% EXPORTING THE DATA

%UNCOMMENT IF NEED TO EXPORT
%writematrix(pressure24h,'bp.csv'), writes csv file to be used in database

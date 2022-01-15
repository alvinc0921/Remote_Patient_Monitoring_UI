%%PROGRAM TO SIMULATE ECG OF HEALTHY PATIENT DURING 24H
close all %clears everything
clear all
clc

%% LOADING AND CREATING THE DATA

sig1=load('100m (0).mat'); %loads downloaded data from
sig2=load('100m (1).mat');
sig3=load('100m (2).mat');
sig4=load('100m (3).mat');
sig5=load('100m (4).mat');
sig6=load('100m (5).mat');
sig7=load('100m (6).mat');
sig8=load('100m (7).mat');
sig9=load('100m (8).mat');

time_ax=0:1/360:(length(sig1.val)-1)/360; 
plot(time_ax,sig1.val) %Plots to see what the ECG looks like


signal=[sig1.val,sig2.val,sig3.val,sig4.val,sig5.val,sig6.val,sig7.val,sig8.val,sig9.val]; %Appends the 9 signals taken into 1 ecg

for i=1:numel(signal)-1
    derivativess(i)=signal(i+1)-signal(i); %calculates the derivative values of the signal for later filtering
end

figure;
plot(1:numel(derivativess),abs(derivativess)); %plots derivative

figure; 
densityreductionfactor=5;  %This factor has the biggest impact, scales higher than linearly, arbitrary value
timepointsperloop = 30000; %This factor has impact but relative impact decreases as this factor is increased, arbitrary value

for p=1:densityreductionfactor

temptimepoints=[];
temptimepoints=randi([1 numel(signal)],1,timepointsperloop); %creates random timepoints to remove the data

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
    if timepoints(j)<numel(signal) %checks that the timepoint selected is defined in the array (after creating the timepoints, we delete part of the array so its possible that some timepoints are outside the range of it now
        if derivativess(timepoints(j))<7.5 && signal(timepoints(j))<1170 %This allows for better filtering (keeping peaks and high frequency compontents)
            signal=signal([1:timepoints(j)-1,timepoints(j)+1:end]); %deletes timepoint's associated data
        end
   end
end

end



leng_signal=length(signal); 
time_ax_signal=0:1/360:(leng_signal-1)/360;

plot(time_ax_signal,signal) %% checks how the signal has changed with plotting
xlim([0.5 1.5]);

%% Trying to simulate signal for 24h
%Each signal above is defined for 90 s

figure
signal_repetitions=3600*24/(32400/350);  %counts the number of repetitions needed to get 24h
signal_24h=[];
for i=1:ceil(signal_repetitions)
    signal_24h=horzcat(signal_24h,signal); %repeats the signal over itself to get a 24h one
end
leng_signal_24h=length(signal_24h);
time_ax_signal_24h=0:1/360:(leng_signal_24h-1)/360;
plot(time_ax_signal_24h,signal_24h) %plots it to check everything is good

%% EXPORTING THE DATA

%UNCOMMENT IF NEED TO EXPORT
%writematrix(signal_24h','ecg24h.csv')
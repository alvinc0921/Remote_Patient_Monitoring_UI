%%PROGRAM TO CREATE HEALTHY/UNHEALTHY (values can be changed) RANDOM RESPIRATORY RATE
clear all; %clears all
close all;

%% PARAMETERS
%ALL OF THESE ARE CHOSEN ARBITRARILY TO HAVE VALUES WITHIN PHYSIOLOGICAL RANGE

bigloopfactor=100; %determines length of signal
bigoffset=zeros(1,bigloopfactor); %keeps the offset of each iteration
bigoffset(1)=0;
averagevalue=21; %average respiratory healthy rate
timepointsforoffset=10; %amount of timepoints for offsets
offset_scaling_factor = 1; %the offset's scaling factor
limitofslope=0.01; %limits the slope of offsets, you don't want them to be too high in magnitude or it won't be realistic
sublength=864; %length of subsignals;
lowervaluebound=17; %lower bound of where you want your signal to be, NOTE : it will slip off slightly away from this value
uppervaluebound=22; %upper bound of where u want ur signal to be, NOTE : it will slip off slightly away from this value
quantification_factor=0; %decimals kept when quantifying the signal
flag1=false;
randvar=0;
noisefactor=1;

%% CREATES THE SUBSIGNALS
for o=1:bigloopfactor %creates bigloopfactor subsignals
    %%Creates healthy signal with noise
    values=averagevalue*ones(1,sublength); 
%     for i=1:numel(values)
%         valueswithnoise(i)=values(1,i)+bigoffset(o);
%         if mod(i,5)==0 && flag1==0
%             flag1=1;
%         else if mod(1,5)==0 && flag1==1
%                 flag1=0;
%         else flag1=0;
%         end
%         end
%         if flag1
%             randvar=rand(1,1);
%         else if mod(i,5)==0
%                 randvar=0;
%         end
%         end
%         valueswithnoise(i)=valueswithnoise(i)+randvar;
%     end
    for i=1:numel(values)
        valueswithnoise(i)=values(1,i)+noisefactor*rand(1,1)+bigoffset(o);
    end
    %%Creates timepoints for offsets
    temptimepoints=randi([1 sublength],1,timepointsforoffset); %creates randomly placed offset timepoints
    timepoints = [];
    n=1;
    for i=1:numel(temptimepoints) %checks for duplicates
        if ~(ismember(temptimepoints(i),timepoints))
            timepoints(n)=temptimepoints(i);
            n=n+1;
        end
    end
    
    timepoints=sort(timepoints); %orders timepoints chronologically
    
    idx=1;
    for i=1:numel(timepoints) %%puts timepoints in pairs for more organised code later since the offset is between two points
        if mod(i,2)==1 && i<numel(timepoints)
            timepointpairs(idx,1)=timepoints(i);
            timepointpairs(idx,2)=timepoints(i+1);
            idx=idx+1;
        end
    end
    
    %%Applies offset to signal with noise
    overalloffset(1)=0; %keeps count of previous iterations' offsets
    valueswithoffset=zeros(1,numel(valueswithnoise));
    for i=1:numel(timepointpairs(:,1)) %% applies offsets
    
       offset=offset_scaling_factor*rand(1,1); %% creates random scaled offset
       offset=offset*sign(offset_scaling_factor/2-offset); %% controls sign of offset

        while offset/(timepointpairs(i,2)-timepointpairs(i,1)) > limitofslope || offset/(timepointpairs(i,2)-timepointpairs(i,1)) < -limitofslope %limits slope to have realistic signals
            offset=offset_scaling_factor*rand(1,1);
            offset=offset*sign((offset_scaling_factor*0.5)-offset); %% controls sign of offset
        end
    
        if (overalloffset(i)+valueswithnoise(timepointpairs(i,1))<lowervaluebound && offset<0) || (overalloffset(i)+valueswithnoise(timepointpairs(i,1))>uppervaluebound && offset>0) %controls that we stay within desired values most of the time
            offset=-offset;
        end
    
        for j=timepointpairs(i,1):timepointpairs(i,2) %applies offset as a linear increase (offset/timerange) for each datapoint in the timerange and keeps track of previous offsets
            valueswithoffset(j)=overalloffset(i)+valueswithnoise(j)+(j+1-timepointpairs(i,1))*(offset/(timepointpairs(i,2)-timepointpairs(i,1)));
        end
            overalloffset(i+1)=overalloffset(i)+offset; %keeps track of all the offsets to avoid discontinuities in the signal
    end
    u(1)=1;

    for i=1:numel(valueswithoffset) 
        for b=1:numel(timepointpairs(:,1)) %keeps track of where we are in time
            if timepointpairs(b,1)<i && timepointpairs(b,2)>i
                u(1)=b+1;
            end
        end
        if valueswithoffset(i)==0 %where there is no offset
            valueswithoffset(i)=valueswithnoise(i)+overalloffset(u(1)); %appends the signal with offsets to the addition of the signal with noise the overall offset at each timepoint to have a final signal with parts that have offsets and parts that don't
        end
    end
    
    for i=1:numel(valueswithoffset)
        valueswithoffset(i)=round(valueswithoffset(i),quantification_factor); %quantifies to the desired decimal place
    end

    bigoffset(o+1)=-averagevalue+valueswithoffset(numel(valueswithoffset)); %keeps the offset of the overall subsignal
    wholesignal(1,1+(o-1)*numel(valueswithoffset):o*numel(valueswithoffset))=valueswithoffset; %puts the subsignal at the end of wholesignal to create the full wanted signal
end
%% PLOTTING
figure;
plot(1:numel(wholesignal),wholesignal); %plots wholesignal signal to check if its wanted
xlim([1 200]);
ylim([10 22]);

figure;
plot(1:numel(wholesignal),wholesignal);
%% EXPORTING THE DATA

%UNCOMMENT IF NEED TO EXPORT BUT RECOMMENDED WRITING NEXT LINE IN COMMAND WINDOW
%OR RUNNING THIS CODE BY SECTIONS TO ALLOW CHECKING OF THE DATA BEFORE EXPORTING

%writematrix(wholesignal,'respiratory_rate.csv'), writes csv file to be used in database
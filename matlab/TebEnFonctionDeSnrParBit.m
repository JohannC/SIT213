clear all
close all

info = csvread('../simuTEBNRZ.csv', 0, 0);
snr = info(:, 1);

data = csvread('../simuTEBNRZ.csv', 0, 1);
data(1, :);
TEB = zeros(1, length(snr));
p = length(snr)-1;
for i = 1:length(snr)
    %Moyenne = filter(ones(1,p)/p,1,DataContent(i, :));
    TEB(i) = mean(data(i, :));
end    


figure(1)

semilogy(snr, TEB);
grid on;

title (sprintf('TEB en Fonction de SNR Par Bit (code de ligne : NRZ)')) ;
xlabel('SRN PAR BIT');
ylabel('TEB');


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

info = csvread('../simuTEBNRZT.csv', 0, 0);
snr = info(:, 1);

data = csvread('../simuTEBNRZT.csv', 0, 1);
data(1, :);
TEB = zeros(1, length(snr));
p = length(snr)-1;
for i = 1:length(snr)
    %Moyenne = filter(ones(1,p)/p,1,DataContent(i, :));
    TEB(i) = mean(data(i, :));
end    


figure(2)

semilogy(snr, TEB);
grid on;

title (sprintf('TEB en Fonction de SNR Par Bit (code de ligne : NRZT)')) ;
xlabel('SRN PAR BIT');
ylabel('TEB');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

info = csvread('../simuTEBRZ.csv', 0, 0);
snr = info(:, 1);

data = csvread('../simuTEBRZ.csv', 0, 1);
data(1, :);
TEB = zeros(1, length(snr));
p = length(snr)-1;
for i = 1:length(snr)
    %Moyenne = filter(ones(1,p)/p,1,DataContent(i, :));
    TEB(i) = mean(data(i, :));
end    


figure(3)

semilogy(snr, TEB);
grid on;

title (sprintf('TEB en Fonction de SNR Par Bit (code de ligne : RZ)')) ;
xlabel('SRN PAR BIT');
ylabel('TEB');
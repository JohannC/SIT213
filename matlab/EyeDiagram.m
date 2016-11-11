clear all
close all

info = csvread('../simuTEBNRZ.csv', 0, 0);
snr = info(:, 1);

data = csvread('../simuTEBNRZ.csv', 0, 1);
data(1, :);
TEB_average = zeros(1, length(snr));
p = length(snr)-1;
for i = 1:length(snr)
    %Moyenne = filter(ones(1,p)/p,1,DataContent(i, :));
    TEB_average(i) = mean(data(i, :));
end    


figure(1)

semilogy(snr, TEB_average);
grid on;

title (sprintf('TEB en Fonction de SNR Par Bit (code de ligne : NRZ)')) ;
xlabel('SRN PAR BIT');
ylabel('TEB');


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
reception = [0 0 0 1 2 3 4 3 2 1 0 0 0 0 0 0 4 3 2 1 2 3 4 0 0 0 0 0 0 1 2 3 4 3 2 1 0 0 0 0 0 0 4 3 2 1 2 3 4 0 0 0];
Ts = 30;

oeil=reshape(reception,Ts,length(reception)/Ts);
figure(1)
plot(oeil)
xlabel('Time');
title('Eye pattern');
grid;
hold off
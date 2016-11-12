clear all
close all

info = csvread('../NRZ-10000-nb30-ampl_-2_2-snr10.csv', 0, 0);
% reception = info(:, 1);

% data = csvread('../simuTEBNRZ.csv', 0, 1);
% data(1, :);
reception = zeros(1, 30000);
% p = length(reception)-1;

for i = 1:30000
    reception(i) = info(i);
end    


% figure(1)
% 
% semilogy(snr, TEB_average);
% grid on;
% 
% title (sprintf('TEB en Fonction de SNR Par Bit (code de ligne : NRZ)')) ;
% xlabel('SRN PAR BIT');
% ylabel('TEB');


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%reception = [0 0 0 1 2 3 4 3 2 1 0 0 0 0 0 0 4 3 2 1 2 3 4 0 0 0 0 0 0 1 2 3 4 3 2 1 0 0 0 0 0 0 4 3 2 1 2 3 4 0 0 0];





Ts = 30;
figure(5)
plot(reception)

%oeil=reshape(reception,Ts,length(reception)/Ts);
oeil = eyediagram(reception,90,60);
axis([-30,30,-5,5])
% figure(1)
% %plot(oeil)
% axis([0,30,-2,2])
% xlabel('Time');
% title('Eye pattern');
% grid;
% hold off







% RZ-10000-5000-0_7-snr0.csv
% RZ-10000-5000-0_7-snr-10.csv
% RZ-10000.csv
% RZ-10000-snr0.csv

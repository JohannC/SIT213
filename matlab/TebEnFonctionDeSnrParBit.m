info = csvread('../simuTEB.csv', 0, 0);
snr = info(:, 1);

data = csvread('../simuTEB.csv', 0, 1);
data(1, :);
TEB_average = zeros(1, length(snr));
p = length(snr)-1;
for i = 1:length(snr)
    %Moyenne = filter(ones(1,p)/p,1,DataContent(i, :));
    TEB_average(i) = mean(data(i, :));
end    


figure(2)

semilogy(snr, TEB_average);
grid on;

title (sprintf('TEB en Fonction de SNR Par Bit')) ;
xlabel('SRN PAR BIT');
ylabel('TEB');
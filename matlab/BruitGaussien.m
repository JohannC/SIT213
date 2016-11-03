
%% %% Vérification : e bruit généré suit une loi gaussienne (histogramme) %% %%
%% %%%%%%%%%%%%%%%%%%%% COPYRIGHT© : Lahoucine AMHOUCHE %%%%%%%%%%%%%%%%%%%% %%

mu = 0; % la moyenne de la gaussienne 

% sigma = sqrt(var(B(:,20)));

sigma = 5; % Ecart-type

%% Rappel : SNR : Puissance moyenne du signal / Puissance moyenne du bruit (linéaire) 

x = 0:0.1:100000;
pure = 2.*x+5;

a1 = rand(1,length(pure)); % generer des valeurs suivant une loi uniforme 
a2 = rand(1,length(pure)); % generer des valeurs suivant une loi uniforme 

%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x1 = -30:1:30;
amp_gaussienne = 990000;
pdf_th = (amp_gaussienne/(sqrt(2*pi*sigma^2))) * exp(-(x1-mu).^2/(2*sigma^2));

b = sigma .* sqrt(-2.*log(1-a1)) .* cos(2*pi.*a2);

figure(10);
hist(b, 50);
hold on; 
plot(x1, pdf_th, 'r', 'LineWidth', 1);

xlabel('x');
ylabel('P(X = k)');
title(sprintf('Illustration : le bruit blanc gaussien suit bien une loi gaussienne \n %d tirages', (length(b)-1)));

legend ('le bruit blanc gaussien', ...
        'Loi Normale (0, sigma^2)', 'Location', 'NorthWest');
    
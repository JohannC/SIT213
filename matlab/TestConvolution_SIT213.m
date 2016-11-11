
signalAconvoler = zeros(1, 180);
reponseImpulsionnelle =  zeros(1, 30);

for i = 1:30
    signalAconvoler(i) = 1;
end 

for i = 31:60
    signalAconvoler(i) = 0;
end 

for i = 61:90
    signalAconvoler(i) = 0;
end 

for i = 91:120
    signalAconvoler(i) = 1;
end 

for i = 121:150
    signalAconvoler(i) = 1;
end 

for i = 151:180
    signalAconvoler(i) = 0;
end 

for i = 1:30
    reponseImpulsionnelle(i) = 1;
end 

reponseDuFiltre = conv2(signalAconvoler,reponseImpulsionnelle)

% ouvrir un fichier ou le créé
fid = fopen('resultatConvolution.txt','w');
% écrir dans ce fichier, fid est sa reference pour matlab
fprintf(fid,'float [] reponseDuFiltreMatlab = {');
fprintf(fid,'%if,',reponseDuFiltre);
fprintf(fid,'};');
% fermer le fichier
fclose(fid);

figure(1);
plot(reponseDuFiltre);
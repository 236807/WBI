function [] = saveToFile( name,strToSave )
name = strcat(name,'.txt');
fileID = fopen(name,'wt'); %Stworzenie pliku
fprintf(fileID,strToSave);
fclose('all');
end

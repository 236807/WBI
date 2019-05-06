function [matrix] = getSubstitudeMatrixFromFile(filename)
table = readtable(filename,'ReadRowNames',true);
seq1 = string(table.Properties.VariableNames);
seq2 = string(table.Properties.RowNames);
seq1 =seq1.';
% Zamiana tabeli csv na tablice
array = table2array(table); 
seq1 = strjoin(seq1,"");
seq2 = strjoin(seq2,"");
seq1 =char(seq1);
seq2 =char(seq2);
% Sprawdzenie d³ugoœci sekwencji
if ~(seq1 == seq2) 
    return 
end
%Sprawdzenie czy wiersze i kolumny zawieraj¹ tylko AGCTU lub AGCT
if (contains(seq1,'A') && contains(seq1,'G') && contains(seq1,'T')&& contains(seq1,'C')&& ...
        contains(seq1,'U')) == 1 || (contains(seq1,'A') && contains(seq1,'G') && contains(seq1,'T')&& contains(seq1,'C')) ==1 
   matrix = getSubstitudeMatrix(char(seq1),char(seq2),array);
  
end
end


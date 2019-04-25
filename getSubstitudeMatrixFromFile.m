function [matrix] = getSubstitudeMatrixFromFile(filename)
T = readtable(filename,'ReadRowNames',true);
seq1 = string(T.Properties.VariableNames);
seq2 = string(T.Properties.RowNames);
seq1 = transpose(seq1);

temp = table2array(T); % Zamiana tabeli csv na tablice
seq1 = strjoin(seq1);
seq2 = strjoin(seq2);

% Usuniêcie przerw
seq1 =char(seq1);
seq1 = erase(seq1," ");
seq2 =char(seq2);
seq2 = erase(seq2," ");
% Sprawdzenie d³ugoœci sekwencji
if ~(seq1 == seq2) 
    return 
end
%Sprawdzenie czy wiersze i kolumny zawieraj¹ tylko AGCTU lub AGCT
if (contains(seq1,'A','IgnoreCase',true) && contains(seq1,'G','IgnoreCase',true) && contains(seq1,'T','IgnoreCase',true)&& contains(seq1,'C','IgnoreCase',true)&& ...
        contains(seq1,'U','IgnoreCase',true)) || (contains(seq1,'A','IgnoreCase',true) && contains(seq1,'G','IgnoreCase',true) && contains(seq1,'T','IgnoreCase',true)&& contains(seq1,'C','IgnoreCase',true)) 
   matrix = getSubstitudeMatrix(char(seq1),char(seq2),temp);
  
end
end


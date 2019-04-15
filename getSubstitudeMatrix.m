function [str] = getSubstitudeMatrix(seq1,seq2,score)
str{1} = 'X';
str{1,2} = ' ';
str{2,1} = ' ';
str{2,2} = 0;
length(seq1);
for i=2 : length(seq1)+1
    for j=2 : length(seq2)+1
         str{1,j} = seq2(j-1); % Ustawienie pierwszego wiersza na ACGTU lub AGCT
         str{i,j} = (score(i-1,j-1));      
    end
   str{i,1} = seq1(i-1); % Ustawienie pierwszej kolumny na ACGTU lub AGCT   
end
end




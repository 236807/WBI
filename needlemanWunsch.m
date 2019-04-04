function [ score, matchMatrix, gaps, identity, Length , scoreMatrix ] = needlemanWunsch(seq1, seq2, match, mismatch, gap)
%% Tworzenie macierzy i zape³nienie jej
seq1_len = length(seq1);
seq2_len = length(seq2);
scoreMatrix = zeros(seq2_len+1, seq1_len+1);
scoreMatrix(1,2:end) = gap*(1:seq1_len); 
scoreMatrix(2:end,1) = gap*(1:seq2_len);

%% Kopia na macierz wsteczn¹ (Trace Back)
TB = repmat('x',size(scoreMatrix)); 
TB(1:end,1) = repmat('u',size(TB,1),1); 
TB(1,1:end) = repmat('l',1,size(TB,2));  
TB(1,1) = '0';
keys = {1,2,3};
%d= diagonal; u = up; l = left
values = {'d','u','l'}; 
M = containers.Map(keys,values);

%% Needleman-Wunsch Algorytm
%Macierz z punktacj¹ + przekazanie klucza do macierzy wstecznej
for i = 2 : size(scoreMatrix,1) 
    for j = 2 : size(scoreMatrix,2) 
        %case 1
        if seq2(i - 1) == seq1(j - 1)
            case1 = scoreMatrix(i - 1,j - 1) + match;
        else
            case1 = scoreMatrix(i - 1,j - 1) + mismatch;
        end
        
        %case 2
        case2 = scoreMatrix(i - 1,j) + gap;
        
        %case 3
        case3 = scoreMatrix(i,j - 1) + gap;
        %Tworzenie macierzy do znalezienia maximum i przypisania go w miejsce
        %wype³niane oraz wstawienie przejœcia z jakiego miejsca zosta³o
        %dokonane
        [max_score,max_score_idx] = max([case1 case2 case3]);
        scoreMatrix(i,j) = max_score;
        TB(i,j) = M(max_score_idx);   
    end
end

%% Trace back
%Znaleznienie optymalnej œcie¿ki
i = size(TB,1); 
j = size(TB,2);
tb=[]; 
while i>1 || j>1
    tb = [tb TB(i,j)];
    switch TB(i,j)
        case 'd'
            i = i-1; 
            j = j-1;
        case 'u'
            i = i-1;
        case 'l'
            j = j-1;
    end
end
tb = fliplr(tb); 
%% Dodanie przerw do macierzy
%Znalezienie indeksu macierzy gdzie wyst¹pi³ gap nastêpnie przepisanie
%starej sekwencji do nowej z - oraz wyci¹ganie iloœci wyst¹pieñ gap.
seq1_gap_idx = find(tb == 'u');
logical_idx = false(1,length(seq1) + length(seq1_gap_idx));
logical_idx(seq1_gap_idx) = true;
new_seq1 = nan(size(logical_idx));
new_seq1(~logical_idx) = seq1;
new_seq1 = char(new_seq1);
gap1=length(new_seq1(logical_idx));%wyci¹ganie d³ogoœci wys¹pieñ gap1
new_seq1(logical_idx) = '-';

seq2_gap_idx = find(tb=='l');
logical_idx = false(1, length(seq2) + length(seq2_gap_idx));
logical_idx(seq2_gap_idx) = true;
new_seq2 = nan(size(logical_idx));
new_seq2(~ logical_idx) = seq2;
new_seq2 = char(new_seq2);
gap2=length(new_seq2(logical_idx));%wyci¹ganie d³ogoœci wys¹pieñ gap2
new_seq2(logical_idx) = '-';



%Wstawienie podobieñstw w sekwencjach w odpowiednich miejscach
matched = repmat(' ',size(new_seq1));
matched(new_seq1 ~= '-' & new_seq2 ~= '-' & new_seq1 == new_seq2) = '|';
identity = length(matched(new_seq1 ~= '-' & new_seq2 ~= '-' & new_seq1 == new_seq2));
%% Wyniki
gaps=gap1+gap2;
matchMatrix=[new_seq1, newline, matched, newline, new_seq2];
score = scoreMatrix(end,end);
Length=length(tb);

end
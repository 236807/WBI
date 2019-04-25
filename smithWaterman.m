function [scoreMatrix,a,b] = smithWaterman(seq1,seq2,matrix,gap)
%% Tworzenie macierzy i zape³nienie jej
seq1_len = length(seq1);
seq2_len = length(seq2);
scoreMatrix = zeros(seq2_len+1, seq1_len+1);
scoreMatrix(1,2:end) = 0*(1:seq1_len); 
scoreMatrix(2:end,1) = 0*(1:seq2_len);


%% Smith - Waterman Algorytm
%Macierz z punktacj¹ + przekazanie klucza do macierzy wstecznej
for i = 2 : size(scoreMatrix,1) 
    for j = 2 : size(scoreMatrix,2) 
        %case 1
        match = checkNuclotide(seq2(i-1),seq1(j-1),matrix);
        case1 = scoreMatrix(i - 1,j - 1) + match;
        
        
        %case 2
        case2 = scoreMatrix(i - 1,j) + gap;
        
        %case 3
        case3 = scoreMatrix(i,j - 1) + gap;
        %Tworzenie macierzy do znalezienia maximum i przypisania go w miejsce
        %wype³niane oraz wstawienie przejœcia z jakiego miejsca zosta³o
        %dokonane
        max_score = max([case1 case2 case3 0]);
        scoreMatrix(i,j) = max_score;
        

    end
end
maximum = max(max(scoreMatrix));
[a,b]=find(scoreMatrix==maximum);
end
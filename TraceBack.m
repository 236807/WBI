function [road,matchMatrix, gaps, Length,score,new_seq1,new_seq2,identity,col,row,a,b] = TraceBack(scoreMatrix,seq1,seq2,matrix,gap,a,b)
A = [];

[m,n] = size(scoreMatrix);
road = zeros(m,n);
score = max(scoreMatrix(:));
c = (scoreMatrix(:)==score);
road(c) = NaN;
[row,col]= find(isnan(road));
row = row(1);
col = col(1);
while scoreMatrix(row,col)>0
        
     x = scoreMatrix(row,col);
     match = checkNuclotide(seq2(row-1),seq1(col-1),matrix);

     if scoreMatrix(row-1,col) == x - gap
        road(row-1,col) = NaN;
        row= row-1;
        A = [A 3];
     elseif scoreMatrix(row,col-1) == x - gap
        road(row,col-1) = NaN;
        col = col-1;
        A = [A 1];
        
     elseif scoreMatrix(row-1,col-1) == x - match
        road(row-1,col-1) = NaN;
        row = row-1;
        col = col-1;
        A = [A 2];
     end

end
A=fliplr(A);
%% Sequence Alignment

Seq1=seq1(col:b-1)
seq1_gap_idx = find(A == 3);
logical_idx = false(1,length(Seq1) + length(seq1_gap_idx));
logical_idx(seq1_gap_idx) = true;
new_seq1 = nan(size(logical_idx));
new_seq1(~logical_idx) = Seq1;
new_seq1 = char(new_seq1);
gap1=length(new_seq1(logical_idx));%wyci¹ganie d³ogoœci wys¹pieñ gap1
new_seq1(logical_idx) = '-';

Seq2=seq2(row:a-1)
seq2_gap_idx = find(A==1);
logical_idx = false(1, length(Seq2) + length(seq2_gap_idx));
logical_idx(seq2_gap_idx) = true;
new_seq2 = nan(size(logical_idx));
new_seq2(~ logical_idx) = Seq2;
new_seq2 = char(new_seq2);
gap2=length(new_seq2(logical_idx));%wyci¹ganie d³ogoœci wys¹pieñ gap2
new_seq2(logical_idx) = '-';

%Wstawienie podobieñstw w sekwencjach w odpowiednich miejscach
matched = repmat(' ',size(new_seq1));
matched(new_seq1 ~= '-' & new_seq2 ~= '-' & new_seq1 == new_seq2) = '|';
identity = length(matched(new_seq1 ~= '-' & new_seq2 ~= '-' & new_seq1 == new_seq2));
%% Wyniki
matchMatrix=[new_seq1,newline,matched,  newline, new_seq2];
Length=length(road);
gaps=gap1 + gap2;
end



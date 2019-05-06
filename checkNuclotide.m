function [match] = checkNuclotide(seq1 , seq2 , matrix)
seq1_len = length(seq1);
seq2_len = length(seq1);
for i = 1:seq1_len
    for j = 1:seq2_len
        if(seq1(i) == 'A' && seq2(j) == 'A')
            match = matrix(2,2);
        
        elseif(seq1(i) == 'A' && seq2(j) == 'C')
            match = matrix(2,3);
        
        elseif(seq1(i) == 'A' && seq2(j) == 'T')
            match = matrix(2,4);
        
        elseif(seq1(i) == 'A' && seq2(j) == 'G')
            match = matrix(2,5);
        
        elseif(seq1(i) == 'A' && seq2(j) == 'U')
            match = matrix(2,6);
        
        elseif(seq1(i) == 'C' && seq2(j) == 'A')
            match = matrix(3,2);
        
        elseif(seq1(i) == 'G' && seq2(j) == 'A')
            match = matrix(4,2);
        elseif(seq1(i) == 'T' && seq2(j) == 'A')
            match = matrix(5,2);
        
        elseif(seq1(i) == 'U' && seq2(j) == 'A')
            match = matrix(6,2);
        
        elseif(seq1(i) == 'C' && seq2(j) == 'C')
            match = matrix(3,3);
        
        elseif(seq1(i) == 'C' && seq2(j) == 'G')
            match = matrix(3,4);
        
        elseif(seq1(i) == 'C' && seq2(j) == 'T')
            match = matrix(3,5);
        
        elseif(seq1(i) == 'C' && seq2(j) == 'U')
            match = matrix(3,6);
        
        elseif(seq1(i) == 'G' && seq2(j) == 'C')
            match = matrix(4,3);
        
        elseif(seq1(i) == 'T' && seq2(j) == 'C')
            match = matrix(5,3);
        
        elseif(seq1(i) == 'U' && seq2(j) == 'C')
            match = matrix(6,3);
        
        elseif(seq1(i) == 'G' && seq2(j) == 'G')
            match = matrix(4,4);
        
        elseif(seq1(i) == 'G' && seq2(j) == 'T')
            match = matrix(4,5);
        
        elseif(seq1(i) == 'G' && seq2(j) == 'U')
            match = matrix(4,6);
        
        elseif(seq1(i) == 'T' && seq2(j) == 'G')
            match = matrix(5,4);
        
        elseif(seq1(i) == 'U' && seq2(j) == 'G')
            match = matrix(6,4);
        
        elseif(seq1(i) == 'T' && seq2(j) == 'T')
            match = matrix(5,5);
        
        elseif(seq1(i) == 'U' && seq2(j) == 'T')
            match = matrix(6,5);
        
        elseif(seq1(i) == 'T' && seq2(j) == 'U')
            match = matrix(5,6);
        
        elseif(seq1(i) == 'U' && seq2(j) == 'U')
            match = matrix(6,6);
        else 
            break
        end
     
        
    end
    if exist('match','var')
         match = cell2mat(match);
    else
        match = 0;
    end
end
end
function [strToSave] = prepareFasta (new_seq1, new_seq2,colB,colF,rowB,rowF)

prepareRegex = ['> seq1 %d - %d\n%s\n> seq2 %d - %d\n%s\n'];
strToSave = sprintf(prepareRegex,colB,colF-1,new_seq1,rowB,rowF-1,new_seq2);


end
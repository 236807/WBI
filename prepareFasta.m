function [strToSave] = prepareFasta (new_seq1, new_seq2)

prepareRegex = ['> seq1\n%s\n> seq2\n%s\n'];
strToSave = sprintf(prepareRegex,new_seq1,new_seq2);


end
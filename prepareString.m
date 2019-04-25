function [strToSave] = prepareString (seq1, seq2, score, matchMatrix, gaps, identity, Length, match , mismatch, gap)

prepareRegex = ['# 1: %s\n# 2: %s\n# # Gap: %d\n# Match: %d\n# Mismatch %d\n# Score: %d\n#' ...
' Length %d\n# Identity %d/%d\n# Gaps: %d/%d\n%s\n%s\n%s'];
strToSave = sprintf(prepareRegex,seq1,seq2,gap,match,mismatch,score,...
Length,identity,Length,gaps,Length,matchMatrix);


end
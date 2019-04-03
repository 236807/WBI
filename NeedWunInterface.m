function NeedWunInterface(varargin)

p=inputParser;
addParameter(p,'fileName1','',@ischar)
addParameter(p,'fileName2','',@ischar)
addParameter(p,'URLIdentifier1','',@ischar)
addParameter(p,'URLIdentifier2','',@ischar)
addParameter(p,'match',2)
addParameter(p,'gap',-2)
addParameter(p,'mismatch',-1)

    parse(p,varargin{:})
    p.Results
    
if (~isempty(p.Results.fileName1))
    seq1 = fileFasta(strcat(p.Results.fileName1,'.fasta'));

elseif (~isempty(p.Results.URLIdentifier1))
    seq1 = fetchFasta(p.Results.URLIdentifier1);

else
    disp("Nie podano pierwszej sekwencji")
    return;
end

if (~isempty(p.Results.fileName2))
    seq2 = fileFasta(strcat(p.Results.fileName2,'.fasta'));

elseif (~isempty(p.Results.URLIdentifier2))
    seq2 = fetchFasta(p.Results.URLIdentifier2);

else
    disp("Nie podano drugiej sekwencji")
    return;
end
SEQ1 = parseFasta(seq1);
SEQ2 = parseFasta(seq2);  
disp("1: " + SEQ1.sequence);
disp("2: " + SEQ2.sequence);
disp("Match: " + p.Results.match);
disp("Mismatch: " + p.Results.mismatch);
disp("Gap: " +  p.Results.gap);
[ score, matchMatrix, gaps, identity, Length , scoreMatrix ]= needleman_wunsch(SEQ1.sequence, SEQ2.sequence, p.Results.match, p.Results.mismatch, p.Results.gap);
disp("Score: " + score);
disp("Length: " + Length);
disp("Identity: " + identity + "/" + Length + "   "+ identity / Length * 100 + "%");
disp( "Gaps: " + gaps + "/" + Length + "   "+ gaps / Length * 100 + "%");
disp(matchMatrix)
drawScoreMatrix(scoreMatrix);
end

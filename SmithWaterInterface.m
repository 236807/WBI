function SmithWaterInterface(varargin)

p=inputParser;
addParameter(p,'fileName1','',@ischar)
addParameter(p,'fileName2','',@ischar)
addParameter(p,'URLIdentifier1','',@ischar)
addParameter(p,'URLIdentifier2','',@ischar)
addParameter(p,'matrixFileName',@ischar)
addParameter(p,'gap',-2)
addParameter(p,'saveFileName','Result',@ischar)
addParameter(p,'saveFileFastaName','ResultFasta',@ischar)


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
seq1=SEQ1.sequence;
seq2=SEQ2.sequence;

disp("1: " + seq1);
disp("2: " + seq2);
disp("Gap: " +  p.Results.gap);
if(~isempty(p.Results.matrixFileName))
matrix=getSubstitudeMatrixFromFile(p.Results.matrixFileName);
else
   disp("Nie podano pliku macierzy substytucji")
end
scoreMatrix=smithWaterman(seq1,seq2,matrix,p.Results.gap);
[road,matchMatrix, gaps, Length,score,new_seq1,new_seq2,identity]= TraceBack(scoreMatrix,seq1,seq2,matrix,p.Results.gap);

disp("Score: " + score);
disp("Length: " + Length);
disp("identity: " + identity + "/" + Length + "   "+ identity / Length * 100 + "%");
disp( "Gaps: " + gaps + "/" + Length + "   "+ gaps / Length * 100 + "%");
disp(matchMatrix)

drawScoreMatrix(scoreMatrix,road);

if(~isempty(p.Results.saveFileName))
    stringToSave =  prepareString (seq1, seq2, score, matchMatrix, gaps,identity, Length, p.Results.gap);
    saveToFile('Result',stringToSave);
end
if(~isempty(p.Results.saveFileFastaName))
    fastaToSave = prepareFasta(new_seq1,new_seq2);
    saveToFile('ResultFasta',fastaToSave);
end

function Integration(varargin)

p=inputParser;
addParameter(p,'fileName1','',@ischar)
addParameter(p,'fileName2','',@ischar)
addParameter(p,'URLIdentifier1','',@ischar)
addParameter(p,'URLIdentifier2','',@ischar)
addParameter(p,'window',15)
addParameter(p,'error',5)
addParameter(p,'pngPlotName','',@ischar)
addParameter(p,'match',2)
addParameter(p,'gap',-2)
addParameter(p,'mismatch',-1)
addParameter(p,'matrixFileName',@ischar)
addParameter(p,'fileName','Result',@ischar)
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

%% dotPlot
if(~isempty(p.Results.error)&& ~isempty(p.Results.window) && ~isempty(p.Results.pngPlotName))
if (p.Results.error >= p.Results.window || p.Results.window <= 0 || p.Results.error < 0)
   disp("Niepoprawnie ustawiono rozmiar okna oraz ilosc bledow. Ustawiono wartosci domyslne okno = 15, blad = 5")
   p.Results.window = 15;
   p.Results.error = 5;
end
matrix = generatorDotPlot(seq1,seq2);
matrix = filterMatrix(matrix,p.Results.window,p.Results.error);

if (~isempty(p.Results.pngPlotName))
DrawFiltredMatrix(matrix,fastaSEQ1.id,fastaSEQ2.id,p.Results.pngPlotName);
end
end
%% Needelman-Wunsch
if(~isempty(p.Results.match) && ~isempty(p.Results.mismatch) && ~isempty(p.Results.gap) && ~isempty(p.Results.fileName))

[ score, matchMatrix, gaps, identity, Length , scoreMatrix ] = needlemanWunsch(seq1, seq2, p.Results.match, p.Results.mismatch, p.Results.gap);
drawScoreMatrix(scoreMatrix);

if(~isempty(p.Results.fileName))
    stringToSave = prepareString (seq1, seq2, score, matchMatrix, gaps, identity, Length, match , mismatch, gap);
    saveToFile('Result',stringToSave);
end
end
%% Smith-Waterman
if(~isempty(p.Results.matrixFileName) && ~isempty(p.Results.gap) && ~isempty(p.Results.saveFileName) && ~isempty(p.Results.saveFileFastaName))
if(~isempty(p.Results.matrixFileName))
matrix=getSubstitudeMatrixFromFile(p.Results.matrixFileName);
else
   disp("Nie podano pliku macierzy substytucji")
end
[scoreMatrix,a,b]=smithWaterman(seq1,seq2,matrix,p.Results.gap);
[road,matchMatrix, gaps, Length,score,new_seq1,new_seq2,identity]= TraceBack(scoreMatrix,seq1,seq2,matrix,p.Results.gap,a,b);
drawScoreMatrix(scoreMatrix,road);

if(~isempty(p.Results.saveFileName))
    stringToSave =  prepareString (seq1, seq2, score, matchMatrix, gaps,identity, Length, p.Results.gap);
    saveToFile('Result',stringToSave);
end
if(~isempty(p.Results.saveFileFastaName))
    fastaToSave = prepareFasta(new_seq1,new_seq2);
    saveToFile('ResultFasta',fastaToSave);
end
end

function dotPlot(varargin)

p=inputParser;
addParameter(p,'fileName1','',@ischar)
addParameter(p,'fileName2','',@ischar)
addParameter(p,'URLIdentifier1','',@ischar)
addParameter(p,'URLIdentifier2','',@ischar)
addParameter(p,'window',20)
addParameter(p,'error',5)
addParameter(p,'pngPlotName','',@ischar)

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

if (p.Results.error >= p.Results.window || p.Results.window <= 0 || p.Results.error < 0)
   disp("Niepoprawnie ustawiono rozmiar okna oraz ilosc bledow. Ustawiono wartosci domyslne okno = 15, blad = 5")
   p.Results.window = 15;
   p.Results.error = 5;
end
SEQ1 = parseFasta(seq1);
SEQ2 = parseFasta(seq2);  
a=tic;
matrix = generatorDotPlot(SEQ1(1).sequence,SEQ2(1).sequence);
time_generator=toc(a);
disp("Czas tworzenia macierzy: " + time_generator(1))
b=tic;
filtredMatrix = filterMatrix(matrix,p.Results.window,p.Results.error);
time_filtred=toc(b);
disp("Czas filtrowania macierzy: " + time_filtred(1))
time_full=toc(a);
disp("Czas tworzenia i filtrowania macierzy: " + time_full)
if (~isempty(p.Results.pngPlotName))
DrawFiltredMatrix(filtredMatrix);
end

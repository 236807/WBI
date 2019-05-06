%% Wczytywanie sekwencji
seq1 = parseFasta(fileFasta('sequence_CYCS_wilk.fasta')); 
%seq1 = parseFasta(fetchFasta('DQ334813.1')); 
%seq1 = parseFasta(inputFasta()); 
seq1 = seq1.sequence;
seq2 = parseFasta(fileFasta('sequence_CYCS_pies.fasta'));
%seq2 = parseFasta(fetchFasta('LT994967.1'));
%seq2 = parseFasta(inputFasta());
seq2 = seq2.sequence;
disp("1: " + seq1);
disp("2: " + seq2);
%% Przyk�adowe warto�ci
gap = -2;
matrix=createSubstitudeMatrixFromFile('matrix.csv');
disp("Gap: " + gap);
%% smith waterman 
[scoreMatrix,a,b]=smithWaterman(seq1,seq2,matrix,gap);
[road,matchMatrix, gaps, Length,score,new_seq1,new_seq2,identity,colB,rowB,rowF,colF] = TraceBack(scoreMatrix,seq1,seq2,matrix,gap,a,b);
disp("score: " + score);
disp("identity: " + identity + "/" + Length + "   "+ identity / Length * 100 + "%");
disp( "Gaps: " + gaps + "/" + Length + "   "+ gaps / Length * 100 + "%");
disp("Length: " + Length);
disp(matchMatrix)

%% wykres 
drawScoreMatrix(scoreMatrix,road);
%% zapis do pliku
stringToSave = prepareString (seq1, seq2, score, matchMatrix, gaps, identity,Length, gap);
fastaToSave = prepareFasta(new_seq1,new_seq2,colB,colF,rowB,rowF);
saveToFile('Result',stringToSave);
saveToFile('ResultFasta',fastaToSave);


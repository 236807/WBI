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
%% Przyk³adowe wartoœci
gap = -2;
match = 2;
mismatch = -1;
disp("Match: " + match);
disp("Mismatch: " + mismatch);
disp("Gap: " + gap);
%% needleman wunsch
[ score, matchMatrix, gaps, identity, Length , scoreMatrix ] = needleman_wunsch(seq1, seq2, match, mismatch, gap);
disp("score: " + score);
disp( "Gaps: " + gaps + "/" + Length + "   "+ gaps / Length * 100 + "%");
disp("identity: " + identity + "/" + Length + "   "+ identity / Length * 100 + "%");
disp("Length: " + Length);
disp(matchMatrix)

%% wykres 
drawScoreMatrix(scoreMatrix);
%% zapis do pliku
stringToSave = prepareString (seq1, seq2, score, matchMatrix, gaps, identity, Length, match , mismatch, gap);
saveToFile('Result',stringToSave);

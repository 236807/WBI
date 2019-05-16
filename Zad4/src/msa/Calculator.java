package msa;

// Klasa do wyliczania kosztów dopasowania,
// metoda wykonywująca dopasowanie globalne
// oraz wykonująca dopasowanie wielu sekwencji
public class Calculator {

    public static String[] mulipleSequence,seqenceBase;
    public static int cost, match, missmatch = 2 , gap = 4, side, variable,idx;
    public static int scoreAligment[][];
    public static String centerOfStar;
    private static  int i, j, k, lengthSeq1, lengthSeq2;

    public static int ScoreGlobalAligmentTwoSequence(String seq1, String seq2) { // dopasowanie globane

        if (seq1.equals(seq2)) {
            // dopasowanie identycznych sekwencji zwraca wynik 0
            return 0;
        }else {
            // budowa tablicy do prównywania 2 sekewencji
            lengthSeq1 = seq1.length();
            lengthSeq2 = seq2.length();
            scoreAligment = new int[lengthSeq1 + 1][lengthSeq2 + 1];
            scoreAligment[0][0] = 0;
            for (i = 1; i <= lengthSeq2; i++) {
                scoreAligment[0][i] = i;
            }
            for (j = 1; j <= lengthSeq1; j++) {
                scoreAligment[j][0] = j;
            }
            // porównywanie ze sobą 2 sekwencji (Global Aligment)
            for (i = 1; i <= lengthSeq1; i++) {
                for (j = 1; j <= lengthSeq2; j++) {
                    if (seq1.charAt(i - 1) == seq2.charAt(j - 1))
                        match = 0;
                    else
                        match = 1;
                    scoreAligment[i][j] = MinimumScore(scoreAligment[i - 1][j - 1] + 1 , scoreAligment[i][j - 1] + 1, scoreAligment[i - 1][j]+ match);
                }
            }
            // ustawnienie zmiennych
            i = lengthSeq1;
            j = lengthSeq2;
            k = 0;

            //traceBack(seq1, seq2, traceBack);
            return scoreAligment[lengthSeq1][lengthSeq2];
        }
    }

    private static int MinimumScore(int up, int left, int diagonal) { // minimalny wynik przy prównywaniu sekwencji każdej z każdą

        side = 0;
        variable = diagonal;
        if (left < variable) {
            variable = left;
            side = 1;
        }
        if (up < variable) {
            variable = up;
            side = 2;
        }
        return variable;
    }

    public static void multipleSequenceAlignment(String[] sequence) { // dopasowanie wielu sekwencji
        int foundMin = Integer.MAX_VALUE;
        int minI = -1;
        int minJ = -1;
        // dopasowanie globalne dla każdej sekwencji
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < sequence.length; j++) {
                if(j != i){
                    int tmpScore = Calculator.ScoreGlobalAligmentTwoSequence(sequence[i], sequence[j]);
                    if(tmpScore < foundMin){
                        foundMin = tmpScore;
                        minI = i;
                        minJ = j;
                    }
                }
            }
        }
        // dopasowanie wszystkich sekwencji na raz
        seqenceBase = new String[sequence.length];
        Node maxMatchLeaf = new Tree(sequence[minI], sequence[minJ]).findMaxMatchLeaf();
        seqenceBase[0]= maxMatchLeaf.getM1();
        seqenceBase[1]=maxMatchLeaf.getM2();
        String centerStringCopy = new Tree(sequence[minI], sequence[minJ]).findMaxMatchLeaf().mergeSequences();
        for (int i = 0; i < sequence.length; i++) {
            if(i != minI && i != minJ){
                maxMatchLeaf = new Tree(centerStringCopy, sequence[i]).findMaxMatchLeaf();
                centerStringCopy = new Tree(centerStringCopy, sequence[i]).findMaxMatchLeaf().mergeSequences();
                seqenceBase[i]=maxMatchLeaf.getM2();
            }
        }
    }

    public static int costOfMSA(int n) {      // koszt całkowity
        cost = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) {
                    for (int k = 0; k < seqenceBase.length; k++) {
                        if (seqenceBase[i].charAt(k) == seqenceBase[j].charAt(k)) {
                            //dopasowanie
                            cost +=0;
                        } else if (seqenceBase[i].charAt(k) == '-' || seqenceBase[j].charAt(k) == '-'){
                            //przerwa
                            cost += gap;
                        } else{
                            // niedopasowanie
                            cost += missmatch;
                        }
                    }
                }
            }
        }
        return cost;
    }

}





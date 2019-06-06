package msa;

// Klasa do wyliczania kosztów dopasowania,
// metoda wykonywująca dopasowanie globalne
// oraz wykonująca dopasowanie wielu sekwencji
public class Calculator {

    public static String[] mulipleSequence;
    public static int cost, missmatch = 1 , gap = 2, variable,idx,treeScore,score;
    public static int scoreAligment[][];
    public static String centerOfStar;
    private int i;
    private int j;
    private int k;
    private int lengthSeq1,lengthSeq2;
    private static String[] mainAlign = new String[2];
    public static double matchingMatrix[][];

    public int costOfMSA(int n) {

        // koszt całkowity
        score = 0;
        int length = mulipleSequence[0].length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) {
                    for (int k = 0; k < length; k++) {
                        if (mulipleSequence[i].charAt(k) == mulipleSequence[j].charAt(k)) {
                            // dopasowanie
                            score += 0;
                        } else if (mulipleSequence[i].charAt(k) == '-' || mulipleSequence[j].charAt(k) == '-')
                            // niedopasowanie
                            score += missmatch;
                        else
                            // przerwa
                            score += gap;
                    }
                }
            }
        }
        return score;
    }
    public double[][] BuildMatrix(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                    for (int k = 0; k < mulipleSequence[0].length(); k++) {
                        if (mulipleSequence[i].charAt(k) == mulipleSequence[j].charAt(k)) {
                            // dopasowanie
                            matchingMatrix[i][j] += 0;
                        } else if (mulipleSequence[i].charAt(k) == '-' || mulipleSequence[j].charAt(k) == '-')
                            // niedopasowanie
                            matchingMatrix[i][j] += missmatch;
                        else
                            // przerwa
                            matchingMatrix[i][j] += gap;
                    }
            }
        }
        return matchingMatrix;
    }

    public int calculateMinimum(int diagonal, int left, int up) {

        // znalenienie minimum w 3 wartościach.
        int temp = diagonal;
        variable = 0;
        if (temp > left) {
            temp = left;
            variable = 1;
        }
        if (temp > up) {
            temp = up;
            variable = 2;
        }
        return temp;
    }
    public int ScoreGlobalAligmentTwoSequence(String seq1, String seq2) {

        // dopasownie sekwencji dla każdej z sekwencji (każdy z każdym)
        if (seq1.equals(seq2))
            // dopasowanie identycznych sekwencji zwraca wynik 0
            return 0;

        // budowa tablicy do prównywania 2 sekewencji
        lengthSeq1 = seq1.length();
        lengthSeq2 = seq2.length();
        int score[][] = new int[lengthSeq1 + 1][lengthSeq2 + 1];
        int path[][] = new int[lengthSeq1 + 1][lengthSeq2 + 1];
        score[0][0] = 0;
        path[0][0] = 0;
        for (i = 1; i <= lengthSeq2; i++) {
            score[0][i] = i;
            path[0][i] = 1;
        }
        for (j = 1; j <= lengthSeq1; j++) {
            score[j][0] = j;
            path[j][0] = 2;
        }
        for (i = 1; i <= lengthSeq1; i++) {
            for (j = 1; j <= lengthSeq2; j++) {
                int match;
                if (seq1.charAt(i - 1) == seq2.charAt(j - 1))
                    match = 0;
                else
                    match = 1;
                score[i][j] = calculateMinimum(score[i - 1][j - 1] + match, score[i][j - 1] + 1, score[i - 1][j] + 1);
                path[i][j] = variable;
            }
        }

        i = lengthSeq1;
        j = lengthSeq2;
        k = 0;

        char[][] pairAlignment = calcAlignment(seq1, seq2, path);
        traceBack(pairAlignment);


        return score[lengthSeq1][lengthSeq2];
    }

    public void multipleSequenceAlignment(int n, String[] sequence) {

        // dopasowanie wielu sekwencji
        String centerStringCopy = centerOfStar;
        for (int i = 0; i < n; i++) {
            if (i == idx) {
                mulipleSequence[i] = centerStringCopy;
                continue;
            }
            // dopasownie sekwencji dla każdej z sekwencji (każdy z każdym)
            ScoreGlobalAligmentTwoSequence(centerOfStar, sequence[i]);
            mulipleSequence[i] = mainAlign[1];
            // doklejanie przerw w sekwencjach
            if (mainAlign[0].length() > centerStringCopy.length()) {
                for (int j1 = 0, j2 = 0; j1 < mainAlign[0].length(); j1++) {
                    if (centerStringCopy.charAt(j2) != mainAlign[0].charAt(j1)) {
                        StringBuilder stringBuilder;
                        for (int k = 0; k < i; k++) {
                            stringBuilder = new StringBuilder(mulipleSequence[k]);
                            stringBuilder.insert(j1, '-');
                            mulipleSequence[k] = stringBuilder.toString();
                        }
                    } else
                        j2++;
                }
                centerStringCopy = mainAlign[0];

            }
            if (mainAlign[0].length() < centerStringCopy.length()) {
                for (int j1 = 0, j2 = 0; j1 < centerStringCopy.length(); j1++) {
                    if (centerStringCopy.charAt(j1) != mainAlign[0].charAt(j2)) {
                        StringBuilder stringBuilder;
                        stringBuilder = new StringBuilder(mulipleSequence[i]);
                        stringBuilder.insert(j1, '-');
                        mulipleSequence[i] = stringBuilder.toString();
                    } else
                        j2++;
                }
            }
        }
    }

    private char[][] calcAlignment(String seq1, String seq2, int[][] path) {

        // przerwy w dopasowaniu 2 seekwencji
        char[][] aligment = new char[2][lengthSeq1 + lengthSeq2];
        while (i != 0 || j != 0) {
            if (path[i][j] == 0) {
                aligment[0][k] = seq1.charAt(i - 1);
                aligment[1][k] = seq2.charAt(j - 1);
                i--;
                j--;
            } else if (path[i][j] == 1) {
                aligment[0][k] = '-';
                aligment[1][k] = seq2.charAt(j - 1);
                j--;
            } else {
                aligment[0][k] = seq1.charAt(i - 1);
                aligment[1][k] = '-';
                i--;
            }
            k++;
        }
        return aligment;
    }

    private void traceBack(char[][] pairAlignment) {

        // macierz wsteczna służąca do znalezienia drogi
        String input;
        char[][] traceBack = new char[2][k];
        i = 0;
        while (k > 0) {
            traceBack[0][i] = pairAlignment[0][k - 1];
            traceBack[1][i] = pairAlignment[1][k - 1];
            k--;
            i++;
        }
        input = String.valueOf(traceBack[0]);
        mainAlign[0] = input;
        input = String.valueOf(traceBack[1]);
        mainAlign[1] = input;
    }

}
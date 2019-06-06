package msa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFinalFile {
    public static void saveToFileClustalOmega(Calculator calculator) throws IOException {

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("CLUSTAL_OMEGA.txt"));
        writer.write("Multiple Sequence Alignment: \n");
        writer.write("KOSZT CALKOWITY =: " + calculator.costOfMSA(Calculator.mulipleSequence.length) + "\n\n");


        StringBuilder[] stars = new StringBuilder[Calculator.mulipleSequence.length];
        for (int i = 0; i < Calculator.mulipleSequence.length - 1; i++) {
            stars[i] = new StringBuilder();
            for (int k = 0; k < Calculator.mulipleSequence[0].length(); k++)
                if (Calculator.mulipleSequence[i].charAt(k) == Calculator.mulipleSequence[i + 1].charAt(k))    //
                    stars[i].append("*");
                else stars[i].append(" ");
        }


        for (int i = 0; i < stars.length - 1; i++) {
            writer.write(Calculator.mulipleSequence[i] + "\n" +
                    Calculator.mulipleSequence[i + 1] + "\n" + stars[i] + "\n");
        }


        writer.close();

    }
    public static void saveToFileFasta(Calculator calculator) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("sequences.fasta"));
        StringBuilder[] stars = new StringBuilder[Calculator.mulipleSequence.length];
        for (int i = 0; i < stars.length -1; i++) {
            writer.write("> sequence" + i + "\n" + Calculator.mulipleSequence[i]+ "\n");

        }
    }
}

package msa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFinalFile {
    public static void saveToFile(Calculator calculator) throws IOException {

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(" CLUSTAL_OMEGA.txt"));
        StringBuilder[] aligment = new StringBuilder[Calculator.seqenceBase.length];
        for (int i = 0; i < Calculator.seqenceBase.length - 1; i++) {
            aligment[i] = new StringBuilder();
            for (int k = 0; k < Calculator.seqenceBase[0].length(); k++)
                if (Calculator.seqenceBase[i].charAt(k) == Calculator.seqenceBase[i + 1].charAt(k)) {
                    aligment[i].append("*");
                }
                else{
                    aligment[i].append(" ");
                }
        }
        for (int i = 0; i < aligment.length - 1; i++) {
            writer.write(Calculator.seqenceBase[i] + "\n" + Calculator.seqenceBase[i + 1] + "\n" + aligment[i] + "\n");
        }
        writer.write("Total cost =: " + Calculator.costOfMSA(Calculator.seqenceBase.length));
        writer.close();
    }
}

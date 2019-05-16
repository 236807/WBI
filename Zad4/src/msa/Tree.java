package msa;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Tree {
    Node seqNode;
    public static String centerString;
    public static int idx;

    public Tree(String seq1, String seq2) {
        seqNode = new Node(seq1, seq2, "", "");
    }


    Node findMaxMatchLeaf() {

        return seqNode.findMaxMatchLeaf();
    }
}

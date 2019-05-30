package msa;

public class Node {
    Node left;
    Node right;
    String seq1, seq2;
    String m1, m2;

    Node findMaxMatchLeaf() {

        if (isleaf()) {
            return this;
        }
        Node maxL = left != null ? left.findMaxMatchLeaf() : null;
        Node maxR = right != null ? right.findMaxMatchLeaf() : null;

        if (maxL != null && maxR == null) {
            return maxL;
        } else if (maxR != null && maxL == null) {
            return maxR;
        } else if (maxL == null) {
            return null;
        } else {
            if (maxL.match() > maxR.match()) {
                return maxL;
            } else {
                return maxR;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "seq1=" + seq1 +
                "seq2=" + seq2 +

                ", m1='" + m1 + '\'' +
                ", m2='" + m2 + '\'' +
                ", match: " + match() +
                ", isLeaf: " + isleaf() +
                '}';
    }

    public Node(String seq1, String seq2, String m1, String m2) {
        this.seq1 = seq1;
        this.seq2 = seq2;
        this.m1 = m1;
        this.m2 = m2;


        String char1 = seq1.isEmpty() ? null : seq1.charAt(0) + "";
        String char2 = seq2.isEmpty() ? null : seq2.charAt(0) + "";

        // RÓWNE
        if (seq1.isEmpty() && seq2.isEmpty()) {
            System.out.println("--- LEAF ---");
        } else if (char1 != null && char2 != null && char1.equals(char2)) {
            left = new Node(seq1.substring(1), seq2.substring(1), m1 + char1, m2 + char2);
        } else {
            // lewa - u góry przerwa

            if (char2 != null) {
                left = new Node(seq1, seq2.substring(1), m1 + "_", m2 + char2);
            } else {
                String spaces = "";
                for (int i = 0; i < seq1.length(); i++) {
                    spaces += "_";
                }
                left = new Node("", "", m1 + seq1, m2 + spaces);

            }

            if (char1 != null) {
                right = new Node(seq1.substring(1), seq2, m1 + char1, m2 + "_");
            } else {
                String spaces = "";
                for (int i = 0; i < seq2.length(); i++) {
                    spaces += "_";
                }
                right = new Node("", "", m1 + spaces, m2 + seq2);
            }

        }

        System.out.println("creating node: " + toString());

    }

    public String mergeSequences(){
        String seq = "";
        for (int i = 0; i < m2.length() ; i++) {
            if (m2.charAt(i) != '_') {
                seq += m2.charAt(i);
            } else {
                seq += m1.charAt(i);
            }
        }

        return seq;
    }
    public boolean isleaf() {
        return seq1.isEmpty() && seq2.isEmpty();
    }

    public int match() {
        int counter = 0;
        for (int i = 0; i < m1.length() && i < m2.length(); i++) {
            if (m1.charAt(i) == m2.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getSeq1() {
        return seq1;
    }

    public void setSeq1(String seq1) {
        this.seq1 = seq1;
    }

    public String getSeq2() {
        return seq2;
    }

    public void setSeq2(String seq2) {
        this.seq2 = seq2;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }
}

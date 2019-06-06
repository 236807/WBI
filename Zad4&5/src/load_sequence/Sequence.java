package load_sequence;

public class Sequence { // Klasa główna Sekwencji służąca do przetwarzania oosbno sekwencji i osobno id tej sekwencji.

    String id;
    String sequence;

    public Sequence(String id, String sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public static void setHighScore(int score) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "loadSequence.Sequence{" +
                "id='" + id + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}


package UPGMA;
import java.util.ArrayList;

/*
 *Cluster is a data structure that contains two entities, each of which
 *can either be a Terminal object or another Cluster.
 */

public class Cluster extends Entity {
    private Entity a;
    private Entity b;
    public Cluster (Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
    public Entity getA() {
        return a;
    }
    public Entity getB() {
        return b;
    }
    public String toString() {
        return "(" + a + ":" + a.getValue() + ", " + b + ":" + b.getValue() + ")";
    }
    public ArrayList<Terminal> returnTerminals() {
        ArrayList<Terminal> result = new ArrayList<Terminal> ();
        result.addAll(a.returnTerminals());
        result.addAll(b.returnTerminals());
        return result;
    }
    public double totalDifferences() {
        // różnica całkowita od skrzyżowania tego klastra do jednego terminala
        return a.getValue() + a.totalDifferences();
    }
}

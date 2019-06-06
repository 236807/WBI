package UPGMA;

import java.util.ArrayList;

public abstract class Entity {
    //kwota, jaką każdy podmiot(klaser) posiada
    protected double value;
    public abstract ArrayList<Terminal> returnTerminals();
    public abstract double totalDifferences();
    public boolean isCluster() {
        return this.getClass().toString().substring(6).equals("Cluster");
    }
    public double getValue() {return value;}
    public void setValue(double value) {this.value = value;}
}

package UPGMA;

public class CalculatorUPGMA {

    public static Cluster makeTree(int n, Entity[] entities, double[][] differences) {
        if (n==2) {
            Cluster tree = new Cluster (entities[0], entities[1]);
            double minimum = differences[0][1]; //AKA differences[1][0]
            entities[0].setValue(minimum/2-entities[0].totalDifferences());
            entities[1].setValue(minimum/2-entities[1].totalDifferences());
            return tree;
        }
        // połączenie dwóch jednostek w klaster
        // czyli zmodyfikacja terminali i różnic

        Object[] grouped = group (entities, differences);
        entities = (Entity[]) grouped[0];
        differences = (double[][]) grouped[1];
        return makeTree(n-1, entities, differences);
    }

    private static Object[] group (Entity[] entities, double[][] differences) {
        // znalezienie minimum róznicy w tablicy różnic sekwencji
        int minimum1 = 0;
        int minimum2 = 1;
        double minimum = differences[minimum1][minimum2];
        for (int i = 0; i < differences.length; i++) {
            for (int j = 0; j < differences[i].length; j++) {
                if (differences[i][j]<minimum && i!=j) {
                    minimum1 = i;
                    minimum2 = j;
                    minimum = differences[i][j];
                }
            }
        }
        // minimum1 jest zawsze mniejsze niż minimum2

        // połączenie dwóch w encje (w zależności od tego, co będzie pierwsze)
        Entity[] newEntities = new Entity[entities.length-1];
        Entity newEntity = new Cluster(entities[minimum1], entities[minimum2]);
        entities[minimum1].setValue(minimum/2-entities[minimum1].totalDifferences());
        entities[minimum2].setValue(minimum/2-entities[minimum2].totalDifferences());
        int counter = 0;
        for (int i = 0; i < entities.length; i++) {
            if (i==minimum1) {newEntities[counter] = newEntity;}
            else if (i==minimum2) {counter--;}
            else {newEntities[counter] = entities[i];}
            counter++;
        }
        // stworzenie nowej tablicy różnic sekwencji
        double[][] newDifferences = new double[differences.length-1][differences.length-1];
        for (int i = 0; i < differences.length-1; i++) {
            for (int j = 0; j < differences.length-1; j++) {
                if (i==j) {newDifferences[i][j] = 0;}
                else if (i!=minimum1 && j!= minimum1) {
                    int a, b;
                    if (i < minimum2) a = i; else a = i+1;
                    if (j < minimum2) b = j; else b = j+1;
                    newDifferences[i][j] = differences[a][b];
                }
            }
        }
        for (int i = 0; i < differences.length-1; i++) {
            double value = 0;
            if (i<minimum2) { value = (differences[i][minimum1]+differences[i][minimum2])/2;}
            if (i>=minimum2) { value = (differences[i+1][minimum1]+differences[i+1][minimum2])/2;}
            if (i==minimum1) value = 0;
            newDifferences[i][minimum1] = value;
            newDifferences[minimum1][i] = value;
        }
        Object[] result = {newEntities, newDifferences};
        result[0] = newEntities;
        result[1] = newDifferences;
        return result;
    }
}


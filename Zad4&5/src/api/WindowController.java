package api;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import load_sequence.FetchFasta;
import load_sequence.FileFasta;
import msa.Calculator;
import msa.SaveFinalFile;
import UPGMA.Cluster;
import UPGMA.Entity;
import UPGMA.Terminal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static UPGMA.CalculatorUPGMA.makeTree;

public class WindowController {

    @FXML
    private TextField seqURL;

    @FXML
    private TextField fromHand;

    @FXML
    void addFromFile(ActionEvent event) throws IOException { //Wczytywanie pliku fasta

        File directory = new File("C://Users//David//Desktop//IB//Bioinformatyka//WBI//Zad4");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose FIle");
        fileChooser.setInitialDirectory(directory);
        File selectedDirectory = fileChooser.showOpenDialog(new Stage());
        FileFasta fileFasta = new FileFasta();
        MainApi.sequences.add(fileFasta.readFasta(selectedDirectory.getAbsolutePath()).getSequence());
    }

    @FXML
    void addSeq(ActionEvent event) throws IOException { //wybór pomiędzy ręcznym wpisaniem sekwencji a sekwencji z URL

        if (!fromHand.getText().isEmpty()) {
            MainApi.sequences.add(fromHand.getText());
            fromHand.clear();
        } else if (!seqURL.getText().isEmpty()) {
            MainApi.sequences.add(new FetchFasta().loadFromURL(new URL(seqURL.getText())).getSequence());
            seqURL.clear();
        }
    }

    @FXML
    void clear(ActionEvent event) { //czyszczenie sekwnecji

        MainApi.sequences.clear();
    }

    @FXML
    void confrim(ActionEvent event) throws IOException { // wybór algorytmu  gwiazdowego do Multiple sequence alignment

        if (MainApi.sequences.size() > 1) {
            String[] sequence = new String[MainApi.sequences.size()];
            for (int i = 0; i < MainApi.sequences.size(); i++)
                sequence[i] = MainApi.sequences.get(i);

            int n = sequence.length;
            Calculator calculator = new Calculator();
            calculator.centerOfStar = sequence[Calculator.idx];
            calculator.mulipleSequence = new String[n];
            calculator.multipleSequenceAlignment(n,sequence);
            SaveFinalFile.saveToFileClustalOmega(calculator);
            SaveFinalFile.saveToFileFasta(calculator);
            Calculator.matchingMatrix = new double[n][n];


            Entity[] entities = null;
            double[][] differences = null;
            ArrayList<Entity> tempEntities = new ArrayList<Entity>();
            for (int i = 0; i < Calculator.mulipleSequence.length ; i++) {
                tempEntities.add(new Terminal(Calculator.mulipleSequence[i]));
            }
            n = tempEntities.size();
            entities = new Entity[n];
            for (int i = 0; i < n; i++) {
                entities[i] = tempEntities.get(i);
            }

            differences = new double[n][n];
            differences = calculator.BuildMatrix(n);
            Object[] result = {n, entities, differences};

            entities = (Entity[]) result[1];
            differences = (double[][]) result[2];

            Cluster tree = makeTree (n, entities, differences);
            System.out.println("Phylogenetic Tree (Newick format): " + tree + ";");
        }
    }
    @FXML
    void upmga(ActionEvent event) throws IOException {

        if (MainApi.sequences.size() > 1) {
            String[] sequence = new String[MainApi.sequences.size()];
            for (int i = 0; i < MainApi.sequences.size(); i++)
                sequence[i] = MainApi.sequences.get(i);

            int n = sequence.length;
            Calculator calculator = new Calculator();
            calculator.centerOfStar = sequence[Calculator.idx];
            calculator.mulipleSequence = new String[n];
            calculator.multipleSequenceAlignment(n,sequence);
            Calculator.matchingMatrix = new double[n][n];

            Entity[] entities = null;
            double[][] differences =  new double[n][n];
            ArrayList<Entity> tempEntities = new ArrayList<Entity>();
            for (int i = 0; i < Calculator.mulipleSequence.length ; i++) {
                tempEntities.add(new Terminal(Calculator.mulipleSequence[i]));
            }
            n = tempEntities.size();
            entities = new Entity[n];
            for (int i = 0; i < n; i++) {
                entities[i] = tempEntities.get(i);
            }
            differences = calculator.BuildMatrix(n);
            Object[] result = {n, entities, differences};

            entities = (Entity[]) result[1];
            differences = (double[][]) result[2];

            Cluster tree = makeTree (n, entities, differences);
            System.out.println(n);
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("Newick.txt"));
            writer.write("Phylogenetic Tree (Newick format): " + tree + ";");
            writer.close();
        }
    }

}

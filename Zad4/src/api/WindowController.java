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

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WindowController {

    @FXML
    private TextField seqURL;

    @FXML
    private TextField fromHand;

    @FXML
    void addFromFile(ActionEvent event) throws IOException { //Wczytywanie pliku fasta

        File directory = new File("/");
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
            for (int i = 0; i < MainApi.sequences.size(); i++) {
                sequence[i] = MainApi.sequences.get(i);
            }
            int n = sequence.length;
            Calculator calculator = new Calculator();
            Calculator.centerOfStar = sequence[Calculator.idx];
            Calculator.mulipleSequence = new String[n];
            Calculator.multipleSequenceAlignment(sequence);
            SaveFinalFile.saveToFile(calculator);
        }
    }
}

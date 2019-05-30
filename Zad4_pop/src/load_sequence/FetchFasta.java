package load_sequence;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FetchFasta {

    public void parseFastaToSave(URL url, String filename) throws IOException { // analiza z URL sekwencji i wywołanie zapisu
        String line;
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null) {
            writeToFile(line, filename);
        }
    }

    private static void writeToFile(String text, String filename) throws IOException { // zapis do pliku wczytanej sekwencji

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename), true));
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public Sequence loadFromURL(URL url) throws IOException { // wczytanie sekwencji z URL

        String id;
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        if ((id = bufferedReader.readLine()) != null) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line != null)
                    stringBuilder.append(line);
            }
            return new Sequence(id, new String(stringBuilder));
        }
        return null;
    }
}

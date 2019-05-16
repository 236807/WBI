package load_sequence;

import java.io.*;

public class FileFasta {

    public Sequence readFasta(String path) throws IOException { // Wczytywanie sekwencji z pliku

        File file = new File(path);
        String line;
        final String title;
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            title = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null)
                if (line != null) {
                    stringBuilder.append(line);
                }
            return new Sequence(title, new String(stringBuilder));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

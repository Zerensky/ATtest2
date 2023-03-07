package model.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrizeReader {
    public ArrayList<String> readAwardedPrizes() {
        ArrayList<String> awardedPrizes = new ArrayList<>();
        try {
            FileReader fr = new FileReader("prizes.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                awardedPrizes.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return awardedPrizes;
    }
}

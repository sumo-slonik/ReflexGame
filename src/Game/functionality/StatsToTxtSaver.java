package Game.functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class StatsToTxtSaver {
    public StatsToTxtSaver()
    {
    }
    public void saveStats(String stats,String name) throws FileNotFoundException {
        try {
            File myObj = new File(name+"Stats.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        PrintWriter result = new PrintWriter(name+"Stats.txt");
        result.println(stats);
        result.close();
    }
}

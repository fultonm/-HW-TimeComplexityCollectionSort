
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author michaelfulton
 * @course Prof. Iverson's CS 211
 * @date Feb 7, 2017
 * @reasonForDoingThis Create a utility for presenting the data in a way we can
 * use to create the Excel chart.
 */
public class ConvertData {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = s.nextLine();
        Scanner f = new Scanner(new File(fileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName + "_mod.csv"));
        while (f.hasNextLine()) {
            String l = f.nextLine();
            String[] lFrags = l.split(",");
            String x = lFrags[0];
            for (int i = 1; i < lFrags.length; i++) {
                if (!lFrags[i].equals("")) {
                    bw.write(x + "," + lFrags[i] + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }

}

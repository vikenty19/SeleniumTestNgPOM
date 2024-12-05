package experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteToFile {
    public static void main(String[] args) {
        File file = new File("writeFile");
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println("Test row 1");
            pw.println("Test 2");
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

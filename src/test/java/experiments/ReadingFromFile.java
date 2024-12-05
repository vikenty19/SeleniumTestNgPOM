package experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadingFromFile {
    public static void main(String[] args) throws FileNotFoundException {
        //reading from the line with spaces
        String path =System.getProperty("user.dir")+"/src/main/java/testData/TestData.csv";
         File file = new File(path);
        Scanner scanner = new Scanner(file);
       String line = scanner.nextLine();
       String[]words = line.split(" ");
        System.out.println(Arrays.toString(words));
//reading from the line with numbers
            String pathNumbers =System.getProperty("user.dir")+"/src/main/java/testData/NumbersReading.csv";
            File fileNumbers = new File(pathNumbers);
            Scanner scanner1= new Scanner(fileNumbers);
             String line1 = scanner1.nextLine();
             String[]numbers = line1.split(" ");
        System.out.println(Arrays.toString(numbers));
        //reading several lines
        String path2 =System.getProperty("user.dir")+"/src/main/java/testData/SeveralLinesReading.csv";
        File file2 = new File(path2);
        Scanner scanner2 = new Scanner(file2);
        while (scanner2.hasNextLine()){
            System.out.println(scanner2.nextLine());
        }
        scanner.close();
    }
}

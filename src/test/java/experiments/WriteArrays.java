package experiments;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteArrays {

    public static void main(String[] args) {
        Person[] persons ={new Person(1, "Bobby"),new Person(2, "Mike Duglas"), new Person(3,"Tom")};
        FileOutputStream fos = null;//create file
        try {
            fos = new FileOutputStream("./people.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persons);
            oos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

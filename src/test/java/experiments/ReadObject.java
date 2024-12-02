package experiments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadObject {
    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("people.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Person person1 = (Person) ois.readObject();
            Person person2 = (Person) ois.readObject();
            // Array reading
//List People downcusting from Object
      //    Person[]people = (Person[]) ois.readObject();
            ois.close();// Important!!
           System.out.println(person1);
           System.out.println(person2);
       //     System.out.println(Arrays.toString(people));


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

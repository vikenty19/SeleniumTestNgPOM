package experiments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteObject {
    public static void main(String[] args) {


        Person person1 = new Person(1, "Bob");
        Person person2 = new Person(2, "Mike");


        {
            try {
                FileOutputStream    fos = new FileOutputStream("./people.bin");//create file
                ObjectOutputStream oos = new ObjectOutputStream(fos);//create object for  which we need file to write in
                oos.writeObject(person1);
                oos.writeObject(person2);//writing in process
                oos.close();//close down stream - mandatory!!
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
////////// Arrays
       Person[] persons ={person1,person2, new Person(3,"Tom")};
        FileOutputStream    fos = null;//create file
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
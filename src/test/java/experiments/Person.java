package experiments;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private   String name;//transient - for omitting from writing this field down
    public Person(int id, String name){
        this.id =id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public String toString(){
        return id + ":"+name;
    }

}

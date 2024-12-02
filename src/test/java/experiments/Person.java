package experiments;

public class Person {
    private int id;
    private String name;
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

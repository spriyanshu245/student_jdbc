
public class Student {

    private int id;
    private String name;
	private int mrk1;
	private int mrk2;

    public Student() {
    }

    public Student(int id, String name, int mrk1, int mrk2) {
        this.id = id;
        this.name = name;
        this.mrk1 = mrk1;
        this.mrk2 = mrk2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMrk1() {
        return mrk1;
    }

    public void setMrk1(int mrk1) {
        this.mrk1 = mrk1;
    }
    
    public int getMrk2() {
        return mrk2;
    }

    public void setMrk2(int mrk2) {
        this.mrk2 = mrk2;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mrk1=" + mrk1 +
                ", mrk2=" + mrk2 +
                '}';
    }
}
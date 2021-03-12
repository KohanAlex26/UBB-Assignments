package model;

public class Enrolment implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String personName, parentName;
    private int age;
    private WinterGame winterGame;

    public Enrolment() {
        ID = 0;
        personName = "";
        parentName = "";
        winterGame =null;
    }

    public Enrolment(int ID, String personName, String parentName,int age, WinterGame winterGame) {
        this.ID = ID;
        this.personName = personName;
        this.parentName = parentName;
        this.winterGame = winterGame;
        this.age=age;
    }

    public Enrolment(String personName, String parentName,int age, WinterGame winterGame) {
        this.personName = personName;
        this.parentName = parentName;
        this.winterGame =winterGame;
        this.age=age;
    }
    public Integer getId() {
        return ID;
    }

    public String getPersonName() {
        return personName;
    }

    public String getParentName() {
        return parentName;
    }


    public void setId(Integer Id) {
        this.ID = Id;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    public void setGame(WinterGame winterGame) {
        this.winterGame = winterGame;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public WinterGame getWinterGame() {
        return winterGame;
    }

    @Override
    public String toString()
    {
        String str = ID + "," + personName + ","+ parentName +","+ winterGame;
        return str;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Enrolment)
        {
            Enrolment c=(Enrolment)obj;
            return c.ID==this.ID;
        }
        return false;
    }
}

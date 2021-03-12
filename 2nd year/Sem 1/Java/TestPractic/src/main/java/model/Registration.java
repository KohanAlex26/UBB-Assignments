package model;

public class Registration implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String personName, phoneNumber,address;
    private int age;
    private MarathonRace marathonRace;

    public Registration() {
        ID = 0;
        personName = "";
        phoneNumber = "";
        marathonRace =null;
        age=0;
    }

    public Registration(int ID, String personName, String phoneNumber,String address,int age, MarathonRace marathonRace) {
        this.ID = ID;
        this.personName = personName;
        this.phoneNumber = phoneNumber;
        this.marathonRace = marathonRace;
        this.age=age;
        this.address=address;
    }

    public Registration(String personName) {
        this.personName = personName;
    }
    public Registration(String personName, String phoneNumber,String address, int age, MarathonRace race) {
        this.personName = personName;
        this.phoneNumber = phoneNumber;
        this.age=age;
        this.marathonRace =race;
        this.address=address;
    }
    public Integer getId() {
        return ID;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public MarathonRace getRace() {
        return marathonRace;
    }


    public void setId(Integer Id) {
        this.ID = Id;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setRace(MarathonRace marathonRace) {
        this.marathonRace = marathonRace;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        String str = ID + "," + personName + ","+ phoneNumber +","+ marathonRace+", "+address;
        return str;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Registration)
        {
            Registration c=(Registration)obj;
            return c.ID==this.ID;
        }
        return false;
    }
}

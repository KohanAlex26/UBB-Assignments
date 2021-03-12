package model;

public class Order implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String personName, personAddress, date;
    private Carpet carpet;

    public Order() {
        ID = 0;
        personName = "";
        personAddress = "";
        carpet =null;
        date = "";
    }

    public Order(int ID, String personName, String personAddress, String date, Carpet carpet) {
        this.ID = ID;
        this.personName = personName;
        this.personAddress = personAddress;
        this.carpet = carpet;
        this.date = date;
    }

    public Order(String personName, String date) {
        this.personName = personName;
        this.date = date;
    }
    public Order(String personName, String personAddress, String date, Carpet c) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.date = date;
        this.carpet =c;
    }
    public Integer getId() {
        return ID;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }


    public String getDate() {
        return date;
    }

    public Carpet getCarpet() {
        return carpet;
    }


    public void setId(Integer Id) {
        this.ID = Id;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setCarpet(Carpet carpet) {
        this.carpet = carpet;
    }


    @Override
    public String toString()
    {
        String str = ID + "," + personName + ","+ personAddress +","+ carpet +","+date;
        return str;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Order)
        {
            Order c=(Order)obj;
            return c.ID==this.ID;
        }
        return false;
    }
}

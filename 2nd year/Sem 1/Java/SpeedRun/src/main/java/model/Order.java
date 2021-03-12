package model;

public class Order implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String personName, personAddress, date;
    private Tractore tractore;

    public Order() {
        ID = 0;
        personName = "";
        personAddress = "";
        tractore =null;
        date = "";
    }

    public Order(int ID, String personName, String personAddress, String date, Tractore tractore) {
        this.ID = ID;
        this.personName = personName;
        this.personAddress = personAddress;
        this.tractore = tractore;
        this.date = date;
    }

    public Order(String personName, String date) {
        this.personName = personName;
        this.date = date;
    }
    public Order(String personName, String personAddress, String date, Tractore c) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.date = date;
        this.tractore =c;
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

    public Tractore getCarpet() {
        return tractore;
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

    public void setCarpet(Tractore tractore) {
        this.tractore = tractore;
    }


    @Override
    public String toString()
    {
        String str = ID + "," + personName + ","+ personAddress +","+ tractore +","+date;
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

package model;

public class CarReservation implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String ownerName, ownerAddress, phoneNumber, date;
    private Car car;

    public CarReservation() {
        ID = 0;
        ownerName = "";
        ownerAddress = "";
        phoneNumber = "";
        car=null;
        date = "";
    }

    public CarReservation(int ID, String ownerName, String ownerAddress, String phoneNumber, Car car, String date) {
        this.ID = ID;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.phoneNumber = phoneNumber;
        this.car=car;
        this.date = date;
    }

    public CarReservation(String ownerName, String phoneNumber, String date) {
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }
    public CarReservation(String ownerName, String ownerAddress, String phoneNumber, String date,Car c) {
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.car=c;
    }
    public Integer getId() {
        return ID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getDate() {
        return date;
    }

    public Car getCar() {
        return car;
    }


    public void setId(Integer Id) {
        this.ID = Id;
    }


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    @Override
    public String toString()
    {
        String str = ID + "," + ownerName + ","+ownerAddress+","+phoneNumber+","+car+","+date;
        return str;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof CarReservation)
        {
            CarReservation c=(CarReservation)obj;
            return c.ID==this.ID;
        }
        return false;
    }
}

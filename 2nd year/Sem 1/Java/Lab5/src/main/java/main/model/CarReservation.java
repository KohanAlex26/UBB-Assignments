package main.model;

public class CarReservation implements Identifiable<Integer>,java.io.Serializable{
    private int ID;
    private String ownerName, ownerAddress, phoneNumber, date;
    private Car car;
    private boolean isValid;

    public CarReservation() {
        ID = 0;
        ownerName = "";
        ownerAddress = "";
        phoneNumber = "";
        car=null;
        date = "";
        isValid=false;
    }

    public CarReservation(int ID, String ownerName, String ownerAddress, String phoneNumber, Car car, String date,boolean isReserved) {
        this.ID = ID;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.phoneNumber = phoneNumber;
        this.car=car;
        this.date = date;
        this.isValid=isReserved;
    }
    public CarReservation(String ownerName, String phoneNumber, String date) {
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
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

    public boolean isReserved() {
        return isValid;
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

    public void setReserved(boolean reserved) {
        isValid = reserved;
    }

    @Override
    public String toString()
    {
        String str = ID + "," + ownerName + ","+ownerAddress+","+phoneNumber+","+car+","+date+","+isValid;
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

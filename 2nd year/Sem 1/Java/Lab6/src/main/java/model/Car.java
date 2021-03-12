package model;

public class Car implements Identifiable<Integer>,java.io.Serializable {
    private String model,color,brand;
    private int seats;
    private int ID;
    private carStatus status;
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public Car() {
        this.model = "";
        this.color = "";
        this.brand = "";
        this.seats = 0;
        this.ID=0;
        this.status= carStatus.Unknown;
    }
    public Car(String model, String color, String brand, int seats) {
        this.model = model;
        this.color = color;
        this.brand = brand;
        this.seats = seats;
        status= carStatus.New;
    }

    public Car(int ID,String model, String color, String brand, int seats) {
        this.model = model;
        this.color = color;
        this.brand = brand;
        this.seats = seats;
        this.ID=ID;
        status= carStatus.New;
    }


    public Car(String brand,String model,int seats) {
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        status= carStatus.New;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public carStatus getStatus() {
        return status;
    }

    public void setStatus(carStatus status) {
        this.status = status;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", seats=" + seats +
                ", ID=" + ID +
                '}';
    }
}

package CarDomain;

import model.Identifiable;

public class Car implements Identifiable<Integer> {
    private String model,color,brand;
    private int seats;
    private int ID;

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
    }
    public Car(String model, String color, String brand, int seats) {
        this.model = model;
        this.color = color;
        this.brand = brand;
        this.seats = seats;
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

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", seats=" + seats +
                '}';
    }
}

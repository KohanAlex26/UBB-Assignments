package model;

public class Tractore implements Identifiable<Integer>,java.io.Serializable {
    private String name,color;
    private int dimension,price;
    private int ID;
    private carpetStatus status;
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public Tractore() {
        this.name = "";
        this.color = "";
        this.dimension = 0;
        this.ID=0;
        this.price=0;
        this.status= carpetStatus.Unknown;
    }
    public Tractore(String name, int dimension, String color, int price) {
        this.name = name;
        this.color = color;
        this.dimension = dimension;
        this.price=price;
        status= carpetStatus.New;
    }

    public Tractore(int ID, String name, int dimension, String color, int price) {
        this.name = name;
        this.color = color;
        this.dimension = dimension;
        this.ID=ID;
        status= carpetStatus.New;
    }


    public Tractore(String name, int dimension) {
        this.name = name;
        this.dimension = dimension;
        status= carpetStatus.New;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public carpetStatus getStatus() {
        return status;
    }

    public void setStatus(carpetStatus status) {
        this.status = status;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tractore{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", dimension=" + dimension +
                ", ID=" + ID +
                '}';
    }
}

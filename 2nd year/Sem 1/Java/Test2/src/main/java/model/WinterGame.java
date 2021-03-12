package model;

public class WinterGame implements Identifiable<Integer>,java.io.Serializable {
    private String name, type,date;
    private int minAge, maxAge;
    private int ID;
    private gameStatus status;
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public WinterGame() {
        this.name = "";
        this.type = "";
        this.minAge = 0;
        this.ID=0;
        this.maxAge =0;
        this.status= gameStatus.Unknown;
        this.date="";
    }
    public WinterGame(String name,String type,String date, int minAge, int maxAge) {
        this.name = name;
        this.type = type;
        this.date=date;
        this.minAge = minAge;
        this.maxAge = maxAge;
        status= gameStatus.New;
    }

    public WinterGame(int ID, String name, String type,String date,int minAge, int maxAge) {
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge=maxAge;
        this.ID=ID;
        status= gameStatus.New;
        this.date=date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public gameStatus getStatus() {
        return status;
    }

    public void setStatus(gameStatus status) {
        this.status = status;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WinterGame{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", date=" + date +
                ", ID=" + ID +
                '}';
    }
}

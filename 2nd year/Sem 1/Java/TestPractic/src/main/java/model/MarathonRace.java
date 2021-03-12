package model;

public class MarathonRace implements Identifiable<Integer>,java.io.Serializable {
    private String name, type,date;
    private int distance;
    private int ID;
//    private marathonRaceStatus status;
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public MarathonRace() {
        this.name = "";
        this.type = "";
        this.distance = 0;
        this.ID=0;
//        this.status= marathonRaceStatus.Unknown;
        this.date="";
    }
    public MarathonRace(String name, String type,String date,int distance) {
        this.name = name;
        this.type = type;
        this.distance = distance;
//        status= marathonRaceStatus.New;
        this.date=date;
    }

    public MarathonRace(int ID, String name, String type,String date, int distance) {
        this.name = name;
        this.type = type;
        this.distance = distance;
        this.ID=ID;
        this.date=date;
//        status= marathonRaceStatus.New;
    }


    public MarathonRace(String name, int distance) {
        this.name = name;
        this.distance = distance;
//        status= marathonRaceStatus.New;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

//    public marathonRaceStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(marathonRaceStatus status) {
//        this.status = status;
//    }


    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MarathonRace{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", distance=" + distance +
                ", ID=" + ID +
                '}';
    }
}

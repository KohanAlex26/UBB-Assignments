package model;

public class ComputerRepairRequest implements Identifiable<Integer> {
    private int ID;
    private String ownerName, ownerAddress, phoneNumber, model, problemDescription, date;

    public ComputerRepairRequest() {
        ID = 0;
        ownerName = "";
        ownerAddress = "";
        phoneNumber = "";
        model = "";
        problemDescription = "";
        date = "";
    }

    public ComputerRepairRequest(int ID, String ownerName, String ownerAddress, String phoneNumber, String model, String problemDescription, String date) {
        this.ID = ID;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.phoneNumber = phoneNumber;
        this.model = model;
        this.problemDescription = problemDescription;
        this.date = date;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer Id) {
        this.ID = Id;
    }

    @Override
    public String toString() {
        String str = ownerName + "," + ID + "," + model + "," + problemDescription;
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComputerRepairRequest) {
            ComputerRepairRequest c = (ComputerRepairRequest) obj;
            return this.ID == c.ID;
        }
        return false;
    }
}
//getters,setters

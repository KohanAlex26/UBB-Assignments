package main.model;

public interface Identifiable<Id>{
    public Id getId();
    public void setId(Id id);
}

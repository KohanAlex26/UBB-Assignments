package main.repo;

public class IDGenerator {
    private static int idGen=1;
    public static int getNextId(){
        return idGen++;
    }
}

package run;

import Repository.RequestRepository;
import model.ComputerRepairRequest;

public class main {
    public static void main(String[] args)
    {
        RequestRepository rr=new RequestRepository();
        rr.add(new ComputerRepairRequest(0,"a","b","c","d","e","f"));
        System.out.println(rr.findbyId(0));
    }
}

package ro.ubb.catalog.core.service;



import ro.ubb.catalog.core.model.Sportive;

import java.util.List;

public interface SportiveService {
    Sportive saveSportive(Sportive sportive);


    List<Sportive> getAllSportives();


}

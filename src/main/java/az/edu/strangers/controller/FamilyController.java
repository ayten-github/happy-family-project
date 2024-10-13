package az.edu.strangers.controller;

import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.service.FamilyService;

public class FamilyController {

    FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public void getAllStudents(){
        familyService.getAllFamilies();
    }

    public FamilyDto createNewFamily(ManDto man, WomanDto women){
        return familyService.createNewFamily(man, women);
    }
}

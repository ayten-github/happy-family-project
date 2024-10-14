package az.edu.strangers.controller;

import az.edu.strangers.dao.Family;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.service.FamilyService;

public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public void getAllFamilies(){
        familyService.getAllFamilies();
    }

    public FamilyDto createNewFamily(ManDto man, WomanDto women){
        return familyService.createNewFamily(man, women);
    }

    public FamilyDto bornChild(FamilyDto familyDto, String masculineName, String feminineName){
        return familyService.bornChild(familyDto, masculineName, feminineName);
    }
}

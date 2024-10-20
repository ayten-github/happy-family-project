package az.edu.strangers.controller;

import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Pet;
import az.edu.strangers.service.FamilyService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    public Family getFamilyById(final Integer index) {
        if (index < 0 && index >= familyService.count()) throw new IndexOutOfBoundsException();

        return familyService.getFamilyById(index);
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getFamiliesBiggerThan(Integer number) {
        return familyService.getFamiliesBiggerThan(number);
    }

    public List<Family> getFamiliesLessThan(Integer number) {
        return familyService.getFamiliesLessThan(number);
    }

    public Long countFamiliesWithMemberNumber( Integer number) {
        return number != null ? familyService.countFamiliesWithMemberNumber(number) : null;
    }

    public FamilyDto createNewFamily(ManDto man, WomanDto women) {
        return familyService.createNewFamily(man, women);
    }

    public boolean deleteFamilyByIndex(final int index) {
        if (index < 0 && index >= familyService.count()) throw new IndexOutOfBoundsException();

        return familyService.deleteFamilyByIndex(index);
    }

    public boolean deleteFamily(final Family family) {
        return family != null && deleteFamily(family);
    }

    public Family bornChild(final Family family, final String masculineName, final String feminineName) {
        if (family == null || masculineName == null || feminineName == null) return null;

        return familyService.bornChild(family, masculineName, feminineName);
    }

    public Optional<Family> adoptChild(final Family family, final Human child) {
        return familyService.adoptChild(family, child);
    }

    public void deleteAllChildrenOlderThen(final Integer age) {
        if (age != null && age >= 0) familyService.deleteAllChildrenOlderThen(age);
    }

    public Set<Pet> getPets(final Integer index) {
        if (index == null || index >= familyService.count() || index < 0) return null;
        return familyService.getPets(index);
    }

    public boolean addPet(final Integer index, final Pet pet) {
        if (familyService.count() <= index || index < 0 || pet == null || index == null) return false;

        return familyService.addPet(index, pet);
    }


}

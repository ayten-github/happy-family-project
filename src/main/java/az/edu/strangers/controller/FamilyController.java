package az.edu.strangers.controller;

import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Pet;
import az.edu.strangers.service.FamilyService;
import jakarta.validation.constraints.NotNull;

import java.util.List;
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
        if (index < 0 && index >= familyService.count()) throw new IndexOutOfBoundsException("Invalid index.");

        return familyService.getFamilyById(index);
    }

    public void displayAllFamilies() {
        if (familyService.count() == 0) System.out.println("No families available to display.");
        else familyService.displayAllFamilies();
    }

    public void displayFamiliesBiggerThan(int count) {
        if (count < 0) throw new IllegalArgumentException("Number can not be negative.");

        List<Family> families = familyService.getFamiliesBiggerThan(count);
        if (families.isEmpty()) {
            System.out.println("No families found with more than " + count + " members.");
        } else {
            families.forEach(family -> System.out.println(family.prettyFormat()));
        }
    }

    public void displayFamiliesLessThan(int count) {
        if (count <= 0) {
            System.out.println("Invalid number of members. Please enter a positive number.");
            return;
        }

        List<Family> families = familyService.getFamiliesLessThan(count);
        if (families.isEmpty()) {
            System.out.println("No families found with less than " + count + " members.");
        } else {
            families.forEach(family -> System.out.println(family.prettyFormat()));
        }
    }

    public long countFamiliesWithMemberNumber(Integer number) {
        if (number <= 0) {
            System.out.println("Invalid number of members. Please enter a positive number.");
            return -1;
        }
        return familyService.countFamiliesWithMemberNumber(number);
    }

    public void createNewFamily(Human father, Human mother) {
        if (father == null || mother == null) {
            System.out.println("Both mother and father must be provided.");
            return;
        }

        familyService.createNewFamily(mother, father);
    }

    public boolean deleteFamilyByIndex(final int index) {
        if (index < 0 && index >= familyService.count()) throw new IndexOutOfBoundsException("Invalid index.");

        return familyService.deleteFamilyByIndex(index);
    }

    public Family bornChild(
            @NotNull final Family family,
            @NotNull final String masculineName,
            @NotNull final String feminineName
    ) {
        if (masculineName.isEmpty() || feminineName.isEmpty())
            throw new IllegalArgumentException("Both masculine and feminine names must be provided.");

        return familyService.bornChild(family, masculineName, feminineName);
    }

    public Family adoptChild(@NotNull final Family family, @NotNull final Human child) {
        return familyService.adoptChild(family, child);
    }

    public void deleteAllChildrenOlderThen(@NotNull final Integer age) {
        if (age < 0) throw new IllegalArgumentException("Age can not be negative.");

        familyService.deleteAllChildrenOlderThen(age);
    }

    public Set<Pet> getPets(@NotNull final Integer index) {
        if (index < 0 || index >= familyService.count()) throw new IllegalArgumentException("Invalid index");

        return familyService.getPets(index);
    }

    public boolean addPet(@NotNull final Integer index, @NotNull final Pet pet) {
        if (index < 0 || index >= familyService.count()) throw new IllegalArgumentException("Invalid index");

        return familyService.addPet(index, pet);
    }

    public void loadData(@NotNull List<Family> families) {
        if (families.isEmpty()) throw new IllegalArgumentException("No families to load.");

        familyService.loadData(families);
    }

    public int count() {
        return familyService.count();
    }
}

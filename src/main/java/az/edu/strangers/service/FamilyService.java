package az.edu.strangers.service;

import az.edu.strangers.entity.Family;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Pet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FamilyService {

    List<Family> getAllFamilies();

    void displayAllFamilies();

    List<Family> getFamiliesBiggerThan(Integer number);

    List<Family> getFamiliesLessThan(Integer number);

    Long countFamiliesWithMemberNumber(Integer number);

    FamilyDto createNewFamily(ManDto man, WomanDto woman);

    boolean deleteFamilyByIndex(int index);

    Family bornChild(Family family, String masculineName, String feminineName);

    Optional<Family> adoptChild(Family family, Human child);

    void deleteAllChildrenOlderThen(Integer age);

    int count();

    Family getFamilyById(Integer index);

    Set<Pet> getPets(Integer index);

    boolean addPet(Integer index, Pet pet);
}

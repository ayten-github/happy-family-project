package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;

import java.util.List;
import java.util.Optional;

public interface FamilyService {

    void getAllFamilies();

    void displayAllFamilies();

    List<Family> getFamiliesBiggerThan(Integer number);

    List<Family> getFamiliesLessThan(Integer number);

    Long countFamiliesWithMemberNumber(Integer number);

    FamilyDto createNewFamily(ManDto man, WomanDto woman);

    void deleteFamilyByIndex(Long index);

    FamilyDto bornChild(FamilyDto family, String masculineName, String feminineName);

    Family adoptChild(Family family, Human child);

    void deleteAllChildrenOlderThen(Integer age);

    Long count();

    Optional<Family> getFamilyById(Long index);

    List<Pet> getPets(Long index);

    void addPet(Long index, Pet pet);
}

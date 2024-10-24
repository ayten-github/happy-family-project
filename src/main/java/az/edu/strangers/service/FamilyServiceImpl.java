package az.edu.strangers.service;

import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Pet;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FamilyServiceImpl implements FamilyService {

    private final FamilyDao familyDao;

    public FamilyServiceImpl(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    @Override
    public Optional<Family> getFamilyById(final Integer index) {
        return familyDao.getFamilyByIndex(index);
    }

    @Override
    public void displayAllFamilies() {
        familyDao.getAllFamilies()
                .forEach(System.out::println);
    }

    @Override
    public List<Family> getFamiliesBiggerThan(Integer number) {
        return getAllFamilies().stream()
                .filter(family -> family.getFamilyCount() > number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Family> getFamiliesLessThan(Integer number) {
        return getAllFamilies().stream()
                .filter(family -> family.getFamilyCount() < number)
                .collect(Collectors.toList());
    }

    @Override
    public Long countFamiliesWithMemberNumber(Integer number) {
        return familyDao.getAllFamilies()
                .stream()
                .filter(family -> family.getFamilyCount() == number)
                .count();
    }

    @Override
    public void createNewFamily(Human father, Human mother) {
        Family family = new Family(father, mother);
        familyDao.saveFamily(family);
    }

    @Override
    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public boolean deleteFamily(final Family family) {
        return familyDao.deleteFamily(family);
    }

    @Override
    public Family bornChild(Family family, String masculineName, String feminineName) {
        Human newbornChild = family.bornChild(masculineName, feminineName);
        familyDao.saveFamily(family);

        System.out.println("New child born: " + newbornChild.getName() + " " + newbornChild.getSurname());

        return family;
    }

    @Override
    public Family adoptChild(final @NotNull Family family, final @NotNull Human child) {
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    @Override
    public void deleteAllChildrenOlderThen(Integer age) {
        familyDao.getAllFamilies().forEach(family -> {
            family.getChildren().removeIf(child -> child.getAge() > age);
            familyDao.saveFamily(family);
        });
    }

    @Override
    public int count() {
        return familyDao.getAllFamilies().size();
    }

    @Override
    public Set<Pet> getPets(final Integer index) {
        return familyDao.getFamilyByIndex(index)
                .orElseThrow(() -> new IllegalArgumentException("Invalid index"))
                .getPets();
    }

    @Override
    public boolean addPet(final Integer index, final Pet pet) {
        Family family = familyDao.getFamilyByIndex(index)
                .orElseThrow(() -> new IllegalArgumentException("Invalid index"));

        if (family == null) return false;

        family.addPet(pet);
        familyDao.saveFamily(family);
        return true;
    }

    @Override
    public void loadData(List<Family> families) {
        familyDao.loadData(families);
    }

    public FamilyDto convertFamily(Family family) {
        return new FamilyDto(family.getFather(), family.getMother());
    }
}

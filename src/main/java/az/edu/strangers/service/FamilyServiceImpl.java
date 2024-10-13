package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;

import java.util.List;
import java.util.Optional;

public class FamilyServiceImpl implements FamilyService{

    private final FamilyDao<Family> familyDao;

    public FamilyServiceImpl(FamilyDao<Family> familyDao) {
        this.familyDao = familyDao;
    }

    @Override
    public void getAllFamilies() {
        List<Family> familyList = familyDao.getAllFamilies();
        if (familyList.isEmpty()){
            System.out.println("There is no family!");
        } else {
            System.out.println("Students in List:");
            for (Family family : familyList) {
                System.out.println(family);
            }
        }
    }

    @Override
    public void displayAllFamilies() {

    }

    @Override
    public List<Family> getFamiliesBiggerThan(Integer number) {
        return null;
    }

    @Override
    public List<Family> getFamiliesLessThan(Integer number) {
        return null;
    }

    @Override
    public Long countFamiliesWithMemberNumber(Integer number) {
        return null;
    }

    @Override
    public FamilyDto createNewFamily(ManDto man, WomanDto women) {
        Woman mother = new Woman(women.getName(), women.getSurname(), women.getYear());
        Man father = new Man(man.getName(), man.getSurname(), man.getYear());

        Family familyEntity = new Family(father, mother);

        Family savedFamily = familyDao.saveFamily(familyEntity);

        return new FamilyDto(savedFamily.getFather(),savedFamily.getMother());
    }

    @Override
    public void deleteFamilyByIndex(Long index) {

    }

    @Override
    public Family bornChild(Family family, String masculineName, String feminineName) {
        return null;
    }

    @Override
    public Family adoptChild(Family family, Human child) {
        return null;
    }

    @Override
    public void deleteAllChildrenOlderThen(Integer age) {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public Optional<Family> getFamilyById(Long index) {
        return Optional.empty();
    }

    @Override
    public List<Pet> getPets(Long index) {
        return null;
    }

    @Override
    public void addPet(Long index, Pet pet) {

    }
}

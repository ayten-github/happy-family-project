package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.HumanDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void displayAllFamilies() {
        List<Family> familyList = familyDao.getAllFamilies();
        for (int i = 0; i < familyList.size(); i++) {
            System.out.println(familyList.get(i).toString());
        }

    }

    @Override
    public List<Family> getFamiliesBiggerThan(Integer number) {
        List<Family> familyList = getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount() > number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Family> getFamiliesLessThan(Integer number) {
        List<Family> familyList = getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount() < number)
                .collect(Collectors.toList());
    }

    @Override
    public Long countFamiliesWithMemberNumber(Integer number) {
        List<Family> familyList = getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount() == number)
                .count();
    }

    @Override
    public FamilyDto createNewFamily(ManDto man, WomanDto woman) {
        Woman mother = new Woman(woman.getName(), woman.getSurname(), woman.getYear());
        Man father = new Man(man.getName(), man.getSurname(), man.getYear());

        Family familyEntity = new Family(father, mother);

        Family savedFamily = familyDao.saveFamily(familyEntity);

        return new FamilyDto(savedFamily.getFather(), savedFamily.getMother());
    }

    @Override
    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    @Override
    public FamilyDto bornChild(FamilyDto familyDto, String masculineName, String feminineName) {
        Human father = familyDto.getFather();

        boolean isBoy = Math.random() < 0.5;

        String surname = father.getSurname();
        int birthYear = java.time.Year.now().getValue();

        Human newbornChild;
        if (isBoy) {
            newbornChild = new Man(masculineName, surname, birthYear);
        } else {
            newbornChild = new Woman(feminineName, surname, birthYear);
        }

        List<Human> children = familyDto.getChildren();
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(newbornChild);
        familyDto.setChildren(children);

        System.out.println("New child born: " + newbornChild.getName() + " " + newbornChild.getSurname());

        familyDao.updateFamily(familyDto);

        return familyDto;
    }

    @Override
    public Optional<Family> adoptChild(Family family, Human child) {
        if (family == null || child == null) return Optional.empty();
        family.addChild(child);
        return Optional.of(family);
    }

    @Override
    public void deleteAllChildrenOlderThen(Integer age) {
        List<Family> familyList = getAllFamilies();
        int nowYear = LocalDate.now().getYear();
        familyList.forEach(family -> {
            List<Human> olderChildren = family.getChildren().stream().filter(child -> (nowYear - child.getYear()) <= age)
                    .collect(Collectors.toList());
            family.setChildren(olderChildren);
        });
    }

    @Override
    public Long count() {
        return (long) familyDao.getAllFamilies().size();
    }

    @Override
    public Optional<Family> getFamilyById(Long index) {
        List<Family> familyList = familyDao.getAllFamilies();
        return index == null || familyList.size() <= index ? Optional.empty() :
                Optional.of(familyList.get(index.intValue()));
    }

    @Override
    public Optional<Pet> getPets(Long index) {
        List<Family> familyList = familyDao.getAllFamilies();
        return (index >= familyList.size() || index < 0) ? Optional.empty() :
                Optional.of(familyList.get(index.intValue()).getPet());
    }

    @Override
    public boolean addPet(final Long index, final Pet pet) {
        if (familyDao.getAllFamilies().size() <= index || index < 0 || pet == null) return false;
        Family family = familyDao.getAllFamilies().get(index.intValue());
        family.setPet(pet);
        return true;
    }
}

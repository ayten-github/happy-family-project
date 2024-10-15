package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.HumanDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FamilyServiceImpl implements FamilyService{

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
        List<Family> familyList= familyDao.getAllFamilies();
        for (int i = 0; i < familyList.size(); i++) {
            System.out.println(familyList.get(i).toString());
        }

    }

    @Override
    public List<Family> getFamiliesBiggerThan(Integer number) {
        List<Family> familyList=getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount()>number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Family> getFamiliesLessThan(Integer number) {
        List<Family> familyList=getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount()<number)
                .collect(Collectors.toList());
    }

    @Override
    public Long countFamiliesWithMemberNumber(Integer number) {
        List<Family> familyList=getAllFamilies();
        return familyList.stream().filter(family -> family.getFamilyCount()==number)
                .count();
    }

    @Override
    public FamilyDto createNewFamily(ManDto man, WomanDto woman) {
        Woman mother = new Woman(woman.getName(), woman.getSurname(), woman.getYear());
        Man father = new Man(man.getName(), man.getSurname(), man.getYear());

        Family familyEntity = new Family(father, mother);

        Family savedFamily = familyDao.saveFamily(familyEntity);

        return new FamilyDto(savedFamily.getFather(),savedFamily.getMother());
    }

    @Override
    public boolean deleteFamilyByIndex(Long index) {
        List<Family> familyList=familyDao.getAllFamilies();
        if(index<0||index>familyList.size()) return false;
        System.out.println(familyList.remove(index.intValue()));
        return true;
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

        familyDao.updateFamily(familyDto, 0L);

        return familyDto;
    }

    @Override
    public Family adoptChild(Family family, Human child) {
        if(family==null||child==null) return null;
        family.addChild(child);
        return family;
    }

    @Override
    public void deleteAllChildrenOlderThen(Integer age) {
        List<Family> familyList=getAllFamilies();
        familyList.forEach(family -> {
            List<Human> olderChildren=family.getChildren().stream().filter(child->child.getYear()<=age)
                    .collect(Collectors.toList());
            family.setChildren(olderChildren);
          //  familyDao.updateFamily(familyDao);
        });

    }

    @Override
    public Long count() {
        return (long)familyDao.getAllFamilies().size();
    }

    @Override
    public Optional<Family> getFamilyById(Long index) {
        List<Family> familyList=familyDao.getAllFamilies();
        return index==null||familyList.size()<=index? Optional.empty():
        Optional.of(familyList.get(index.intValue()));
    }

    @Override
    public Optional<Pet> getPets(Long index) {
        List<Family> familyList=familyDao.getAllFamilies();
        return index>= familyList.size()? Optional.empty():
                Optional.of(familyList.get(index.intValue()).getPet());
    }

    @Override
    public boolean addPet(final Long index,final Pet pet) {
        List<Family> familyList=familyDao.getAllFamilies();
        if (familyList.size()<=index||index<0) return false;
        familyList.get(index.intValue());
        return true;
    }
}

package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public Family getFamilyById(final Integer index) {
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
                .peek(System.out::println)
                .filter(family -> family.getFamilyCount() == number)
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

    public boolean deleteFamily(final Family family){
        return familyDao.deleteFamily(family);
    }

    @Override
    public FamilyDto bornChild(FamilyDto familyDto, String masculineName, String feminineName) {
        Human father = familyDto.getFather();

        boolean isBoy = Math.random() < 0.5;

        String surname = father.getSurname();
        int birthYear = java.time.Year.now().getValue();

        Human newbornChild = isBoy ?
                new Man(masculineName, surname, birthYear) :
                new Woman(feminineName, surname, birthYear);

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
    public Optional<Family> adoptChild(final Family family, final Human child) {
        if (family == null || child == null) return Optional.empty();
        family.addChild(child);

        FamilyDto familyDto = new FamilyDto(family.getFather(), family.getMother());
        familyDto.setPets(family.getPets());
        familyDto.setChildren(family.getChildren());

        familyDao.updateFamily(familyDto);

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
    public int count() {
        return familyDao.getAllFamilies().size();
    }

    @Override
    public Set<Pet> getPets(final Integer index) {
        return familyDao.getFamilyByIndex(index).getPets();
    }

    @Override
    public boolean addPet(final Integer index, final Pet pet) {
        Family family = familyDao.getAllFamilies().get(index);
        family.addPet(pet);
        return true;
    }
}

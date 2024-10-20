package az.edu.strangers.service;

import az.edu.strangers.entity.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Man;
import az.edu.strangers.entity.Woman;
import az.edu.strangers.entity.Pet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
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
                .filter(family -> family.getFamilyCount() == number)
                .count();
    }

    @Override
    public FamilyDto createNewFamily(ManDto man, WomanDto woman) {
        Woman mother = new Woman(woman.getName(), woman.getSurname(), woman.getBirthDate(), woman.getIQ());
        Man father = new Man(man.getName(), man.getSurname(), man.getBirthDate(), man.getIQ());

        Family familyEntity = new Family(father, mother);

        Family savedFamily = familyDao.saveFamily(familyEntity);

        return new FamilyDto(savedFamily.getFather(), savedFamily.getMother());
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
        Human father = family.getFather();

        boolean isBoy = new Random().nextBoolean();

        String surname = father.getSurname();
        int birthYear = LocalDate.now().getYear();

        Human newbornChild = isBoy ?
                new Man(masculineName, surname, birthYear) :
                new Woman(feminineName, surname, birthYear);

        List<Human> children = family.getChildren();
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(newbornChild);
        family.setChildren(children);

        System.out.println("New child born: " + newbornChild.getName() + " " + newbornChild.getSurname());

        return family;
    }

    @Override
    public Optional<Family> adoptChild(final Family family, final Human child) {
        if (family == null || child == null) return Optional.empty();

        System.out.println("------");
        System.out.println(getAllFamilies());
        System.out.println("------");

        for (Family existingFamily : getAllFamilies()) {
            System.out.println("1231231231231");
            if (existingFamily.getFather().getName().equals(family.getFather().getName()) &&
                    existingFamily.getMother().getName().equals(family.getMother().getName())) {
                System.out.println("a");
                existingFamily.addChild(child);
                break;
            }

        }
        return Optional.of(family);
    }

    @Override
    public void deleteAllChildrenOlderThen(Integer age) {
        List<Family> familyList = getAllFamilies();
        int nowYear = LocalDate.now().getYear();

        familyList.forEach(family -> {
            List<Human> olderChildren = family.getChildren().stream()
                    .filter(child -> (nowYear - child.convertMillisDate(child.getYear()).getYear()) <= age)
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

    public FamilyDto convertFamily(Family family) {
        return new FamilyDto(family.getFather(), family.getMother());
    }


}

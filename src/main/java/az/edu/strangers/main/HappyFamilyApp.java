package az.edu.strangers.main;

import az.edu.strangers.controller.FamilyController;
import az.edu.strangers.dao.CollectionFamilyDao;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.entity.Woman;
import az.edu.strangers.entity.Dog;
import az.edu.strangers.entity.DomesticCat;
import az.edu.strangers.entity.Pet;
import az.edu.strangers.service.FamilyService;
import az.edu.strangers.service.FamilyServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class HappyFamilyApp {

    public static void main(String[] args) {

        Set<String> habits = new HashSet<>();
        habits.add("Eat");
        habits.add("Drink");
        habits.add("Sleep");
        Pet dog = new Dog("Rock", 5, 75, habits);
//        dog.eat();
//        dog.foul();
//        dog.respond();
        Pet cat = new DomesticCat("Whiskers", 2, 50, habits);
//        cat.eat();
//        cat.respond();
//        cat.foul();


//        Man father = new Man("Vito", "Karleone", 1973, 90);
////        father.repairCar();
////        mother.makeUp();
//
//        Family family = new Family(father, mother);
//        family.addPet(cat);
//        family.addPet(dog);

//        Map<DayOfWeek, String> schedule = new HashMap<>();
//        schedule.put(DayOfWeek.MONDAY , "Go to School");
//        schedule.put(DayOfWeek.TUESDAY , "Play football");
//        Human child = new Human("Michael", "Karleone", 1999, 90, schedule, family);
//
//        family.addChild(child);

//        System.out.println(family);
//
//        mother.greetPet();
//        child.describePet();

        //DAO
        FamilyDao familyDao = new CollectionFamilyDao();

        //Service
        FamilyService familyService =  new FamilyServiceImpl(familyDao);

        //Controller
        FamilyController familyController =   new FamilyController(familyService);

        FamilyDto createdFamily = familyController.createNewFamily(
                new ManDto("Vito", "Karleone", 1973),
                new WomanDto("Jane", "Karleone", 1975) );

        familyController.addPet(0,dog);

        familyController.bornChild(createdFamily,"Ronaldo","Georgina");

        System.out.println(familyController.getAllFamilies());

        Woman mother = new Woman("Jane", "Karleone", 1975, 80);
        System.out.println(mother.describeAge());


        Long l = familyController.countFamiliesWithMemberNumber(3);
        System.out.println(l);
    }
}

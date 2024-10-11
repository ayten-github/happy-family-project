package az.edu.strangers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HappyFamilyApp {

    public static void main(String[] args) {
        Set<String> habits = new HashSet<>();
        habits.add("Bark");
        habits.add("Sleep");
        Pet dog = new Dog("Rock", 5, 75, habits);
        dog.eat();
        dog.foul();
        dog.respond();
        Pet cat = new DomesticCat("Whiskers", 2, 50, habits );
        cat.eat();
        cat.respond();
        cat.foul();

        Woman mother = new Woman("Jane", "Karleone", 1975, 80);
        Man father = new Man("Vito", "Karleone", 1973, 90);
        father.repairCar();
        mother.makeUp();

        Family family = new Family(mother, father);
        family.setPet(dog);

        //String[][] schedule = {{"Monday", "Go to school"}, {"Tuesday", "Play football"}};
        Map<DayOfWeek , String> schedule = new HashMap<>();
        schedule.put(DayOfWeek.MONDAY , "Go to School");
        schedule.put(DayOfWeek.TUESDAY , "Play football");
        Human child = new Human("Michael", "Karleone", 1999, 90, schedule, family);

        family.addChild(child);


        System.out.println(family);

        mother.greetPet();
        child.describePet();
    }
}

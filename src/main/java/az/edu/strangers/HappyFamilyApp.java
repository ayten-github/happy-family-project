package az.edu.strangers;

public class HappyFamilyApp {

    public static void main(String[] args) {
        String[] habits = {"eat", "drink", "sleep"};
        Pet dog = new Pet("dog", "Rock", 5, 75, habits);

        Human mother = new Human("Jane", "Karleone", 1975);
        Human father = new Human("Vito", "Karleone", 1973);

        Family family = new Family(mother, father);
        family.setPet(dog);

        String[][] schedule = {{"Monday", "Go to school"}, {"Tuesday", "Play football"}};
        Human child = new Human("Michael", "Karleone", 1999, 90, schedule, family);

        family.addChild(child);

        System.out.println(family);

        child.greetPet();
        child.describePet();
    }
}

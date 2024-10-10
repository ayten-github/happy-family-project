package az.edu.strangers;

public class HappyFamilyApp {

    public static void main(String[] args) {
        String[] habits = {"eat", "drink", "sleep"};
        Pet dog = new Dog("Rock", 5, 75, habits);
        dog.eat();
        dog.foul();
        dog.respond();
        Pet cat = new DomesticCat("Whiskers", 2, 50, new String[]{"sleep", "meow"});
        cat.eat();
        cat.respond();
        cat.foul();

        Woman mother = new Woman("Jane", "Karleone", 1975, 80);
        Man father = new Man("Vito", "Karleone", 1973, 90);

        Family family = new Family(mother, father);
        family.setPet(dog);

        //String[][] schedule = {{"Monday", "Go to school"}, {"Tuesday", "Play football"}};
        String[][] schedule = new String[2][2];
        schedule[0][0] = DayOfWeek.MONDAY.name();
        schedule[0][1] = "Go to school";
        schedule[1][0] = DayOfWeek.TUESDAY.name();
        schedule[1][1] = "Play football";
        Human child = new Human("Michael", "Karleone", 1999, 90, schedule, family);

        family.addChild(child);


        Human newChild = family.bornChild();
        family.addChild(newChild);

        System.out.println(family);

        child.greetPet(father);
        child.describePet();
    }
}

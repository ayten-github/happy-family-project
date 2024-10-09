package az.edu.strangers;

public class HappyFamilyApp {
    public static void main(String[] args) {
        Pet pet1 = new Pet("dog", "Charlie", 4, 33, new String[]{"drink", "play", "eat"});
        Pet pet2 = new Pet("cat", "Mex", 5, 49, new String[]{"sleep", "play", "eat"});

        Human mother = new Human(" Helen", "Miren", 1991);
        Human father = new Human("Fred", "Miren", 1989);
        Human child = new Human("Anna", "Miren", 2012, mother, father);
        child.pet = pet1;
        child.greetPet();
        pet2.eat();
        pet1.foul();
        pet2.respond();
    }
}

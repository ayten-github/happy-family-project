package az.edu.strangers;

import java.util.Set;

public class DomesticCat extends Pet implements Foulable {

    public DomesticCat(String nickName, Integer age, Integer trickLevel, Set<String> habits) {
        this.nickName = nickName;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
        this.species = Species.getSpecies("Cat");
    }

    @Override
    public void respond() {
        System.out.printf("Meow,I am %s!\n", nickName);
    }

    @Override
    public void foul() {
        System.out.println("Screaming furniture");
    }
}

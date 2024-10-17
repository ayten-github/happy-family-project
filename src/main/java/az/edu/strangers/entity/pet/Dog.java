package az.edu.strangers.entity.pet;

import java.util.Set;

public class Dog extends Pet implements Foulable {

    public Dog(String nickName, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickName, age, trickLevel, habits);
        this.species = Species.getSpecies("Dog");
    }

    @Override
    public void respond() {
        System.out.printf("Haw-Haw,I am %s!\n", nickName);
    }

    @Override
    public void foul() {
        System.out.println("Digging holes");
    }
}

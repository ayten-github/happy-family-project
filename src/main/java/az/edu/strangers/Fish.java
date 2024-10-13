package az.edu.strangers;

import java.util.Set;

public class Fish extends Pet {

    public Fish(String nickName, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickName, age, trickLevel, habits);
        this.species = Species.getSpecies("Fish");
    }

    @Override
    public void respond() {
        System.out.printf("Hi,I am %s!\n", nickName);
    }
}

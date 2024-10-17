package az.edu.strangers.entity.pet;

import java.util.Set;

public class RoboCat extends Pet {

    public RoboCat(String nickName, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickName, age, trickLevel, habits);
        this.species = Species.getSpecies("Cat");
    }

    @Override
    public void respond() {
        System.out.printf("Beep Bop. I am %s!\n", nickName);
    }

}

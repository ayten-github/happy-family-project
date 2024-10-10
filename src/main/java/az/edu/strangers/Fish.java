package az.edu.strangers;

public class Fish extends Pet {

    public Fish(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(nickName, age, trickLevel, habits);
        this.species = Species.getSpecies("Fish");
    }

    @Override
    public void respond() {
        System.out.printf("Hi,I am %s!\n", nickName);
    }
}

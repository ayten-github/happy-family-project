package az.edu.strangers;

public class DomesticCat extends Pet implements Foulable {

    public DomesticCat(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(nickName, age, trickLevel, habits);
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

package az.edu.strangers;

public class DomesticCat extends Pet {

    public DomesticCat(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(Species.CAT, nickName, age, trickLevel, habits);
    }

    @Override
    public void respond() {
        System.out.printf("Meow,I am %s!/n", nickName);
    }

    @Override
    public void foul() {
        System.out.println("Screaming furniture");
    }
}

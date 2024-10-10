package az.edu.strangers;

public class Fish extends Pet {

    public Fish(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(Species.BIRD, nickName, age, trickLevel, habits);
    }

    @Override
    public void respond() {
        System.out.printf("Hi,I am %s!/n", nickName);
    }
}

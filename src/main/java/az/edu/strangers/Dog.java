package az.edu.strangers;

public class Dog extends Pet {

    public Dog(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(Species.DOG, nickName, age, trickLevel, habits);
    }

    @Override
    public void respond() {
        System.out.printf("Haw-Haw,I am %s!/n", nickName);
    }

    @Override
    public void foul() {
        System.out.println("Digging holes");
    }
}

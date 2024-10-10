package az.edu.strangers;

public class RoboCat extends Pet {

    public RoboCat(String nickName, Integer age, Integer trickLevel, String[] habits) {
        super(Species.CAT, nickName, age, trickLevel, habits);
    }

    @Override
    public void respond() {
        System.out.printf("Beep Bop. I am %s!/n", nickName);
    }

}

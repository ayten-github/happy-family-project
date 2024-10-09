package az.edu.strangers;

import java.util.Arrays;

public class Pet {
    public String species;
    public String nickname;
    public Integer age;
    public Integer tricklevel;
    public String[] habits;

    public Pet() {}
    public Pet(String species, String nickname, Integer age, Integer tricklevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.tricklevel = tricklevel;
        this.habits = habits;
    }

    public Pet(String species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }


    public void eat() {

        System.out.println("I am eating");
    }

    public void respond() {
        System.out.printf("Hello, owner. I am  %s I miss you /n", nickname);
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }

    public String toString() {
        return "%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}"
                .formatted(species, nickname, age, trickLevel, Arrays.toString(habits));
    }
}

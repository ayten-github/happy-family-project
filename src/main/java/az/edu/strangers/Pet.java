package az.edu.strangers;

import java.util.Arrays;

public class Pet {
    private String species;
    private String nickName;
    private Integer age;
    private Integer trickLevel;
    private String[] habits;

    public Pet() {
    }

    public Pet(String species, String nickName) {
        this.species = species;
        this.nickName = nickName;
    }

    public Pet(String species, String nickName, Integer age, Integer trickLevel, String[] habits) {
        this.species = species;
        this.nickName = nickName;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public void eat() {

        System.out.println("I am eating");
    }

    public void respond() {
        System.out.printf("Hello, owner. I am  %s I miss you /n", getNickName());
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(Integer trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }


    public String toString() {
        return String.format("{ Pet: species=%s ,nickname=%s, age=%d, trickLevel=%d, habits=%s }"
                , getSpecies(), getNickName(), getAge(), getTrickLevel(), Arrays.toString(getHabits()));
    }
}

package az.edu.strangers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pet {

    protected Species species;
    protected String nickName;
    protected Integer age;
    protected Integer trickLevel;
    protected Set<String> habits;


    public Pet() {
    }

    public Pet(Species species, String nickName) {
        this.species = species;
        this.nickName = nickName;
    }

    public Pet(String nickName, Integer age, Integer trickLevel, Set<String> habits) {
        this.nickName = nickName;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public void eat() {
        System.out.println(species.getSpeciesName() + " is eating.");
    }

    public abstract void respond();

    public void foul() {
        System.out.println("I need to cover it up");
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
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

    public Set<String> getHabits() {
        return habits;
    }

    public void setHabits(Set<String> habits) {
        this.habits = habits;
    }

    public String toString() {
        return "%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}"
                .formatted(species, nickName, age, trickLevel, habits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(species, pet.species) &&
                Objects.equals(nickName, pet.nickName) &&
                Objects.equals(age, pet.age) &&
                Objects.equals(trickLevel, pet.trickLevel) &&
                Objects.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(species, nickName, age, trickLevel, habits);
        return result;
    }
}

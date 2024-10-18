package az.edu.strangers;

import az.edu.strangers.entity.pet.Dog;
import az.edu.strangers.entity.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    private Pet pet;

    @BeforeEach
    void setUp() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        pet = new Dog( "Rock", 5, 75, habits);
    }

    @Test
    void testConstructor() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        assertEquals("DOG", pet.getSpecies().name());
        assertEquals("Rock", pet.getNickName());
        assertEquals(5, pet.getAge());
        assertEquals(75, pet.getTrickLevel());
        assertEquals(habits, pet.getHabits());
    }

    @Test
    void testToString() {
        String expected = "DOG{nickname='Rock', age=5, trickLevel=75, habits=[sleep, eat]}";
        assertEquals(expected, pet.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        Pet pet2 = new Dog( "Rock", 5, 75, habits);
        assertEquals(pet, pet2);
        assertEquals(pet.hashCode(), pet2.hashCode());
    }
}
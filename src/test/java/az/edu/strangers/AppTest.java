package az.edu.strangers;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void testPetToString() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        habits.add("drink");
        Pet pet = new Dog( "Rock", 5, 75, habits);
        String expected = "dog{nickname='Rock', age=5, trickLevel=75, habits=[eat, drink, sleep]}";
        assertEquals(expected, pet.toString());
    }
}

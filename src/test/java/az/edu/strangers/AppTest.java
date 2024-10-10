package az.edu.strangers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testPetToString() {
        Pet pet = new Pet(Species.DOG, "Rock", 5, 75, new String[]{"eat", "drink", "sleep"});
        String expected = "dog{nickname='Rock', age=5, trickLevel=75, habits=[eat, drink, sleep]}";
        assertEquals(expected, pet.toString());
    }
}

package az.edu.strangers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet("dog", "Rock", 5, 75, new String[]{"eat", "sleep"});
    }

    @Test
    void testConstructor() {
        assertEquals("dog", pet.getSpecies());
        assertEquals("Rock", pet.getNickName());
        assertEquals(5, pet.getAge());
        assertEquals(75, pet.getTrickLevel());
        assertArrayEquals(new String[]{"eat", "sleep"}, pet.getHabits());
    }

    @Test
    void testToString() {
        String expected = "dog{nickname='Rock', age=5, trickLevel=75, habits=[eat, sleep]}";
        assertEquals(expected, pet.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Pet pet2 = new Pet("dog", "Rock", 5, 75, new String[]{"eat", "sleep"});
        assertEquals(pet, pet2);
        assertEquals(pet.hashCode(), pet2.hashCode());
    }
}
package az.edu.strangers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    private Human human;
    private Pet pet;
    private Family family;
    private String[][] schedule;

    @BeforeEach
    void setUp() {
        pet = new Pet("dog", "Rock", 5, 75, new String[]{"eat", "sleep"});
        Human mother = new Human("Jane", "Karleone", 1975);
        Human father = new Human("Vito", "Karleone", 1973);
        family = new Family(mother, father);
        family.setPet(pet);
        schedule = new String[][]{{"Monday", "Go to school"}, {"Tuesday", "Play football"}};
        human = new Human("Michael", "Karleone", 1999, 90, schedule, family);
    }

    @Test
    void testConstructor() {
        assertEquals("Michael", human.getName());
        assertEquals("Karleone", human.getSurname());
        assertEquals(1999, human.getYear());
        assertEquals(90, human.getIQ());
        assertArrayEquals(schedule, human.getSchedule());
    }

    @Test
    void testSettersAndGetters() {
        human.setName("John");
        human.setSurname("Doe");
        human.setIQ(95);

        assertEquals("John", human.getName());
        assertEquals("Doe", human.getSurname());
        assertEquals(95, human.getIQ());
    }

    @Test
    void testGreetPet() {
        human.greetPet();
    }

    @Test
    void testDescribePet() {
        human.describePet();
    }

    @Test
    void testToString() {
        String expected = "Human{name='Michael', surname='Karleone', year=1999, iq=90, schedule=[[Monday, Go to school], [Tuesday, Play football]]}";
        assertEquals(expected, human.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Human human2 = new Human("Michael", "Karleone", 1999, 90, schedule, family);
        assertEquals(human, human2);
        assertEquals(human.hashCode(), human2.hashCode());
    }
}

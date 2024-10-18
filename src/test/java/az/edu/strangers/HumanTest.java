package az.edu.strangers;

import az.edu.strangers.entity.human.Family;
import az.edu.strangers.entity.human.DayOfWeek;
import az.edu.strangers.entity.human.Human;
import az.edu.strangers.entity.pet.Dog;
import az.edu.strangers.entity.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    private Human human;
    private Pet pet;
    private Family family;
    private Map<DayOfWeek, String> schedule;

    @BeforeEach
    void setUp() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        pet = new Dog( "Rock", 5, 75, habits);
        Human mother = new Human("Jane", "Karleone", 1975);
        Human father = new Human("Vito", "Karleone", 1973);
        family = new Family(father, mother);
        family.addPet(pet);
        schedule = new HashMap<>();
        schedule.put(DayOfWeek.TUESDAY , "Play football");
        schedule.put(DayOfWeek.MONDAY , "Go to school");
        human = new Human("Michael", "Karleone", 1999, 90, schedule, family);
    }

    @Test
    void testConstructor() {
        assertEquals("Michael", human.getName());
        assertEquals("Karleone", human.getSurname());
        assertEquals(1999, human.getYear());
        assertEquals(90, human.getIQ());
        assertEquals(schedule, human.getSchedule());
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
        String expected = "Human{name='Michael', surname='Karleone', year=1999, iq=90, schedule={TUESDAY=Play football, MONDAY=Go to school}}";
        assertEquals(expected, human.toStringV2());
    }

    @Test
    void testEqualsAndHashCode() {
        Human human2 = new Human("Michael", "Karleone", 1999, 90, schedule, family);
        assertEquals(human, human2);
        assertEquals(human.hashCode(), human2.hashCode());
    }

}

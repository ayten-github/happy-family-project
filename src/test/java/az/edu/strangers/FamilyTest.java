package az.edu.strangers;

import az.edu.strangers.dao.Family;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    private Family family;
    private Human mother;
    private Human father;
    private Human child;
    private Pet pet;

    @BeforeEach
    void setUp() {
        Set<String> habits = new HashSet<>();
        habits.add("sleep");
        habits.add("eat");
        mother = new Human("Jane", "Karleone", 1975);
        father = new Human("Vito", "Karleone", 1973);
        family = new Family(father, mother);
        pet = new Dog( "Rock", 5, 75, habits);
        family.setPet(pet);

        Map<DayOfWeek , String> schedule = new HashMap<>();
        schedule.put(DayOfWeek.MONDAY , "Go to school");
        schedule.put(DayOfWeek.TUESDAY , "Play football");
        child = new Human("Michael", "Karleone", 1999, 90, schedule, family);
    }

    @Test
    void testAddChild() {
        family.addChild(child);
        assertEquals(1, family.getChildren().size());
        assertSame(child, family.getChildren().get(0));
    }

    @Test
    void testDeleteChildByIndex() {
        family.addChild(child);
        assertTrue(family.deleteChild(0).isPresent());
        assertEquals(0, family.getChildren().size());
    }

    @Test
    void testDeleteChildByHumanInput() {
        family.addChild(child);
        assertTrue(family.deleteChild(child));
        assertEquals(0, family.getChildren().size());
    }

    @Test
    void testCountFamily() {
        family.addChild(child);
        assertEquals(3, family.getFamilyCount()); // mother, father, child
    }

    @Test
    void testToString() {
        family.addChild(child);
        String expected = "Family{mother=Jane Karleone, father=Vito Karleone, children=[Human{name='Michael', surname='Karleone', year=1999, iq=90, schedule={TUESDAY=Play football, MONDAY=Go to school}}], pet=DOG{nickname='Rock', age=5, trickLevel=75, habits=[sleep, eat]}}";
        assertEquals(expected, family.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Family family2 = new Family(father, mother);
        family2.setPet(pet);
        assertEquals(family, family2);
        assertEquals(family.hashCode(), family2.hashCode());
    }
}

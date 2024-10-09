package az.edu.strangers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    private Family family;
    private Human mother;
    private Human father;
    private Human child;
    private Pet pet;

    @BeforeEach
    void setUp() {
        mother = new Human("Jane", "Karleone", 1975);
        father = new Human("Vito", "Karleone", 1973);
        family = new Family(mother, father);
        pet = new Pet("dog", "Rock", 5, 75, new String[]{"eat", "sleep"});
        family.setPet(pet);

        String[][] schedule = {{"Monday", "Go to school"}, {"Tuesday", "Play football"}};
        child = new Human("Michael", "Karleone", 1999, 90, schedule, family);
    }

    @Test
    void testAddChild() {
        family.addChild(child);
        assertEquals(1, family.getChildren().length);
        assertSame(child, family.getChildren()[0]);
    }

    @Test
    void testDeleteChildByIndex() {
        family.addChild(child);
        assertTrue(family.deleteChild(0));
        assertEquals(0, family.getChildren().length);
    }

    @Test
    void testDeleteChildByHumanInput() {
        family.addChild(child);
        assertTrue(family.deleteChild(child));
        assertEquals(0, family.getChildren().length);
    }

    @Test
    void testCountFamily() {
        family.addChild(child);
        assertEquals(3, family.getFamilyCount()); // mother, father, child
    }

    @Test
    void testToString() {
        family.addChild(child);
        String expected = "Family{mother=Jane Karleone, father=Vito Karleone, children=[Human{name='Michael', surname='Karleone', year=1999, iq=90, schedule=[[Monday, Go to school], [Tuesday, Play football]]}], pet=dog{nickname='Rock', age=5, trickLevel=75, habits=[eat, sleep]}";
        assertEquals(expected, family.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Family family2 = new Family(mother, father);
        family2.setPet(pet);
        assertEquals(family, family2);
        assertEquals(family.hashCode(), family2.hashCode());
    }
}
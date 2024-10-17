package az.edu.strangers.service;

import az.edu.strangers.*;
import az.edu.strangers.dao.CollectionFamilyDao;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.FamilyDto;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyServiceImplTest {
    private static final FamilyDao dao = new CollectionFamilyDao();
    private static final FamilyServiceImpl service = new FamilyServiceImpl(dao);

    @BeforeAll
    static void beforeAll() {
        initializeDao();
    }

    @Test
    void testGetAllFamilies() {
        assertEquals(dao.getAllFamilies(), service.getAllFamilies());
    }

    @Test
    void testGetFamilyById() {
        Family familyById = service.getFamilyById(1);

        assertEquals(dao.getFamilyByIndex(1), familyById);
        assertEquals("Alex", familyById.getFather().getName());
        assertEquals("Smith", familyById.getFather().getSurname());
    }

    @Test
    void testGetFamiliesBiggerThan() {
        int number = 3;
        assertEquals(4, service.getFamiliesBiggerThan(number).size());
    }

    @Test
    void testGetFamiliesLessThan() {
        int number = 4;
        assertEquals(1, service.getFamiliesLessThan(number).size());
    }

    @Test
    void testCountFamiliesWithMemberNumber() {
        int number = 3;
        assertEquals(1, service.countFamiliesWithMemberNumber(number));
    }

    @Test
    void testCreateNewFamily() {
        ManDto manDto = new ManDto("Dexter", "Fulhawks", 1970);
        WomanDto womanDto = new WomanDto("Layla", "Sweethome", 1972);
        FamilyDto savedFamilyDto = service.createNewFamily(manDto, womanDto);
        Family familyByIndex = dao.getFamilyByIndex(dao.getAllFamilies().size() - 1);

        assertEquals(familyByIndex.getFather(), savedFamilyDto.getFather());
        assertEquals(familyByIndex.getMother(), savedFamilyDto.getMother());
    }

    @Test
    void testDeleteFamilyByIndex() {
        int count = service.count();
        assertTrue(service.deleteFamilyByIndex(service.count() - 1));
        assertEquals(count-1, service.count());
    }

    @Test
    void testDeleteFamily() {
        Man man1 = new Man("John", "Doe", 1985, 56);
        Woman woman1 = new Woman("Emily", "Davis", 1992, 72);
        Human child1 = new Human("John", "Doe", 1985, 100);
        Human child2 = new Human("Alice", "Smith", 1990, 91);

        Family family = new Family(man1, woman1);
        family.addChild(child1);
        family.addChild(child2);

        int count = service.count();

        assertTrue(service.deleteFamily(family));
        assertEquals(count-1, service.count());
    }

    @Test
    void testBornChild() {
        FamilyDto createdFamily = service.createNewFamily(
                new ManDto("Vito", "Karleone", 1973),
                new WomanDto("Jane", "Karleone", 1975)
        );

        service.bornChild(createdFamily, "Michael", "Tresta");

        assertEquals(1 ,service.getFamilyById(service.count()-1).getChildren().size());
    }

    @Test
    void testAdoptChild() {
        Human newChildToAdopt = new Human("Bayonette", "Philips", 2000);
        Family familyById = service.getFamilyById(service.count() - 1);
        int familyCount = familyById.getFamilyCount();

        System.out.println(familyById);

        service.adoptChild(familyById, newChildToAdopt);
        assertEquals(++familyCount, familyById.getFamilyCount());
    }

    @Test
    void testDeleteAllChildrenOlderThen() {
        Family familyById = service.getFamilyById(2);
        assertFalse(familyById.getChildren().isEmpty());
        service.deleteAllChildrenOlderThen(0);
        assertTrue(familyById.getChildren().isEmpty());
    }

    @Test
    void testGetPetsWhenPetsDontExist(){
        assertTrue(service.getFamilyById(1).getPets().isEmpty());
    }

    @Test
    void testGetPetsWhenPetsExist() {
        Set<String> habits = new HashSet<>();
        habits.add("Eat");
        habits.add("Drink");
        habits.add("Sleep");

        Pet dog = new Dog("Rock", 5, 75, habits);

        Family familyById = service.getFamilyById(1);
        familyById.addPet(dog);

        assertEquals(1, familyById.getPets().size());
    }

    static void initializeDao() {
        Man man1 = new Man("John", "Doe", 1985, 56);
        Man man2 = new Man("Alex", "Smith", 1990, 91);
        Man man3 = new Man("Michael", "Johnson", 1975, 79);
        Man man4 = new Man("David", "Brown", 2000, 68);
        Man man5 = new Man("James", "Williams", 1988, 89);

        Woman woman1 = new Woman("Emily", "Davis", 1992, 72);
        Woman woman2 = new Woman("Sophia", "Johnson", 1986, 99);
        Woman woman3 = new Woman("Olivia", "Wilson", 1995, 69);
        Woman woman4 = new Woman("Ava", "Garcia", 1988, 84);
        Woman woman5 = new Woman("Isabella", "Martinez", 1990, 85);

        Human child1 = new Human("John", "Doe", 1985, 100);
        Human child2 = new Human("Alice", "Smith", 1990, 91);
        Human child3 = new Human("Michael", "Brown", 1978, 95);
        Human child4 = new Human("Emma", "Jones", 1995, 23);
        Human child5 = new Human("William", "Garcia", 1988, 59);
        Human child6 = new Human("Sophia", "Miller", 1992, 88);
        Human child7 = new Human("James", "Wilson", 1980, 92);
        Human child8 = new Human("Charlotte", "Davis", 1998, 85);
        Human child9 = new Human("Benjamin", "Rodriguez", 1983, 95);
        Human child10 = new Human("Lucas", "Martinez", 1991, 90);

        Family family = new Family(man1, woman1);
        family.addChild(child1);
        family.addChild(child2);

        Family family2 = new Family(man2, woman2);
        family2.addChild(child3);
        family2.addChild(child4);

        Family family3 = new Family(man3, woman3);
        family3.addChild(child5);
        family3.addChild(child6);

        Family family4 = new Family(man4, woman4);
        family4.addChild(child7);
        family4.addChild(child8);

        Family family5 = new Family(man5, woman5);
        family5.addChild(child9);

        Family family1 = dao.saveFamily(family);
        dao.saveFamily(family2);
        dao.saveFamily(family3);
        dao.saveFamily(family4);
        dao.saveFamily(family5);
    }
}
package az.edu.strangers.service;

import az.edu.strangers.controller.FamilyController;
import az.edu.strangers.dao.CollectionFamilyDao;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.dto.WomanDto;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Man;
import az.edu.strangers.entity.Woman;
import az.edu.strangers.exception.FamilyOverflowException;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApplication {
    private final FamilyDao familyDao = new CollectionFamilyDao();
    private final FamilyService familyService = new FamilyServiceImpl(familyDao);
    private final FamilyController familyController = new FamilyController(familyService);
    private final Scanner scanner = new Scanner(System.in);

    private final int familyCountLimit = 5;

    public void run() {
        boolean canLoop = true;
        while (canLoop) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        initializeDao();
                        System.out.println("Database has been successfully filled with test data.");
                        break;
                    case 2:
                        familyController.displayAllFamilies();
                        break;
                    case 3:
                        System.out.print("Enter the number: ");
                        familyController.getFamiliesBiggerThan(scanner.nextInt())
                                .forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("Enter the number: ");
                        familyController.getFamiliesLessThan(scanner.nextInt())
                                .forEach(System.out::println);
                        break;
                    case 5:
                        System.out.print("Enter the number: ");
                        System.out.println(familyController.countFamiliesWithMemberNumber(scanner.nextInt()));
                        break;
                    case 6:
                        createNewFamily();
                        break;
                    case 7:
                        deleteFamily();
                        break;
                    case 8:
                        checkSize();
                        altMenuOfEditFamily();
                        getBabyInformation();
                        break;
                    case 9:
                        System.out.print("Please enter age of majority: ");
                        int ageOfMajority = Integer.parseInt(scanner.nextLine());
                        familyController.deleteAllChildrenOlderThen(ageOfMajority);
                        break;
                    case 10:
                        canLoop = false;
                        System.out.println("Exiting..");
                        break;
                    default:
                        System.out.println("Wrong choice, try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred:" + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void getBabyInformation() {
        int choiceOfAltMenu = Integer.parseInt(scanner.nextLine());
        switch (choiceOfAltMenu) {
            case 1:
                System.out.print("Enter family iD: ");
                int familyID = Integer.parseInt(scanner.nextLine());

                System.out.println("Name for baby, if it's a boy: ");
                String boyName = scanner.nextLine();

                System.out.println("Name for baby, if it's a girl");
                String girlName = scanner.nextLine();

                Family familyByID = familyController.getFamilyById(familyID - 1);
                familyController.bornChild(familyByID, boyName, girlName);
                break;
            case 2:
                System.out.print("Enter family ID: ");
                int familyId = Integer.parseInt(scanner.nextLine());

                if (familyId > familyController.getAllFamilies().size() || familyId < 0)
                    throw new IllegalArgumentException("Given ID is above the count of family.Back to menu");
                Human child = null;
                try {
                    System.out.print("Enter child name: ");
                    String childName = scanner.nextLine();

                    System.out.print("Enter child's last name: ");
                    String childLastName = scanner.nextLine();

                    System.out.print("Enter child's birth year (YYYY): ");
                    int childBirthYear = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter child's birth month (1-12): ");
                    int childBirthMonth = Integer.parseInt(scanner.nextLine());
                    if (childBirthMonth < 1 || childBirthMonth > 12) {
                        throw new IllegalArgumentException("Month must be between 1 and 12.");
                    }

                    System.out.print("Enter child's birthday (1-31): ");
                    int childBirthDay = Integer.parseInt(scanner.nextLine());
                    if (childBirthDay < 1 || childBirthDay > 31) {
                        throw new IllegalArgumentException("Day must be between 1 and 31");
                    }

                    LocalDate childBirthDate = LocalDate.of(childBirthYear, childBirthMonth, childBirthDay);

                    System.out.print("Enter child's IQ: ");
                    int childIQ = Integer.parseInt(scanner.nextLine());

                    child = new Human(childName, childLastName, childBirthDate, childIQ);
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input.Reverting back to menu.");
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }
                familyController.adoptChild(familyController.getFamilyById(familyId - 1), child);
                break;
            case 3:
                System.out.println("Returning back to menu.");
                break;
            default:
        }
    }

    private void createNewFamily() {
        checkSize();

        String motherName, motherSurname, fatherName, fatherSurname;
        int motherBirthYear, motherBirthMonth, motherBirthDay, motherIQ;
        int fatherBirthYear, fatherBirthMonth, fatherBirthDay, fatherIQ;

        try {
            System.out.print("Enter mother's name: ");
            motherName = scanner.nextLine();

            System.out.print("Enter mother's last name: ");
            motherSurname = scanner.nextLine();

            System.out.print("Enter mother's birth year (YYYY): ");
            motherBirthYear = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter mother's birth month (1-12): ");
            motherBirthMonth = Integer.parseInt(scanner.nextLine());
            if (motherBirthMonth < 1 || motherBirthMonth > 12) {
                throw new IllegalArgumentException("Month must be between 1 and 12.");
            }

            System.out.print("Enter mother's birthday (1-31): ");
            motherBirthDay = Integer.parseInt(scanner.nextLine());
            if (motherBirthDay < 1 || motherBirthDay > 31) {
                throw new IllegalArgumentException("Day must be between 1 and 31.");
            }
            LocalDate motherBirthDate = LocalDate.of(motherBirthYear, motherBirthMonth, motherBirthDay);

            System.out.print("Enter mother's IQ: ");
            motherIQ = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter father's name: ");
            fatherName = scanner.nextLine();

            System.out.print("Enter father's last name: ");
            fatherSurname = scanner.nextLine();

            System.out.print("Enter father's birth year (YYYY): ");
            fatherBirthYear = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter father's birth month (1-12): ");
            fatherBirthMonth = Integer.parseInt(scanner.nextLine());
            if (fatherBirthMonth < 1 || fatherBirthMonth > 12) {
                throw new IllegalArgumentException("Month must be between 1 and 12.");
            }

            System.out.print("Enter father's birthday (1-31): ");
            fatherBirthDay = Integer.parseInt(scanner.nextLine());
            if (fatherBirthDay < 1 || fatherBirthDay > 31) {
                throw new IllegalArgumentException("Day must be between 1 and 31.");
            }
            LocalDate fatherBirthDate = LocalDate.of(fatherBirthYear, fatherBirthMonth, fatherBirthDay);

            System.out.print("Enter father's IQ: ");
            fatherIQ = Integer.parseInt(scanner.nextLine());

            ManDto father = new ManDto(fatherName, fatherSurname, fatherBirthDate, fatherIQ);
            WomanDto mother = new WomanDto(motherName, motherSurname, motherBirthDate, motherIQ);
            familyController.createNewFamily(father, mother);

            System.out.println("New family created successfully.");
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input.Reverting back to menu.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteFamily() {
        System.out.println("Enter the index of the family to be deleted:");
        int index = scanner.nextInt();
        try {
            familyController.deleteFamilyByIndex(index - 1);
            System.out.println("Family deleted.");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Please select available index.Reverting back to menu.");
        }
    }

    private void displayMenu() {
        System.out.print("""
                Make your choice:
                1. Fill with test data
                2. Show all families
                3. Show all families with number of people greater than specified number
                4. Show all families with number of people less than specified number
                5. Show number of families with number of members is specified number
                6. Create a new family
                7. Delete a family by index
                8. Edit a family by index
                9. Remove all children over the age of majority
                10. Exit
                """);
    }

    private void altMenuOfEditFamily() {
        System.out.print(
                """
                        1. Give birth to a baby
                        2. Adopt a child
                        3. Return to main menu
                        """
        );
    }

    void initializeDao() {
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

        Human child1 = new Human("John", "Doe", LocalDate.of(1985, 1, 1), 100);
        Human child2 = new Human("Alice", "Smith", LocalDate.of(1990, 1, 1), 91);
        Human child3 = new Human("Michael", "Brown", LocalDate.of(1978, 1, 1), 95);
        Human child4 = new Human("Emma", "Jones", LocalDate.of(1995, 1, 1), 23);
        Human child5 = new Human("William", "Garcia", LocalDate.of(1988, 1, 1), 59);
        Human child6 = new Human("Sophia", "Miller", LocalDate.of(1992, 1, 1), 88);
        Human child7 = new Human("James", "Wilson", LocalDate.of(1980, 1, 1), 92);
        Human child8 = new Human("Charlotte", "Davis", LocalDate.of(1998, 1, 1), 85);
        Human child9 = new Human("Benjamin", "Rodriguez", LocalDate.of(1983, 1, 1), 95);
        Human child10 = new Human("Lucas", "Martinez", LocalDate.of(1991, 1, 1), 90);

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

        familyDao.saveFamily(family);
        familyDao.saveFamily(family2);
        familyDao.saveFamily(family3);
        familyDao.saveFamily(family4);
        familyDao.saveFamily(family5);
    }

    public void checkSize() {
        int size = familyController.getAllFamilies().size();
        if (size >= familyCountLimit) throw new FamilyOverflowException("Family count has reached its limit.");
    }
}

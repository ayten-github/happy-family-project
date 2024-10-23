package az.edu.strangers.service;

import az.edu.strangers.controller.FamilyController;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dao.FileFamilyDao;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.entity.Man;
import az.edu.strangers.entity.Woman;
import az.edu.strangers.exception.FamilyOverflowException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApplication implements Serializable {
    private final FileFamilyDao dao =
//            new CollectionFamilyDao();
            new FileFamilyDao(fileName);
    private final FamilyService familyService = new FamilyServiceImpl(dao);
    private final FamilyController familyController = new FamilyController(familyService);
    private final Scanner scanner = new Scanner(System.in);
    public static final String fileName = "family.dat";
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
                        loadDataFromFile();
                        break;
                    case 2:
                        familyController.displayAllFamilies();
                        break;
                    case 3:
                        System.out.print("Enter the number: ");
                        familyController.displayFamiliesBiggerThan(scanner.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter the number: ");
                        familyController.displayFamiliesLessThan(scanner.nextInt());
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
                        loadData();
                        break;
                    case 11:
                        canLoop = false;
                        System.out.println("Exiting..");
                        break;
                    default:
                        System.out.println("Wrong choice, try again.");
                        break;
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
                System.out.print("Enter family ID: ");
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
                System.out.println("Wrong number. Please select available number next time. Reverting back to menu");
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

            Man father = new Man(fatherName, fatherSurname, fatherBirthDate, fatherIQ);
            Woman mother = new Woman(motherName, motherSurname, motherBirthDate, motherIQ);
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
            System.out.println("Please select available index. Reverting back to menu.");
        }
    }

    private void displayMenu() {
        System.out.print("""
                Make your choice:
                1. Load from file
                2. Show all families
                3. Show all families with number of people greater than specified number
                4. Show all families with number of people less than specified number
                5. Show number of families with number of members is specified number
                6. Create a new family
                7. Delete a family by index
                8. Edit a family by index
                9. Remove all children over the age of majority
                10. Load to file
                11. Exit 
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

    public void checkSize() {
        int size = familyController.getAllFamilies().size();
        if (size >= familyCountLimit) throw new FamilyOverflowException("Family count has reached its limit.");
    }

    public void loadDataFromFile() throws FileNotFoundException {
        if (dao instanceof FileFamilyDao) {
            ((FileFamilyDao) dao).loadFromFile();
            System.out.println("loaded from file");
        } else System.out.println("Working with files is not available.");
    }

    public void loadData() throws FileNotFoundException {
        familyController.loadData(familyController.getAllFamilies());
        System.out.println("Data loaded to file.");
    }
}

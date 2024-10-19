package az.edu.strangers.service;

import az.edu.strangers.controller.FamilyController;
import az.edu.strangers.dao.CollectionFamilyDao;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.service.FamilyService;
import az.edu.strangers.service.FamilyServiceImpl;

import java.util.Scanner;

public class ConsoleApplication {
    private final FamilyDao familyDao=new CollectionFamilyDao();
    private final FamilyService familyService = new FamilyServiceImpl(familyDao);
    private final FamilyController familyController=new FamilyController(familyService);
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        familyController.getAllFamilies();
                        break;
                    case 2:
                        familyService.displayAllFamilies();
                        break;
                    case 3:
                        familyController.getFamiliesBiggerThan(scanner.nextInt());
                        break;
                    case 4:
                        familyController.getFamiliesLessThan(scanner.nextInt());
                        break;
                    case 5:
                        familyController.countFamiliesWithMemberNumber(scanner.nextInt());
                        break;
                    case 6:
                        createNewFamily();
                        break;
                    case 7:
                        familyController.deleteFamilyByIndex(scanner.nextInt());
                        break;
//                    case 8:
//
                    case 9:
                        familyController.deleteAllChildrenOlderThen(scanner.nextInt());
                        break;
                    case 0:
                        System.exit(0);
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

    private void displayMenu() {
        System.out.println("Make your choice:");
        System.out.println("Fill with test data");
        System.out.println("Show all families");
        System.out.println("Create new family");
        System.out.println("Delete family");
        System.out.println("Exit");
    }

    private void createNewFamily() {
        System.out.print("Enter mother's name: ");
        String motherName = scanner.nextLine();

        System.out.print("Enter mother's last name: ");
        String motherSurname = scanner.nextLine();

        System.out.print("Enter mother's birth year (YYYY): ");
        int motherBirthYear = scanner.nextInt();

        System.out.print("Enter mother's birth month (1-12): ");
        int motherBirthMonth = scanner.nextInt();

        System.out.print("Enter mother's birthday (1-31): ");
        int motherBirthDay = scanner.nextInt();

        System.out.print("Enter mother's IQ: ");
        int motherIq = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Collect father's information
        System.out.print("Enter father's name: ");
        String fatherName = scanner.nextLine();
        System.out.print("Enter father's last name: ");
        String fatherSurname = scanner.nextLine();

        System.out.print("Enter father's birth year (YYYY): ");
        int fatherBirthYear = scanner.nextInt();

        System.out.print("Enter father's birth month (1-12): ");
        int fatherBirthMonth = scanner.nextInt();

        System.out.print("Enter father's birthday (1-31): ");
        int fatherBirthDay = scanner.nextInt();

        System.out.print("Enter father's IQ: ");
        int fatherIq = scanner.nextInt();


        System.out.println("New family created successfully.");
    }
}

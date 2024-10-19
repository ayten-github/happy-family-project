package az.edu.strangers.service;

import az.edu.strangers.controller.FamilyController;
import az.edu.strangers.dao.CollectionFamilyDao;
import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.dto.ManDto;
import az.edu.strangers.entity.Family;
import az.edu.strangers.entity.Human;
import az.edu.strangers.service.FamilyService;
import az.edu.strangers.service.FamilyServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

public class ConsoleApplication {
    private final FamilyDao familyDao = new CollectionFamilyDao();
    private final FamilyService familyService = new FamilyServiceImpl(familyDao);
    private final FamilyController familyController = new FamilyController(familyService);
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
                        familyController.displayAllFamilies();
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
                        deleteFamily();
                        break;
                    case 8:
                        createNewFamily();
                        break;
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
        String motherName, motherSurname, fatherName, fatherSurname;
        int motherBirthYear, motherBirthMonth, motherBirthDay, motherIq;
        int fatherBirthYear, fatherBirthMonth, fatherBirthDay, fatherIq;

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

            System.out.print("Enter mother's IQ: ");
            motherIq = Integer.parseInt(scanner.nextLine());

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
//yeni ailenin add olunmasi
            System.out.println("New family created successfully.");

        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void deleteFamily() {
        System.out.println("Enter the index of the family to be deleted:");
        int index = scanner.nextInt();
        familyController.deleteFamilyByIndex(index - 1);
        System.out.println("Family deleted.");
    }
}

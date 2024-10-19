package az.edu.strangers.service;

import az.edu.strangers.dao.FamilyDao;
import az.edu.strangers.service.FamilyService;
import az.edu.strangers.service.FamilyServiceImpl;

import java.util.Scanner;

public class ConsoleApplication {
    private FamilyService familyService = new FamilyServiceImpl(FamilyDao);
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        familyService.getAllFamilies();
                        break;
                    case 2:
                        familyService.displayAllFamilies();
                        break;
                    case 6:
                        createNewFamily();
                        break;
                    case 7:
                        deleteFamily();
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
    }

    private void deleteFamily() {
        System.out.println("Enter the index of the family to be deleted:");
        int index = scanner.nextInt();
        familyService.deleteFamilyByIndex(index - 1);
        System.out.println("Family deleted.");
    }
}

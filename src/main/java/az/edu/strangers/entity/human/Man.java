package az.edu.strangers.entity.human;

import az.edu.strangers.entity.pet.Pet;

import java.time.LocalDate;

public final class Man extends Human {
    public Man(String name, String surname, long year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, long year, Integer IQ) {
        super(name, surname, LocalDate.ofEpochDay(year), IQ);
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPets() != null && !getFamily().getPets().isEmpty()) {
            for (Pet pet : getFamily().getPets()) {
                System.out.printf("Hello my friend %s \n", pet.getNickName());
            }
        } else {
            System.out.println("Family doesn't have a pet");
        }
    }

    public void repairCar() {
        System.out.println("A man's car is cleaner than a woman's house.");
    }
}

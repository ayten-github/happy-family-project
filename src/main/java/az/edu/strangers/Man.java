package az.edu.strangers;

public final class Man extends Human {
    public Man(String name, String surname, Integer year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, Integer year, Integer IQ) {
        super(name, surname, year, IQ);
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

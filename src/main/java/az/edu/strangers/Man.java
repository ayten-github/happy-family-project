package az.edu.strangers;

public final class Man extends Human {
    public Man(String name, String surname, Integer year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, Integer year, Integer IQ) {
        super(name, surname, year, IQ);
    }

    public void repairCar() {
        System.out.println("A man's car is cleaner than a woman's house.");
    }
}

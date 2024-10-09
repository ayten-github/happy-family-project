package az.edu.strangers;

public class Human {
    public String name;
    public String surname;
    public Integer year;
    public Integer IQ;
    public Pet pet;
    public Human mother;
    public Human father;
    public String[][] schedule;

    public Human(){}

    public Human(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Human(String name, String surname, Integer year, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.mother = mother;
        this.father = father;
    }

    public Human(String name, String surname, Integer year, Pet pet, Human father, Human mother, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.pet = pet;
        this.father = father;
        this.mother = mother;
        this.schedule = schedule;
    }

    public void greetPet() {
        System.out.printf("Hello, %s /n",pet.nickname );
    }

    public void describePet() {
        System.out.printf("I have an %sis %dyears old, he is%d%n", pet.species, pet.age, pet.tricklevel);
    }

    public String toString() {
        return String.format("Human{name = %s," + "surname = %s," + "year = %d," + "iq= %d," +
                "mother = %s%s,%s",
                name, surname, year,
                IQ, mother.name, mother.surname, pet.toString());
    }
}

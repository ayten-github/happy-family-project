package az.edu.strangers;

public class Human {
    private String name;
    private String surname;
    private Integer year;
    private Integer IQ;
    private Pet pet;
    private String[][] schedule;
    private Family family;

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Human() {
    }

    public Human(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Human(String name, String surname, Integer year, Pet pet, Human father, Human mother, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.pet = pet;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getIQ() {
        return IQ;
    }

    public void setIQ(Integer IQ) {
        this.IQ = IQ;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public void greetPet() {
        System.out.printf("Hello, %s /n", pet.getNickName());
    }

    public void describePet() {
        System.out.printf("I have an %s is %d years old, he is %d %n", pet.getSpecies(), pet.getAge(), pet.getTrickLevel());
    }


    public String toString() {
        return String.format("{ Human: name = %s, surname = %s, year = %d, IQ= %d,",
                getName(), getSurname(), getYear(), pet.toString());
    }


}

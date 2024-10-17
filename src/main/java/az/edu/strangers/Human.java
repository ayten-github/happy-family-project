package az.edu.strangers;

import az.edu.strangers.dao.Family;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Human {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Integer IQ;
    private Map<DayOfWeek, String> schedule;
    private Family family;
//    private FamilyDto familyDto;

    public Human() {
    }

    public Human(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
    public Human(String name, String surname, String birthDateStringForAoptedChild, Integer IQ) {
        this.name = name;
        this.surname = surname;
        this.IQ = IQ;
        this.birthDate = LocalDate.parse(birthDateStringForAoptedChild, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Human(String name, String surname, LocalDate birthDate, Integer IQ, Map<DayOfWeek, String> schedule, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.IQ = IQ;
        this.schedule = schedule;
        this.family = family;
    }

    public Human(String name, String surname, LocalDate birthday, Integer IQ) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthday;
        this.IQ = IQ;
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

    public LocalDate getYear() {
        return birthDate;
    }

    public void setYear(LocalDate birthdate) {
        this.birthDate = birthdate;
    }

    public Integer getIQ() {
        return IQ;
    }

    public void setIQ(Integer IQ) {
        this.IQ = IQ;
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void greetPet() {
        if (family != null && family.getPets() != null && !family.getPets().isEmpty()) {
            for (Pet pet : family.getPets()) {
                System.out.printf("Hello  %s \n", pet.getNickName());
            }
        } else {
            System.out.println("Family doesn't have a pet");
        }
    }
    public String describeAge() {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        return String.format("Age: %d years, %d months, and %d days.", age.getYears(), age.getMonths(), age.getDays());
    }

    public void describePet() {
        if (family != null && family.getPets() != null && !family.getPets().isEmpty()) {
            for (Pet pet : family.getPets()) {
                String slyness = pet.getTrickLevel() > 50 ? "very sly" : "almost not sly";
                System.out.printf(
                        "I have a %s, it is %d years old, and it is %s%n",
                        pet.getSpecies(), pet.getAge(), slyness
                );
            }
        }
    }

    public String toStringV2() {
        return "Human{name='%s', surname='%s', birthdate=%s, iq=%s, schedule=%s}".formatted(name, surname, birthDate, IQ, schedule);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedBirthDate = birthDate.format(formatter);
        return "Human{name='%s', surname='%s', birthdate=%s}".formatted(name, surname, formattedBirthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(birthDate, human.birthDate) && Objects.equals(IQ, human.IQ) && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, IQ, schedule);
    }
}

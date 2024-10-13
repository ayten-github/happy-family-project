package az.edu.strangers;
import az.edu.strangers.dao.Family;
import az.edu.strangers.dto.FamilyDto;

import java.util.Map;
import java.util.Objects;

public class Human {

    private String name;
    private String surname;
    private Integer year;
    private Integer IQ;
    private Map<DayOfWeek, String> schedule;
    private Family family;
    private FamilyDto familyDto;

    public Human() {
    }

    public Human(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Human(String name, String surname, Integer year, Integer IQ, Map<DayOfWeek, String> schedule, Family family) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.IQ = IQ;
        this.schedule = schedule;
        this.family = family;
    }

    public Human(String name, String surname, Integer year, Integer IQ) {
        this.name = name;
        this.surname = surname;
        this.year = year;
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

    public FamilyDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }

    public void greetPet() {
        if (family != null && family.getPet() != null) {
            System.out.printf("Hello  %s \n", family.getPet().getNickName());
        } else {
            System.out.println("Family doesn't have a pet");
        }
    }

    public void describePet() {
        if (family != null && family.getPet() != null) {
            String slyness = family.getPet().getTrickLevel() > 50 ? "very sly" : "almost not sly";
            System.out.printf("I have a %s, it is %d years old, and it is %s%n", family.getPet().getSpecies(), family.getPet().getAge(), slyness);
        }
    }

    public String toStringV2() {
        return "Human{name='%s', surname='%s', year=%d, iq=%s, schedule=%s}".formatted(name, surname, year, IQ, schedule);
    }

    public String toString() {
        return "Human{name='%s', surname='%s', year=%d}".formatted(name, surname, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(year, human.year) && Objects.equals(IQ, human.IQ) && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, IQ, schedule);

        return result;
    }
}

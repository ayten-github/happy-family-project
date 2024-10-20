package az.edu.strangers.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Human {

    private String name;
    private String surname;
    private long birthDate;
    private Integer IQ;
    private Map<DayOfWeek, String> schedule;
    private Family family;
//    private FamilyDto familyDto;

    public Human() {
    }

    public Human(String name, String surname, long birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
    public Human(String name, String surname, LocalDate birthday, Integer IQ) {
        this.name = name;
        this.surname = surname;
        this.birthDate = convertLocalDateToMillis(birthday);
        this.IQ = IQ;
    }

    public Human(String name, String surname, long birthDate, Integer IQ, Map<DayOfWeek, String> schedule, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.IQ = IQ;
        this.schedule = schedule;
        this.family = family;
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

    public long getYear() {
        return birthDate;
    }

    public void setYear(long birthdate) {
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

    public long convertLocalDateToMillis(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
        LocalDate birthDateInLocal= convertMillisDate(birthDate);
        long year= ChronoUnit.YEARS.between(birthDateInLocal,currentDate);
        long month=ChronoUnit.MONTHS.between(birthDateInLocal.plusYears(year),currentDate);
        long day=ChronoUnit.DAYS.between(birthDateInLocal.plusYears(year).plusMonths(month),currentDate);
        return String.format("%s was born %d year, %d month, %d day", name, year, month, day);

    }

    public LocalDate convertMillisDate(long milliInDate){
        return new Date(milliInDate).toInstant().
                atZone(ZoneId.systemDefault()).
                toLocalDate();
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

    public String prettyFormat() {
        return String.format("name='%s', surname='%s', birthDate='%s', IQ=%d, schedule='%s',family=%s",
                name, surname, birthDate, IQ, schedule,family);
    }

    public String toStringV2() {
        return "Human{name='%s', surname='%s', birthdate=%s, iq=%s, schedule=%s}".formatted(name, surname, birthDate, IQ, schedule);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedBirthDate = convertMillisDate(birthDate).format(formatter);
        return "{ name='%s', surname='%s', birthdate='%s', schedule='%s' }"
                .formatted(name, surname, formattedBirthDate,schedule);
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

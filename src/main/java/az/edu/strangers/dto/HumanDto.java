package az.edu.strangers.dto;

import az.edu.strangers.entity.DayOfWeek;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class HumanDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private long year;
    private LocalDate birthDate;
    private Integer IQ;
    private Map<DayOfWeek, String> schedule;
    private FamilyDto familyDto;

    public HumanDto() {
    }

    public HumanDto(String name, String surname, long year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public HumanDto(String name, String surname, LocalDate birthDate, Integer IQ) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.IQ = IQ;
    }

    public HumanDto(String name, String surname, long year, Integer IQ, Map<DayOfWeek, String> schedule, FamilyDto familyDto) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.IQ = IQ;
        this.schedule = schedule;
        this.familyDto = familyDto;
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
        return year;
    }

    public void setYear(long year) {
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

    public FamilyDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String toString() {
        return "Human{name='%s', surname='%s', year=%d, iq=%s, schedule=%s}".formatted(name, surname, year, IQ, schedule);
    }
}

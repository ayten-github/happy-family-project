package az.edu.strangers.dto;

import java.time.LocalDate;

public class WomanDto extends HumanDto{

    public WomanDto(String name, String surname, long year) {
        super(name, surname, year);
    }

    public WomanDto(String name, String surname, LocalDate birthDate, Integer IQ) {
        super(name, surname, birthDate, IQ);
    }
}

package az.edu.strangers.dto;

import java.time.LocalDate;

public class ManDto extends HumanDto{

    public ManDto(String name, String surname, long year) {
        super(name, surname, year);
    }

    public ManDto(String name, String surname, LocalDate birthDate, Integer IQ) {
        super(name, surname, birthDate, IQ);
    }

}

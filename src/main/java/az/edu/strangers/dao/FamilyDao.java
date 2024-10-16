package az.edu.strangers.dao;

import az.edu.strangers.dto.FamilyDto;

import java.util.List;
import java.util.Optional;

public interface FamilyDao {

    List<Family> getAllFamilies();

    Family getFamilyByIndex(int id);

    boolean deleteFamily(int id);

    boolean deleteFamily(Family family);

    Family saveFamily(Family family);

    Family updateFamily(FamilyDto familyDto);
}

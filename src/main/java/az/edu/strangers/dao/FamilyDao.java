package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyDao {

    List<Family> getAllFamilies();

    Optional<Family> getFamilyByIndex(int id);

    boolean deleteFamily(int id);

    boolean deleteFamily(Family family);

    void saveFamily(Family family);

    void loadData(List<Family> families);
}

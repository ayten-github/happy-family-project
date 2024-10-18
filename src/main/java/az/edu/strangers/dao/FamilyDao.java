package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.util.List;

public interface FamilyDao {

    List<Family> getAllFamilies();

    Family getFamilyByIndex(int id);

    boolean deleteFamily(int id);

    boolean deleteFamily(Family family);

    Family saveFamily(Family family);
}

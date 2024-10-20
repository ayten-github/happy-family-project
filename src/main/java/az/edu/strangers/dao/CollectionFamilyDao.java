package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao, Serializable {

    private final List<Family> FAMILY_LIST = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return FAMILY_LIST;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        return index >= 0 && index < FAMILY_LIST.size() ? FAMILY_LIST.get(index) : null;
    }

    @Override
    public boolean deleteFamily(int id) {
        return FAMILY_LIST.remove(id) != null;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return FAMILY_LIST.remove(family);
    }

    @Override
    public Family saveFamily(Family familyEntity) {
        if (!FAMILY_LIST.contains(familyEntity)) {
            FAMILY_LIST.add(familyEntity);
        } else {
            System.out.println("Family is already contains!");
        }
        return familyEntity;
    }


}

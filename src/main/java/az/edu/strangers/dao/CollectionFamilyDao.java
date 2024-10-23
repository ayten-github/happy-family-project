package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {
    private final List<Family> families = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        for (int i = 0; i < families.size(); i++) {
            Family existingFamily = families.get(i);

            if (existingFamily.getFather().equals(family.getFather())){
                int index = families.indexOf(family);
                families.set(index, family);
                return;
            }
        }

        families.add(family);
    }
}

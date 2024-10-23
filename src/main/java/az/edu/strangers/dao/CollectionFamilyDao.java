package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionFamilyDao implements FamilyDao {
    private List<Family> families = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Optional<Family> getFamilyByIndex(int index) {
        return (index >= 0 && index < families.size()) ? Optional.of(families.get(index)) : Optional.empty();
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size() && !families.isEmpty()) {
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

    @Override
    public void loadData(List<Family> families) {
        this.families = families;
    }
}

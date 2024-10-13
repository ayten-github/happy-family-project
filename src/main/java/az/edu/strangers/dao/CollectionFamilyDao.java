package az.edu.strangers.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionFamilyDao implements FamilyDao<Family>{

    private static final List<Family> FAMILY_LIST = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(FAMILY_LIST);
    }

    @Override
    public Optional<Family> getFamilyByIndex(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFamily(long id) {
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return false;
    }

    @Override
    public Family saveFamily(Family familyEntity) {
        FAMILY_LIST.add(familyEntity);
        return familyEntity;
    }
}

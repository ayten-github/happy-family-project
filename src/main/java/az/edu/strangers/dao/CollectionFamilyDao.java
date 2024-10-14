package az.edu.strangers.dao;

import az.edu.strangers.dto.FamilyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionFamilyDao implements FamilyDao{

    private static final List<Family> FAMILY_LIST = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return FAMILY_LIST;
    }

    @Override
    public Optional<Family> getFamilyByIndex(long index) {
        if (index < 0 || index >= FAMILY_LIST.size()) {
            return Optional.empty();
        }
        Family findedFamily = FAMILY_LIST.get((int) index);
        return Optional.of(findedFamily);
    }

    @Override
    public boolean deleteFamily(Long id) {
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return false;
    }

    @Override
    public Family saveFamily(Family familyEntity) {
        if (!FAMILY_LIST.contains(familyEntity)) {
            FAMILY_LIST.add(familyEntity);
        }
        return familyEntity;
    }

    @Override
    public Family updateFamily(FamilyDto familyDto, Long index) {
        Optional<Family> optionalFamily = getFamilyByIndex(index);

        if (optionalFamily.isPresent()){
            Family newFamily = optionalFamily.get();

            newFamily.setFather(familyDto.getFather());
            newFamily.setMother(familyDto.getMother());
            newFamily.setPet(familyDto.getPet());
            newFamily.setChildren(familyDto.getChildren());

            return saveFamily(newFamily);
        }
        return null;
    }
}

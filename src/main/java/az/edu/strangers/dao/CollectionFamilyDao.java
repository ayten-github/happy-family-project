package az.edu.strangers.dao;

import az.edu.strangers.dto.FamilyDto;

import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private static final List<Family> FAMILY_LIST = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return FAMILY_LIST;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        return FAMILY_LIST.get(index);
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
        }
        return familyEntity;
    }

    @Override
    public Family updateFamily(FamilyDto familyDto) {
        for (Family family : FAMILY_LIST) {
            if (family.getFather().equals(familyDto.getFather()) &&
                    family.getMother().equals(familyDto.getMother())) {
                family.setFather(familyDto.getFather());
                family.setMother(familyDto.getMother());
                family.setChildren(familyDto.getChildren());
                family.setPets(familyDto.getPets());
                return family;
            }
        }

        Family updatedFamily = new Family(familyDto.getFather(), familyDto.getMother());
        updatedFamily.setPets(familyDto.getPets());
        updatedFamily.setChildren(familyDto.getChildren());

        return saveFamily(updatedFamily);
    }
}

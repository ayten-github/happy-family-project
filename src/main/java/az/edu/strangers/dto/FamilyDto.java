package az.edu.strangers.dto;

import az.edu.strangers.Human;
import az.edu.strangers.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FamilyDto {

    private Human father;
    private Human mother;
    private Pet pet;
    private List<Human> children;

    public FamilyDto(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new ArrayList<>();
//        mother.setFamily(this);
//        father.setFamily(this);
    }

    @Override
    public String toString() {
        return "FamilyDto{father=%s, mother=%s, pet=%s, children=%s}"
                .formatted(father, mother, pet, children);
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }
}

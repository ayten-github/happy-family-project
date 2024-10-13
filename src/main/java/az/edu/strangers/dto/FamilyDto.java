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
        mother.setFamilyDto(this);
        father.setFamilyDto(this);
    }

    @Override
    public String toString() {
        return "FamilyDto{" +
                "father=" + father +
                ", mother=" + mother +
                ", pet=" + pet +
                ", children=" + children +
                '}';
    }
}

package az.edu.strangers;

import java.util.Arrays;

public class Family {
    private Human father;
    private Human mother;
    private Pet pet;
    private Human[] children;
    private Integer childrenCount;

    static {
        System.out.println("Class Family is being loaded.");
    }

    {
        System.out.println("New Family object is created.");
    }


    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new Human[1];
        childrenCount = 0;
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

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public void addChild(Human child) {
        this.children[childrenCount] = child;
        child.setFamily(this);
        childrenCount++;
        children = Arrays.copyOf(children, children.length + 1);

    }

    public boolean deleteChild(int index) {
        if (index >= 0 && index < childrenCount) {
            Human child = children[index];
            child.setFamily(null);
            for (int i = index; i < childrenCount - 1; i++) {
                children[i] = children[i + 1];
            }
            children[childrenCount - 1] = null;
            childrenCount--;
            return true;
        }
        return false;
    }

    public int getFamilyCount() {
        return 2 + childrenCount;
    }

    @Override
    public String toString() {
        return String.format("{ Family: father=%s, mother=%s, pet=%s, children=%s, childrenCount=%d",
                father != null ? getFather().toString() : "null",
                mother != null ? getMother().toString() : "null",
                pet != null ? getPet().toString() : "null",
                Arrays.toString(children),
                childrenCount != null ? childrenCount : 0);
    }

}

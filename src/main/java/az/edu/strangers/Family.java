package az.edu.strangers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Family implements HumanCreator {

    private Human father;
    private Human mother;
    private Pet pet;
    private Human[] children;
    private Integer childrenCount;

    private Random random;

    static {
        System.out.println("Class Family is being loaded.");
    }

    {
        System.out.println("New Family object is created.");
    }


    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new Human[0];
        childrenCount = 0;
        mother.setFamily(this);
        father.setFamily(this);
        this.random = new Random();
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
        children = Arrays.copyOf(children, children.length + 1);
        this.children[childrenCount] = child;
        child.setFamily(this);
        childrenCount++;

    }


    public boolean deleteChild(int index) {
        if (index >= 0 && index < children.length) {
            Human[] newChildren = new Human[children.length - 1];
            for (int i = 0, k = 0; i < children.length; i++) {
                if (i == index) {
                    continue;
                }
                newChildren[k++] = children[i];
            }
            children[index].setFamily(null);
            children = newChildren;
            return true;
        }
        return false;
    }

    public boolean deleteChild(Human child) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                children[i] = null;
                children = Arrays.copyOf(children, childrenCount - 1);
                return true;
            }
        }
        return false;
    }

    public int getFamilyCount() {
        return 2 + childrenCount;
    }

    @Override
    public Human bornChild() {
        String[] boyNames = {"Alex", "Jord", "Mike"};
        String[] girlNames = {"Alina", "Julia", "Mia"};

        boolean isMan = random.nextBoolean();

        Integer childIQ = (getFather().getIQ() + getMother().getIQ()) / 2;

        String name;
        if (isMan) {
            name = boyNames[random.nextInt(boyNames.length)];
            Man man = new Man(name, getFather().getSurname(), LocalDate.now().getYear(), childIQ);
            this.addChild(man);
            return man;

        } else {
            name = girlNames[random.nextInt(boyNames.length)];
            Woman woman = new Woman(name, getFather().getSurname(), LocalDate.now().getYear(), childIQ);
            this.addChild(woman);
            return woman;

        }

    }

    @Override
    public String toString() {
        return String.format("Family{mother=%s, father=%s, children=%s, pet=%s", father != null ? "%s %s".formatted(father.getName(), father.getSurname()) : "null", mother != null ? "%s %s".formatted(mother.getName(), mother.getSurname()) : "null", Arrays.toString(children), pet != null ? getPet().toString() : "null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Arrays.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pet);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }


}

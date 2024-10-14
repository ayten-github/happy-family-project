package az.edu.strangers.dao;

import az.edu.strangers.*;

import java.time.LocalDate;
import java.util.*;

public class Family implements HumanCreator {

    private Human father;
    private Human mother;
    private Pet pet;
    private List<Human> children;

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
        this.children = new ArrayList<>();
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

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);
    }

    public Optional<Human> deleteChild(int index) {
        if (index >= children.size() || index < 0) return Optional.empty();

        Human removedChild = children.remove(index);
        removedChild.setFamily(null);

        return Optional.of(removedChild);
    }

    public boolean deleteChild(Human child) {
        if (!children.contains(child)) return false;

        Human removedChild = children.get(children.indexOf(child));
        children.remove(child);
        removedChild.setFamily(null);
        return true;
    }

    public int getFamilyCount() {
        return 2 + children.size();
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
        return String.format("Family{mother=%s, father=%s, children=%s, pet=%s}",
                mother != null ? "%s %s".formatted(mother.getName(), mother.getSurname()) : "null",
                father != null ? "%s %s".formatted(father.getName(), father.getSurname()) : "null",
                children, pet != null ? getPet().toString() : "null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Objects.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pet, children);
        return result;
    }
}

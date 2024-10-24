package az.edu.strangers.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Family implements HumanCreator, Serializable {

    private static final long serialVersionUID = 1L;
    private Human father;
    private Human mother;
    private Set<Pet> pets;
    private List<Human> children;

    private final Random random = new Random();

    static {
        System.out.println("Class Family is being loaded.");
    }
    public String prettyFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("family:\n");
        sb.append("\tmother: ").append(mother.prettyFormat()).append(",\n");
        sb.append("\tfather: ").append(father.prettyFormat()).append(",\n");

        sb.append("\tchildren: \n");
        for (Human child : children) {
            sb.append("\t\t").append(child.prettyFormat()).append("\n");
        }

        sb.append("\tpets: ").append(pets.stream().map(Pet::prettyFormat).toList()).append("\n");

        return sb.toString();
    }
    {
        System.out.println("New Family object is created.");
    }


    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
        mother.setFamily(this);
        father.setFamily(this);
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

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
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

    public void addPet(Pet pet) {
        pets.add(pet);
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
    public Human bornChild(String masculineName, String femineName) {
        boolean isMan = random.nextBoolean();

        Integer childIQ = (getFather().getIQ() + getMother().getIQ()) / 2;

        Human human = isMan ? new Man(masculineName, getFather().getSurname(), LocalDate.now(), childIQ) :
                            new Woman(femineName, getFather().getSurname(), LocalDate.now(), childIQ);
        this.addChild(human);

        return human;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) &&
                Objects.equals(father, family.father) &&
                Objects.equals(children, family.children) &&
                Objects.equals(pets, family.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, pets, children);
    }

    @Override
    public String toString() {
        return "Family: \n\t\tfather: %s, \n\t\tmother: %s,\n\t\tchildren=%s, \n\t\tpets: %s"
                .formatted(father, mother,children,pets);
    }
}

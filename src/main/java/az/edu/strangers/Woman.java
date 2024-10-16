package az.edu.strangers;

public final class Woman extends Human {
    public Woman(String name, String surname, Integer year) {
        super(name, surname, year);
    }

    public Woman(String name, String surname, Integer year, Integer IQ) {
        super(name, surname, year, IQ);
    }

    public void makeUp() {
        System.out.println("For women, grooming is their way of life.");
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPets() != null && !getFamily().getPets().isEmpty()) {
            for (Pet pet : getFamily().getPets()) {
                System.out.printf("Hello beatiful friend %s! How Are you? \n", pet.getNickName());
            }
        } else {
            System.out.println("Family doesn't have a pet");
        }
    }

}

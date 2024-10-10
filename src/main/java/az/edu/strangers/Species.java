package az.edu.strangers;

public enum Species {
    DOG("Dog", 4, true, false),
    CAT("Cat", 4, true, false),
    BIRD("Bird", 2, false, true),
    FISH("Fish", 0, false, false),
    UNKNOWN("Unknown", 0, false, false);
    private final String speciesName;
    private final int numberOfLegs;
    private final boolean hasFur;
    private final boolean canFly;

    Species(String speciesName, int numberOfLegs, boolean hasFur, boolean canFly) {
        this.speciesName = speciesName;
        this.numberOfLegs = numberOfLegs;
        this.hasFur = hasFur;
        this.canFly = canFly;
    }

    public static Species getSpecies(String speciesName) {
        Species[] species = Species.values();
        for (int i = 0; i < species.length; i++) {
            if (species[i].getSpeciesName().equals(speciesName)) {
                return species[i];
            }
        }
        return UNKNOWN;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean hasFur() {
        return hasFur;
    }

    public boolean canFly() {
        return canFly;
    }
}

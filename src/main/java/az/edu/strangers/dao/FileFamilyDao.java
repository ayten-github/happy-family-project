package az.edu.strangers.dao;
import az.edu.strangers.entity.Family;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFamilyDao implements FamilyDao {
    private final String FILE_PATH;

    public FileFamilyDao(String filePath) {
        this.FILE_PATH = filePath;
    }
    private List<Family> families = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(families);  // Return a copy of the families list
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index < 0 || index >= families.size()) {
            throw new IndexOutOfBoundsException("Invalid family index.");
        }
        return families.get(index);
    }

    @Override
    public void saveFamily(Family family) {
        if (!families.contains(family)) {
            families.add(family);
        }
        saveToFile();
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index < 0 || index >= families.size()) {
            throw new IndexOutOfBoundsException("Invalid family index.");
        }
        families.remove(index);
        saveToFile();

        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        boolean removed = families.remove(family);

        if (!removed) {
            throw new IllegalArgumentException("Family not found.");
        }
        saveToFile();
        return true;
    }

    public void loadData(List<Family> families) {
        this.families = new ArrayList<>(families);
        saveToFile();  // Save the loaded data to the file system
    }

    public List<Family> loadFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) throw new FileNotFoundException("File does not exists, or failed to find.");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            families = (List<Family>) ois.readObject();
            System.out.println("Families loaded successfully from the file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading families from the file: " + e.getMessage());
            families = new ArrayList<>();  // Reset to empty list if loading fails
        }
        return families;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(families);
            System.out.println("Families saved successfully to the file.");
        } catch (IOException e) {
            System.err.println("Error saving families to the file: " + e.getMessage());
        }
    }
}
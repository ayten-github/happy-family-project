package az.edu.strangers.dao;

import az.edu.strangers.entity.Family;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileFamilyDao implements FamilyDao {

    private final String FILE_PATH;
    private List<Family> families = new ArrayList<>();

    public FileFamilyDao(String filePath) {
        this.FILE_PATH = filePath;
    }

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(families);  // Return a copy of the families list
    }

    @Override
    public Optional<Family> getFamilyByIndex(int index) {
        return (index >= 0 && index < families.size()) ? Optional.of(families.get(index)) : Optional.empty();
    }

    @Override
    public void saveFamily(Family family) {
        for (int i = 0; i < families.size(); i++) {
            Family existingFamily = families.get(i);

            if (existingFamily.getFather().equals(family.getFather())) {
                int index = families.indexOf(family);
                families.set(index, family);
                saveToFile();
                return;
            }
        }

        families.add(family);
        saveToFile();
    }

    @Override
    public boolean deleteFamily(int index) {
        boolean result = false;
        if (index >= 0 && index < families.size() && !families.isEmpty()) {
            Family removed = families.remove(index);
            result = removed != null;
            saveToFile();
        }

        saveToFile();
        return result;
    }

    @Override
    public boolean deleteFamily(Family family) {
        boolean removed = families.remove(family);

        if (removed) saveToFile();

        saveToFile();
        return removed;
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
        }
        return families;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(families);
            System.out.println("Families saved successfully to the file.");
        } catch (IOException e) {
            System.err.println("Error saving families to the file: " + e.getMessage());
        }
    }
}
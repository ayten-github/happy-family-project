package az.edu.strangers.util;

import az.edu.strangers.entity.Family;

import java.io.*;
import java.util.List;

public class FileUtil {
    public static void writeObjectToFile(List<Family> families , String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(families);
        } catch (IOException ex) {
            System.out.println("File cant witten");
        }
    }

    public static List<Family> readObjectFromFile(String name) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))){
            return(List<Family>) in.readObject();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }catch (IOException |ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}

package az.edu.strangers.util;

import java.io.*;

public class FileUtil {
    public static void writeObjectToFile(Serializable object, String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(object);
        } catch (IOException ex) {
            System.out.println("File cant witten");
        }
    }

    public static Object readObjectFromFile(String name) {
        Object obj = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))){
            obj=in.readObject();
        } catch (IOException ex) {
            System.out.println("Object not found....");
        } finally {
            return obj;
        }

    }
}

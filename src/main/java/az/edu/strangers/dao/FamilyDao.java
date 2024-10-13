package az.edu.strangers.dao;

import java.util.List;
import java.util.Optional;

public interface FamilyDao<T> {

    List<T> getAllFamilies();

    Optional<T> getFamilyByIndex(long id);

    boolean deleteFamily(long id);

    boolean deleteFamily(T t);

    T saveFamily(T t);
}

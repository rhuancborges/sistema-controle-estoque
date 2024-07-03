package dao.interfaces;

import java.util.List;

public interface ObjectDAO<T> {
    void registerObject(T object) throws Exception;
    T searchObjectById(Integer id) throws Exception;
    T searchObjectByString(String searchString) throws Exception;
    List<T> listObject() throws Exception;
    void removeObject(T object);
}

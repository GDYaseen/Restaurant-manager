package DAO;

import java.util.List;

public interface DAO<T>{
    public void addElement(T t);
    public void deleteElement(T t);
    public List<T> getElements();
    public void updateElement(T t);
}

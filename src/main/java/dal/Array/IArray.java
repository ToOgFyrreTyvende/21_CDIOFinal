package dal.Array;

import java.util.List;

public interface IArray<T> {
    T getA(int Id);
    List<T> getListA();
    void createA(T ob);
    void updateA(T ob);
    void deleteA(int Id);
}

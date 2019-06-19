package dal.Array;

import java.util.List;
//interface med generic type, dvs at alle typer kan bruges

public interface IArray<T> {
    //er ikke her men alle klasser som implementere har en arrayliste, som skabes når man man constructer klassen

    T getA(int Id);
    //henter et objekt fra en id i en arraylist

    List<T> getListA();
    //henter listen af objekter i arraylisten

    void createA(T ob);
    //Tilføjer element i arraylisten

    void updateA(T ob);
    //til at opdatere et objekt i arraylisten

    void deleteA(int Id);
    //sletter et objekt i arraylisten
}

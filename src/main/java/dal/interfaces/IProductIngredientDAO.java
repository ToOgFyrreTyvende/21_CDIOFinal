package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductIngredientDAO {
    IProductIngredient createproductIngredient();

    IProductIngredient getObject(Connection conn, int ingredientId) throws NotFoundException, SQLException;

    void load(Connection conn, IProductIngredient productIngredient) throws NotFoundException, SQLException;

    List<IProductIngredient> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IProductIngredient productIngredient) throws SQLException;

    void save(Connection conn, IProductIngredient productIngredient) throws NotFoundException, SQLException;

    void delete(Connection conn, IProductIngredient productIngredient) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IProductIngredient> searchMatching(Connection conn, IProductIngredient productIngredient) throws SQLException;
}

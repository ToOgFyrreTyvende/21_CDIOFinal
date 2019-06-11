package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.ProductIngredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductIngredientDAO {
    ProductIngredient createproductIngredient();

    ProductIngredient getObject(Connection conn, int ingredientId) throws NotFoundException, SQLException;

    void load(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, ProductIngredient productIngredient) throws SQLException;

    void save(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException;

    void delete(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, ProductIngredient productIngredient) throws SQLException;
}

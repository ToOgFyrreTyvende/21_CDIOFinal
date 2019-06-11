package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    Product createproduct();

    Product getObject(Connection conn, int productId) throws NotFoundException, SQLException;

    void load(Connection conn, Product product) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, Product product) throws SQLException;

    void save(Connection conn, Product product)
            throws NotFoundException, SQLException;

    void delete(Connection conn, Product product)
                    throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, Product product) throws SQLException;
}

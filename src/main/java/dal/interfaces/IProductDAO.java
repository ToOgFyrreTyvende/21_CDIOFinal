package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.IProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    IProduct createproduct();

    IProduct getObject(Connection conn, int productId) throws NotFoundException, SQLException;

    void load(Connection conn, IProduct product) throws NotFoundException, SQLException;

    List<IProduct> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IProduct product) throws SQLException;

    void save(Connection conn, IProduct product)
            throws NotFoundException, SQLException;

    void delete(Connection conn, IProduct product)
                    throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IProduct> searchMatching(Connection conn, IProduct product) throws SQLException;
}

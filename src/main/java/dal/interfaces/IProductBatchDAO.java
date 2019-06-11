package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.ProductBatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductBatchDAO {
    ProductBatch createProductBatch();

    ProductBatch getObject(Connection conn, int prodBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, ProductBatch ProductBatch) throws SQLException;

    void save(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, ProductBatch ProductBatch) throws SQLException;
}

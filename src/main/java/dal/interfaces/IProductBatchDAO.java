package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.ProductBatch;
import dto.interfaces.IProductBatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductBatchDAO {
    IProductBatch createProductBatch();

    IProductBatch getObject(Connection conn, int prodBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException;

    List<IProductBatch> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IProductBatch ProductBatch) throws SQLException;

    void save(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IProductBatch> searchMatching(Connection conn, IProductBatch ProductBatch) throws SQLException;
}
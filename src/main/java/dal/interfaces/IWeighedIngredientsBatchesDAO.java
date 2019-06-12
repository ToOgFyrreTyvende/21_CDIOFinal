package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.WeighedIngredientsBatches;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IWeighedIngredientsBatchesDAO {
    WeighedIngredientsBatches createweighedBatch();

    WeighedIngredientsBatches getObject(Connection conn, int weighedBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, WeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, WeighedIngredientsBatches weighedBatch) throws SQLException;

    void save(Connection conn, WeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, WeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, WeighedIngredientsBatches weighedBatch) throws SQLException;
}

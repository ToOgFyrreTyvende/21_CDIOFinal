package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.IWeighedIngredientsBatches;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IWeighedIngredientsBatchesDAO {
    IWeighedIngredientsBatches createweighedBatch();

    IWeighedIngredientsBatches getObject(Connection conn, int weighedBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    List<IWeighedIngredientsBatches> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IWeighedIngredientsBatches weighedBatch) throws SQLException;

    void save(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IWeighedIngredientsBatches> searchMatching(Connection conn, IWeighedIngredientsBatches weighedBatch) throws SQLException;
}

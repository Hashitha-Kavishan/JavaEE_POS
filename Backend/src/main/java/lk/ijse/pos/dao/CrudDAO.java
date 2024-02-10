package lk.ijse.pos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<CONNECTION, T, ID> extends SuperDAO {
    ArrayList<T> getAll(CONNECTION connection) throws SQLException, ClassNotFoundException;

    boolean save(CONNECTION connection, T dto) throws SQLException, ClassNotFoundException;

    boolean update(CONNECTION connection, T dto) throws SQLException, ClassNotFoundException;

    T search(CONNECTION connection, ID id) throws SQLException, ClassNotFoundException;

    boolean exit(CONNECTION connection, ID id) throws SQLException, ClassNotFoundException;

    boolean delete(CONNECTION connection, ID id) throws SQLException, ClassNotFoundException;
}
package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;

public interface ItemDAO extends CrudDAO<Connection, Item, String> {
}

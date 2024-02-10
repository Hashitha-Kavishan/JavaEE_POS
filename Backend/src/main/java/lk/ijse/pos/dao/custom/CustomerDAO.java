package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;

public interface CustomerDAO extends CrudDAO<Connection, Customer, String> {
}
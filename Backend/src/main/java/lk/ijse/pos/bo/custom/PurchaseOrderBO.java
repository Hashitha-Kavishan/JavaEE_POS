package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    boolean purchaseOrder(Connection connection, OrderDTO dto) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(Connection connection, String code) throws SQLException, ClassNotFoundException;

    boolean checkItemIsAvailable(Connection connection, String code) throws SQLException, ClassNotFoundException;

    boolean checkCustomerIsAvailable(Connection connection, String id) throws SQLException, ClassNotFoundException;

    String generateNewOrderID(Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;
}
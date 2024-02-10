package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean purchaseOrder(Connection connection, OrderDTO order) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        if (!orderDAO.save(connection, new Order(order.getOrderId(), order.getCusId(), order.getCost(), order.getOrderDate()))) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        for (OrderDetailDTO detailDTO : order.getOrderDetails()) {
            if (!orderDetailDAO.save(connection, new OrderDetail(detailDTO.getOrderId(), detailDTO.getItemCode(), detailDTO.getPrice(), detailDTO.getQty()))) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            ItemDTO item = searchItem(connection, detailDTO.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

            if (!itemDAO.update(connection, new Item(item.getCode(), item.getName(), item.getQtyOnHand(), item.getPrice()))) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(connection, id);
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getSalary());
    }

    @Override
    public ItemDTO searchItem(Connection connection, String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(connection, code);
        return new ItemDTO(item.getCode(), item.getName(), item.getQty(), item.getPrice());
    }

    @Override
    public boolean checkItemIsAvailable(Connection connection, String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean checkCustomerIsAvailable(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderID(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId(connection);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.bo.custom.OrderDetailBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO, SuperBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetail> all = orderDetailDAO.getAll(connection);
        ArrayList<OrderDetailDTO> orderDetails = new ArrayList<>();

        for (OrderDetail orderDetail : all) {
            orderDetails.add(new OrderDetailDTO(orderDetail.getOrderId(), orderDetail.getCode(), orderDetail.getPrice(), orderDetail.getQty()));
        }

        return orderDetails;
    }
}
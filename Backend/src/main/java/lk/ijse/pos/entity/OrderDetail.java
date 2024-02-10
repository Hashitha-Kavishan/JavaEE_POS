package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {
    private String orderId;
    private String code;
    private double price;
    private int qty;
}

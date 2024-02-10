package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ItemDTO {
    private String code;
    private String name;
    private int qtyOnHand;
    private double price;
}

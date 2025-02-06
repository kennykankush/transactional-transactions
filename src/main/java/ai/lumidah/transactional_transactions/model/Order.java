package ai.lumidah.transactional_transactions.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int orderId;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private float tax;
    private OrderDetail orderDetail;

}

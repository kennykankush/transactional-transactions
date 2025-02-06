package ai.lumidah.transactional_transactions.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    private int id;
    private int orderId;
    private List<Product> products;
    
    
}

package ai.lumidah.transactional_transactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String productName;
    private float unitPrice;
    private float discount;
    private int quantity;
    
}

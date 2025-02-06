package ai.lumidah.transactional_transactions.repository;

public class SqlQueryland {

    public final static String SQL_CREATE_ORDER =
    """
    insert into orders (
    order_date, customer_name, ship_address, notes, tax
    )
    values (
    ?, ?, ?, ?, ?
    )        
    """;

    public final static String SQL_CREATE_ORDER_DETAILS = 
    """
    insert into order_details (
    order_id, product, unit_price, discount, quantity
    )
    values (
    ?, ?, ?, ?, ?
    )        
    """;
    
}

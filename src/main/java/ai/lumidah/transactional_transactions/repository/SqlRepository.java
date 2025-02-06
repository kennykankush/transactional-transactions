package ai.lumidah.transactional_transactions.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ai.lumidah.transactional_transactions.model.Order;
import ai.lumidah.transactional_transactions.model.OrderDetail;
import ai.lumidah.transactional_transactions.model.Product;

@Repository
public class SqlRepository {

    @Autowired
    private JdbcTemplate template;

    public int createOrder(Order order){

        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SqlQueryland.SQL_CREATE_ORDER, new String[]{"order_id"});

                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());

                return ps;
                }
        };

        template.update(psc, keyHolder);

        int primaryKey = keyHolder.getKey().intValue();

        return primaryKey;

    }

    public boolean CreateOrderDetails(Order order){

        OrderDetail orderDetail = order.getOrderDetail();
        List<Product> products = orderDetail.getProducts();

        int update = 0;
        for (Product product : products){
            int currentRow = template.update(SqlQueryland.SQL_CREATE_ORDER_DETAILS, order.getOrderId(), product.getProductName(), product.getUnitPrice(), product.getDiscount(), product.getQuantity());
            update += currentRow;
        }

        return products.size() == update;

    }
    
}

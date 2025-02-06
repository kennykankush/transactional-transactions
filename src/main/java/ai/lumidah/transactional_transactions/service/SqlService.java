package ai.lumidah.transactional_transactions.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.lumidah.transactional_transactions.model.Order;
import ai.lumidah.transactional_transactions.model.OrderDetail;
import ai.lumidah.transactional_transactions.model.Product;
import ai.lumidah.transactional_transactions.repository.SqlRepository;

@Service
public class SqlService {

    @Autowired
    private SqlRepository sqlRepository;

    @Transactional
    public boolean createOrder(String json) throws JsonProcessingException, ParseException {
        
        boolean bCreated = false;

        Order order = jsonToObject(json);

        try {
            int order_id = sqlRepository.createOrder(order);
            order.setOrderId(order_id);
            
            // throw new IllegalStateException("Simulating error after inserting Order...");
            sqlRepository.CreateOrderDetails(order);
            bCreated = true;

            return bCreated;

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Transaction rollback test: " + e.getMessage());
        }
    }
    
    

    public Order jsonToObject(String json) throws JsonProcessingException, ParseException{

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order();
        
        JsonNode orderJsonNode = objectMapper.readTree(json);

        String dateJson = orderJsonNode.get("orderDate").asText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date utilDate = dateFormat.parse(dateJson);
        Date date = new Date(utilDate.getTime());

        order.setOrderDate(date);
        order.setCustomerName(orderJsonNode.get("customerName").asText());
        order.setShipAddress(orderJsonNode.get("shipAddress").asText());
        order.setNotes(orderJsonNode.get("notes").asText());
        order.setTax((float) orderJsonNode.get("tax").asDouble());

        OrderDetail orderDetail = new OrderDetail();
        JsonNode orderDetailNode = orderJsonNode.get("orderDetail");
    
        List<Product> products = new ArrayList<>();
        JsonNode jsonProducts = orderDetailNode.get("products");
        

        for (JsonNode jsonProduct : jsonProducts){
            Product product = new Product();
            product.setProductName(jsonProduct.get("product").asText());
            product.setUnitPrice((float) jsonProduct.get("unitPrice").asDouble());
            product.setDiscount((float) jsonProduct.get("discount").asDouble());
            product.setQuantity(jsonProduct.get("quantity").asInt());

            products.add(product);
        }
        
        orderDetail.setProducts(products);
        order.setOrderDetail(orderDetail);

        return order;

    
    }
    
}

package ai.lumidah.transactional_transactions.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import ai.lumidah.transactional_transactions.model.Order;
import ai.lumidah.transactional_transactions.service.SqlService;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Autowired
    private SqlService sqlService;

    @PostMapping( path = "/submit", consumes = "application/json")
    public ResponseEntity<Object> submitOrder(@RequestBody String json) throws JsonProcessingException, ParseException{
        boolean boo = sqlService.createOrder(json);
        return ResponseEntity.ok().body(boo);

    }
    
    
}

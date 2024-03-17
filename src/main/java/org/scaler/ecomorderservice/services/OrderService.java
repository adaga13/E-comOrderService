package org.scaler.ecomorderservice.services;

import org.scaler.ecomorderservice.dtos.BuyProductDto;
import org.scaler.ecomorderservice.exceptions.OrderDoesNotExistException;
import org.scaler.ecomorderservice.models.Order;
import org.scaler.ecomorderservice.repositories.OrderRepository;
import org.slf4j.Logger;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    private final Logger logger;

    private Environment environment;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, Logger logger, Environment env) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.logger = logger;
        this.environment = env;
    }

    public List<Order> getOrdersByUserId(String token) {
        long userId = extractUserIdFromToken(token);
        return orderRepository.findAllByUserId(userId);
    }

    public Order getOrderByOrderId(Long orderId) throws OrderDoesNotExistException {
        return orderRepository.findById(orderId).orElseThrow(() -> new
                OrderDoesNotExistException("Order with id : " + orderId + " doesn't exist."));
    }

    public Order createOrder(String token, long productId, int quantity, double amount, double discount) {
        long userId = extractUserIdFromToken(token);
        String productServiceUrl = environment.getProperty("product.service.buy.url") + "products/buy";
        if (logger.isDebugEnabled())
            logger.debug("Sending request to product service url : {} for product id : {} and quantity: {}.",
                    productServiceUrl, productId, quantity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<BuyProductDto> httpEntity = new HttpEntity<>(new BuyProductDto(productId, quantity), headers);
        Boolean response = restTemplate.postForObject(productServiceUrl, httpEntity, Boolean.class);
        if (logger.isDebugEnabled())
            logger.debug("Received response from product service url as : {}.", response);
        if (Boolean.TRUE.equals(response)) {
            Order newOrder = new Order();
            newOrder.setUserId(userId);
            newOrder.setProductId(productId);
            newOrder.setOrderDate(new Date());
            newOrder.setAmount(amount);
            newOrder.setQuantity(quantity);
            newOrder.setDiscount(discount);
            Order createdOrder = orderRepository.save(newOrder);
            if (logger.isDebugEnabled())
                logger.debug("Order created successfully for user id : {}, product id : {} and order id is {}.",
                        userId, productId, createdOrder.getId());
            return createdOrder;
        }

        return null;
    }

    private long extractUserIdFromToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> payloadData = parser.parseMap(payload);
        return ((Integer) payloadData.get("userId")).longValue();
    }
}

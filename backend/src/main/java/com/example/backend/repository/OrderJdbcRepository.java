package com.example.backend.repository;

import com.example.backend.model.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.backend.repository.JdbcUtils.toLocalDateTime;
import static com.example.backend.repository.JdbcUtils.toUUID;

@Repository
public class OrderJdbcRepository implements OrderRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        var orders = jdbcTemplate.query("SELECT * FROM orders", orderDtoRowMapper);
        var orderList = new ArrayList<Order>();
        orders.forEach(order -> {
            var orderItems = jdbcTemplate.query("SELECT * FROM order_items ",
                    Collections.singletonMap("orderId", order.orderId()),
                    orderItemRowMapper
            );
            System.out.println(orderItems.get(0).price());
            System.out.println(orderItems.get(1).price());
            orderList.add(orderDtoToOrder(order, orderItems));
        });
        return orderList;
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update("INSERT INTO orders(order_id, email, address, postcode, order_status, created_at, updated_at)" +
                        "VALUES(UNHEX(REPLACE(:orderId, '-','')), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
                toOrderParamMap(order));
        order.getOrderItems()
                .forEach(item ->
                        jdbcTemplate.update("INSERT INTO order_items(order_id, product_id, category, price, quantity, created_at, updated_at)" +
                                        "VALUES(UNHEX(REPLACE(:orderId, '-','')), UNHEX(REPLACE(:productId, '-','')), :category, :price, :quantity, :createdAt, :updatedAt)",
                                toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));
        return order;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        try{
            var order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE order_id = UNHEX(REPLACE(:orderId, '-',''))",
                    Collections.singletonMap("orderId", orderId.toString()), orderDtoRowMapper);
            var orderItems = jdbcTemplate.query("SELECT * FROM order_items ",
                    Collections.singletonMap("orderId", order.orderId()),
                    orderItemRowMapper
            );
            return Optional.of(
                    orderDtoToOrder(order, orderItems)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> findByEmail(Email email) {
        try{
            var order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE email = :email",
                    Collections.singletonMap("email", email.toString()), orderDtoRowMapper);
            var orderItems = jdbcTemplate.query("SELECT * FROM order_items ",
                    Collections.singletonMap("orderId", order.orderId()),
                    orderItemRowMapper
            );
            return Optional.of(
                    orderDtoToOrder(order, orderItems)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> findByOrderStatus(OrderStatus orderStatus) {
        try{
            var order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE order_status = :orderStatus",
                    Collections.singletonMap("orderStatus", orderStatus.toString()), orderDtoRowMapper);
            var orderItems = jdbcTemplate.query("SELECT * FROM order_items ",
                    Collections.singletonMap("orderId", order.orderId()),
                    orderItemRowMapper
            );
            return Optional.of(
                    orderDtoToOrder(order, orderItems)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(UUID orderId) {
        jdbcTemplate.update("DELETE FROM orders WHERE order_id = :orderId",
                Collections.singletonMap("orderId", orderId));
    }


    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId.toString());
        paramMap.put("productId", item.productId().toString());
        paramMap.put("category", item.category().toString());
        paramMap.put("price", item.price());
        paramMap.put("quantity", item.quantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);
        return paramMap;
    }

    private static final RowMapper<OrderDto> orderDtoRowMapper = (resultSet, i) -> {
        var orderId = toUUID(resultSet.getBytes("order_id"));
        var email = new Email(resultSet.getString("email"));
        var address = resultSet.getString("address");
        var postcode = resultSet.getString("postcode");
        var orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new OrderDto(orderId, email, address, postcode, orderStatus, createdAt, updatedAt);
    };

    private static final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        var orderId = toUUID(resultSet.getBytes("order_id"));
        var email = new Email(resultSet.getString("email"));
        var address = resultSet.getString("address");
        var postcode = resultSet.getString("postcode");
        var orderitems = new ArrayList<OrderItem>();
        var orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Order(orderId, email, address, postcode, orderitems, orderStatus, createdAt, updatedAt);
    };

    private static final RowMapper<OrderItem> orderItemRowMapper = (resultSet, i) -> {
        var productId = toUUID(resultSet.getBytes("product_id"));
        var category = Category.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var quantity = resultSet.getInt("quantity");
        return new OrderItem(productId, category, price, quantity);
    };

    Order orderDtoToOrder(OrderDto orderDto, List<OrderItem> orderItems){
        var orderId = orderDto.orderId();
        var email = orderDto.email();
        var address = orderDto.address();
        var postcode = orderDto.postcode();
        var orderitems = orderItems;
        var orderStatus = orderDto.orderStatus();
        var createdAt = orderDto.createdAt();
        var updatedAt = orderDto.updatedAt();
        return new Order(orderId, email, address, postcode, orderitems, orderStatus, createdAt, updatedAt);
    }
}

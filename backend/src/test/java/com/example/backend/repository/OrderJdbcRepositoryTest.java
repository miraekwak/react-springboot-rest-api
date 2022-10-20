package com.example.backend.repository;

import com.example.backend.model.*;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_10;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@SpringBootTest

class OrderJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void setUp() {
        var config = aMysqldConfig(v5_7_10)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    static void cleanup() {
        embeddedMysql.stop();
    }

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    private static Product product1 = new Product(UUID.randomUUID(), "product1", Category.MACAROON, 1000);
    private static Product product2 = new Product(UUID.randomUUID(), "product2", Category.COFFEE_BEAN_PACKAGE, 2000);
    private static Member member = new Member(UUID.randomUUID(), "test-user",Role.USER, "test", "test");

    @Test
    void insertData() {

    }

    @Test
    void insertTest() {
        // when
        productRepository.insert(product1);
        productRepository.insert(product2);

        OrderItem orderItem1 = new OrderItem(product1.getProductId(), Category.MACAROON, 20000, 20);
        OrderItem orderItem2 = new OrderItem(product2.getProductId(), Category.COFFEE_BEAN_PACKAGE, 6000, 3);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        Order order = new Order(UUID.randomUUID(), member.getMemberId(), new Email("alfo9490@naver.com"), "daejeon", "1234", orderItemList, OrderStatus.ACCEPTED, LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS), LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        orderRepository.insert(order);

        assertThat(order.getOrderItems().get(0).productId(), samePropertyValuesAs(orderItem1.productId()));

    }

    @Test
    void findAllTest() {
        // when
        UUID orderId = UUID.fromString("68058067-414a-4d2e-b5d0-209ccf755e8c");
        var order = orderRepository.findById(orderId);

        // then
        var orderList = orderRepository.findAll();
        assertThat(orderList.get(0).getEmail(), samePropertyValuesAs(order.get().getEmail()));
        System.out.println(orderList.get(0).getEmail());
        assertThat(orderList.get(0).getOrderItems().get(0).productId(), samePropertyValuesAs(order.get().getOrderItems().get(0).productId()));
        assertThat(orderList.get(0).getOrderItems().get(1).productId(), samePropertyValuesAs(order.get().getOrderItems().get(1).productId()));
        assertThat(orderList.get(0), samePropertyValuesAs(order.get()));
    }
}
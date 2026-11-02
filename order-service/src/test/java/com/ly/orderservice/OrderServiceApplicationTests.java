package com.ly.orderservice;

import com.ly.orderservice.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.mysql.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void shouldCreateAnOrder(){
        String requestBody = """
                {
                  "sku": "iphone_15",
                  "price": 1000,
                  "quantity": 10
                }
                
                """;

        InventoryClientStub.stubInventoryCall("iphone_15", 10);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(200)
                .body("sku", Matchers.equalTo("iphone_15"))
                .body("price", Matchers.equalTo(1000))
                .body("quantity", Matchers.equalTo(10));
    }

    @Test
    void shouldNotCreateAnOrder(){
        String requestBody = """
                {
                  "sku": "iphone_15",
                  "price": 1000,
                  "quantity": 101
                }

                """;

        InventoryClientStub.stubInventoryCall("iphone_15", 101, false);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(500);
    }

}

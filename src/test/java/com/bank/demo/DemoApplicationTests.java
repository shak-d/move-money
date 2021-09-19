package com.bank.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


import com.bank.demo.transaction.TransactionStatus;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = {"spring.main.allow-bean-definition-overriding=true", "server.servlet.context-path=/"})
class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

	@Test
	void sendMoneyAndVerifyBalance(){
		given().get("/account/0").then().statusCode(200)
		.assertThat().body("balance",equalTo(1000f));

		given().get("/account/1").then().statusCode(200)
		.assertThat().body("balance",equalTo(1000f));

		given().get("/transaction/new?sender=0&recipient=1&amount=100").then().statusCode(200)
		.assertThat().body("status",equalTo(TransactionStatus.SUCCESSFUL.toString()));

		given().get("/account/0").then().statusCode(200)
		.assertThat().body("balance",equalTo(900f));

		given().get("/account/1").then().statusCode(200)
		.assertThat().body("balance",equalTo(1100f));
	}

}

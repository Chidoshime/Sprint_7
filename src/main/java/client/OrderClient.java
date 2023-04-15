package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import client.base.ScooterRestClient;
import model.order.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    private static final String ORDER_URI = BASE_URI + "orders/";

    @Step("Create order {order}")
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseReqSpec())
                .body(order)
                .when()
                .post(ORDER_URI)
                .then();
    }

    @Step("Delete order {track}")
    public ValidatableResponse delete(int track) {
        return given()
                .spec(getBaseReqSpec())
                .body("{ \"track\": " + track + "}")
                .when()
                .put(ORDER_URI + "cancel/")
                .then();
    }

    @Step("Get list of orders")
    public ValidatableResponse getOrdersList(){
        return given()
                .spec(getBaseReqSpec())
                .when()
                .get(ORDER_URI)
                .then();
    }
}
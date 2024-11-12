package arcis.ramon.inditexProducto.infraestructure.in.web;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import arcis.ramon.inditexProducto.application.port.in.PriceServicePort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PriceControllerAdapterTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceServicePort port;




    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8050;
    }


 @Test
 @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void testGetProductPrice() throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
     LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
     DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
     String formattedDate = date.format(formatter);
     ValidatableResponse response =given()
                .param("brandId", brandId)
                .param("date", formattedDate)
                .param("productId", productId)
                .baseUri(RestAssured.baseURI)
                .port(RestAssured.port)
                .when()
                .get("/price")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);

     System.out.println(response.extract().body().asString());
     JsonPath jp = new JsonPath(response.extract().body().asString());
     String branName = jp.get("brandName").toString();
     String productIdValue = jp.get("productId").toString();
     String priceValue = jp.get("price").toString();
     String tarifa = jp.get("priceList").toString();
     String startDate = jp.get("startDate").toString();
     String endDate = jp.get("endDate").toString();

     assertEquals("ZARA", branName, "Value from API doesn't match Value from DB");
     assertEquals("35455", productIdValue, "Value from API doesn't match Value from DB");
     assertEquals("35,50 EUR", priceValue, "Value from API doesn't match Value from DB");
     assertEquals("1", tarifa, "Value from API doesn't match Value from DB");
     assertEquals("2020-06-14T00:00:00", startDate, "Value from API doesn't match Value from DB");
     assertEquals("2020-12-31T23:59:59", endDate, "Value from API doesn't match Value from DB");
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void testGetProductPrice2() throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = date.format(formatter);


        ValidatableResponse response =given()
                .param("brandId", brandId)
                .param("date", formattedDate)
                .param("productId", productId)
                .baseUri(RestAssured.baseURI)
                .port(RestAssured.port)
                .when()
                .get("/price")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);


        JsonPath jp = new JsonPath(response.extract().body().asString());
        String branName = jp.get("brandName").toString();
        String productIdValue = jp.get("productId").toString();
        String priceValue = jp.get("price").toString();
        String tarifa = jp.get("priceList").toString();
        String startDate = jp.get("startDate").toString();
        String endDate = jp.get("endDate").toString();

        assertEquals("ZARA", branName, "Value from API doesn't match Value from DB");
        assertEquals("35455", productIdValue, "Value from API doesn't match Value from DB");
        assertEquals("25,45 EUR", priceValue, "Value from API doesn't match Value from DB");
        assertEquals("2", tarifa, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-14T15:00:00", startDate, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-14T18:30:00", endDate, "Value from API doesn't match Value from DB");
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void testGetProductPrice3() throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = date.format(formatter);


        ValidatableResponse response =given()
                .param("brandId", brandId)
                .param("date", formattedDate)
                .param("productId", productId)
                .baseUri(RestAssured.baseURI)
                .port(RestAssured.port)
                .when()
                .get("/price")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);


        JsonPath jp = new JsonPath(response.extract().body().asString());
        String branName = jp.get("brandName").toString();
        String productIdValue = jp.get("productId").toString();
        String priceValue = jp.get("price").toString();
        String tarifa = jp.get("priceList").toString();
        String startDate = jp.get("startDate").toString();
        String endDate = jp.get("endDate").toString();

        assertEquals("ZARA", branName, "Value from API doesn't match Value from DB");
        assertEquals("35455", productIdValue, "Value from API doesn't match Value from DB");
        assertEquals("35,50 EUR", priceValue, "Value from API doesn't match Value from DB");
        assertEquals("1", tarifa, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-14T00:00:00", startDate, "Value from API doesn't match Value from DB");
        assertEquals("2020-12-31T23:59:59", endDate, "Value from API doesn't match Value from DB");
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void testGetProductPrice4() throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = date.format(formatter);



        ValidatableResponse response =given()
                .param("brandId", brandId)
                .param("date", formattedDate)
                .param("productId", productId)
                .baseUri(RestAssured.baseURI)
                .port(RestAssured.port)
                .when()
                .get("/price")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);


        JsonPath jp = new JsonPath(response.extract().body().asString());
        String branName = jp.get("brandName").toString();
        String productIdValue = jp.get("productId").toString();
        String priceValue = jp.get("price").toString();
        String tarifa = jp.get("priceList").toString();
        String startDate = jp.get("startDate").toString();
        String endDate = jp.get("endDate").toString();

        assertEquals("ZARA", branName, "Value from API doesn't match Value from DB");
        assertEquals("35455", productIdValue, "Value from API doesn't match Value from DB");
        assertEquals("30,50 EUR", priceValue, "Value from API doesn't match Value from DB");
        assertEquals("3", tarifa, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-15T00:00:00", startDate, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-15T11:00:00", endDate, "Value from API doesn't match Value from DB");
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void testGetProductPrice5() throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = date.format(formatter);



        ValidatableResponse response =given()
                .param("brandId", brandId)
                .param("date", formattedDate)
                .param("productId", productId)
                .baseUri(RestAssured.baseURI)
                .port(RestAssured.port)
                .when()
                .get("/price")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);


        JsonPath jp = new JsonPath(response.extract().body().asString());
        String branName = jp.get("brandName").toString();
        String productIdValue = jp.get("productId").toString();
        String priceValue = jp.get("price").toString();
        String tarifa = jp.get("priceList").toString();
        String startDate = jp.get("startDate").toString();
        String endDate = jp.get("endDate").toString();

        assertEquals("ZARA", branName, "Value from API doesn't match Value from DB");
        assertEquals("35455", productIdValue, "Value from API doesn't match Value from DB");
        assertEquals("38,95 EUR", priceValue, "Value from API doesn't match Value from DB");
        assertEquals("4", tarifa, "Value from API doesn't match Value from DB");
        assertEquals("2020-06-15T16:00:00", startDate, "Value from API doesn't match Value from DB");
        assertEquals("2020-12-31T23:59:59", endDate, "Value from API doesn't match Value from DB");
    }
}
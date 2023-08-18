
import objects.bankGo.Exchange;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestEXCH {
    static String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Test
    public void test() {
        String str = when()
                .get(URL)
                .then()
                .log()
                .all()
                .toString();
        System.out.println(str);
    }
    @Test
    public void test1(){
        when()
                .get(URL)
                .then()
                .body("cc",notNullValue());
    }
    @Test
    public void test2(){
       List<String> list =when()
                .get(URL)
                .then()
               .extract()
               .jsonPath()
               .getList("cc",String.class);
        System.out.println(list);
    }
    @Test
    public void test3(){
        List<Exchange> list =
                when()
                .get(URL)
                .then()
                .extract()
                .jsonPath()
                .getList(".",Exchange.class);



        for (Exchange exchange : list) {
            System.out.println(exchange.cc);
        }
    }
    @Test
    public void test4(){
        Exchange exchange= when()
                .get("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=MXN&json")
                .then()
                .extract()
                .jsonPath()
                .getList(".",Exchange.class).get(0);
        System.out.println(exchange.rate);
    }

}

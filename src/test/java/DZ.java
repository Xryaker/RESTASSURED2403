import io.restassured.RestAssured;
import objects.bankGo.Exchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.when;

@RunWith(Parameterized.class)
public class DZ {

    static Exchange exchange;


    @Parameterized.Parameters
    public static Collection options(){
        return Arrays.asList("USD","CAD","MXN");
    }

    public DZ(String str) {
        exchange = when()
                .get("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode="+str+"&json")
                .then()
                .extract()
                .jsonPath()
                .getList(".",Exchange.class)
                .get(0);
    }

    @Test
    public void test(){
        System.out.println(exchange.cc + " = " + exchange.rate);
    }
}

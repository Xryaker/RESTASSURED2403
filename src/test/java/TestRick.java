import config.BaseClass;
import objects.rick.characterobjects.Characters;
import objects.rick.characterobjects.Info;
import objects.rick.characterobjects.Result;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class TestRick extends BaseClass {

    @Test
    public void test() {
        System.out.println(when()
                .get()
                .then()
                .log()
                .body());
    }

    @Test
    public void test1() {
        when()
                .get("character")
                .then()
                .body("info.pages", equalTo(4));
    }

    @Test
    public void test2() {
        int i = when()
                .get("character")
                .then()
                .extract().body().path("info.count");
        System.out.println("== " + i);
    }

    @Test
    public void test3() {
        Info info = when()
                .get("character")
                .then()
                .extract()
                .jsonPath()
                .getObject("info", Info.class);
        System.out.println(info.count);
    }

    @Test
    public void test4() {
        Characters character = when()
                .get("character")
                .then()
                .extract().as(Characters.class);
        System.out.println(character.info.next);
    }

    @Test
    public void test5() {
        List<Result> list = when().get("character").then().extract().jsonPath().getList("results", Result.class);
        for (Result l : list) {
            System.out.println(l.name);
        }
    }

    @Test
    public void test6() {
        for (String l : when().get("character").then().extract().jsonPath().getList("results.name", String.class)) {
            System.out.println(l);
        }
    }

}

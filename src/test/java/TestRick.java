import config.BaseClass;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import objects.rick.characterobjects.Characters;
import objects.rick.characterobjects.Info;
import objects.rick.characterobjects.Result;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
@Feature("Junit 4 support")
@Epic("Allure examples")
public class TestRick extends BaseClass {

    @Test
    @Description("get all log responses rick and morty api")
    @DisplayName("Smoke test main APi")
    @Severity(SeverityLevel.CRITICAL)
    public void test() {
        System.out.println(when()
                .get()
                .then()
                .log()
                .body());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void test1() {
        when()
                .get("character")
                .then()
                .body("info.pages", equalTo(4));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void test2() {
        int i = when()
                .get("character")
                .then()
                .extract().body().path("info.count");
        System.out.println("== " + i);
    }

    @Test
    @Description(" Used method with annotation @Step ")
    @Severity(SeverityLevel.NORMAL)
    public void test3() {
        Info info = when()
                .get("character")
                .then()
                .extract()
                .jsonPath()
                .getObject("info", Info.class);
        infoCheck(info);
        System.out.println(info.count);
    }

    @Test
    @DisplayName("Attachment")
    @Severity(SeverityLevel.MINOR)
    @Description(" Used annotation @Attachment ")
    public void test4() {
        Characters character = when()
                .get("character")
                .then()
                .extract().as(Characters.class);
        Allure.addAttachment("Next url link",character.info.next);
        checkCharacter(character);
        System.out.println(character.info.next);
    }

    @Step("Check character {character.info.pages}")
    @Severity(SeverityLevel.MINOR)
    @Story("Advanced support for bdd annotations")
    @Attachment
    private String checkCharacter(Characters character) {
    return character.results.get(0).species;
    }

    @Test
    @DisplayName("Used Links")
    @Link("https://docs.qameta.io/allure/#_junit_4")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "Vasiliy",type = "mylink")
    @Link(name ="my favorite site",type = "youtube")
    @Link(name = "news",type = "youTube",value = "rU7_50FSNrI")
    public void test5() {
        List<Result> list = when().get("character").then().extract().jsonPath().getList("results", Result.class);
        for (Result l : list) {
            System.out.println(l.name);
        }
    }

    @Test
    @DisplayName("TMS and issues")
    @TmsLink("introduction-python")
    @Severity(SeverityLevel.MINOR)
    @Story("Advanced support for bdd annotations")
    @Issue("qa-manual")
    public void test6() {
        for (String l : when().get("character").then().extract().jsonPath().getList("results.name", String.class)) {
            System.out.println(l);
        }
    }

    @Step("Type {info.count} / {info.next}.")
    public void infoCheck(Info info) {
        System.out.println(info);
    }
}

package specification;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class RickAndMortySpecification {

    public static void createEMcpecification(String baseURL){
        RestAssured.requestSpecification= new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseURL)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
        RestAssured.responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}

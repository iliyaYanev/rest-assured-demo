import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import dao.BaseResponse;
import dao.CreateUser;
import dao.User;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

public class CreateUserTests extends TestConfig {


    @Test
    public void createUserExtractResponse() throws IOException {

        String userJson = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/testUser.json")));

        // Create new user
        Response response = (Response) given()
                .contentType(ContentType.JSON)
            .and()
                .auth()
                .oauth2(apiToken)
            .and()
                .body(userJson)
            .when()
                .post(users)
            .then()
                .log()
                .body()
            .and()
                .body("_meta.code", equalTo(201))
            .extract();

        JsonPath responseJsonPath = new JsonPath(response.getBody().asString()).setRootPath("result");
        String id = responseJsonPath.getString("id");

        JsonPath requestJsonPath = new JsonPath(userJson);
        String expectedFirstName = requestJsonPath.getString("first_name");
        String expectedLastName = requestJsonPath.getString("last_name");
        String expectedGender = requestJsonPath.getString("gender");
        String expectedDob = requestJsonPath.getString("dob");

        System.out.println(id);

        //Get the created user
        given()
            .accept(ContentType.JSON)
        .and()
            .auth()
            .oauth2(apiToken)
        .and()
            .pathParam("userId", id)
        .when()
            .get(usersById)
        .then()
            .log()
            .body()
        .and()
            .rootPath("result")
            .body("first_name", equalTo(expectedFirstName))
            .body("last_name", equalTo(expectedLastName))
            .body("gender", equalTo(expectedGender))
            .body("dob", equalTo(expectedDob));

    }

    @Test
    public void createUserDeserializeResponse() {
        CreateUser createUser = new CreateUser();
        createUser.setFirstName("Iliya");
        createUser.setLastName("Yanev");
        createUser.setGender("male");
        createUser.setEmail("randomemail@email.com");

        BaseResponse response = given()
            .contentType(ContentType.JSON)
            .and()
                .auth()
                .oauth2(apiToken)
            .and()
                .body(createUser)
            .when()
                .post(users)
            .then()
                .log()
                .body()
            .and()
                .body("_meta.code", equalTo(201))
            .extract()
            .as(BaseResponse.class);

        assert createUser.getFirstName().equals(response.getResult().getFirstName());
        assert createUser.getLastName().equals(response.getResult().getLastName());
    }
}

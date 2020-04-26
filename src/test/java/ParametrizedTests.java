import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParametrizedTests extends TestConfig {

    @Parameterized.Parameter(0)
    public String id;
    @Parameterized.Parameter(1)
    public String firstName;
    @Parameterized.Parameter(2)
    public String lastName;
    @Parameterized.Parameter(3)
    public String email;

    @Parameters(name = "{index}: user with id {0} should have first name: {1} last name: {2} and email: {3}")
    public static Collection<Object> data() {
        Object[][] data = new Object[][] {
            {"815", "Jacynthe", "Reynolds", "micheal97@example.org"},
            {"835", "Dana", "Rutherford", "wlesch@example.org"},
            {"840", "Dolly",  "Collins", "rogahn.evelyn@example.com"},
            {"850", "Merle", "Okuneva", "xcrist@example.org"},
            {"875", "Tara", "Boehm", "johan18@example.org"}
        };

        return Arrays.asList(data);
    }

    @Test
    public void getUser() {
        given()
            .spec(requestSpec)
        .and()
            .pathParam("userId", id)
        .when()
            .get(usersById)
        .then()
           .spec(responseOkSpec)
        .and()
            .rootPath("result")
            .body("first_name", equalTo(firstName))
            .body("last_name", equalTo(lastName))
            .body("email", equalTo(email));
    }
}

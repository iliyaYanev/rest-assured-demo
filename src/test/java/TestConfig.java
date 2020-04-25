import io.restassured.RestAssured;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;

public class TestConfig {

    protected static String apiToken;
    protected static String usersById;
    protected static String users;
    protected static String posts;
    protected static String comments;
    protected static String albums;
    protected static String photos;

    @Before
    public void setup() throws IOException {
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        users = "/users";
        usersById = "/users/{userId}";
        posts = "/posts";
        comments = "/comments";
        albums = "/albums";
        photos = "/photos";
        apiToken = getApiToken();
    }

    private String getApiToken() throws IOException {
        InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream("authentication.properties");

        Properties prop = new Properties();
        prop.load(inputStream);
        return prop.getProperty("token");
    }
}

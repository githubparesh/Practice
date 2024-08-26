package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    public Response fetchData(String id) {
        return RestAssured.get(BASE_URL + id);
    }
}
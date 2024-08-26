
import Utils.APIClient;
import Utils.CSVReader;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DataValidationTest {
    private CSVReader csvReader;
    private APIClient apiClient;
    private List<String[]> csvData;

    @Before
    public void setup() {
        csvReader = new CSVReader();
        apiClient = new APIClient();
        csvData = csvReader.readCSV("src/main/java/Data/test_data.csv");
    }

    @Test
    public void validateData() {
        for (int j = 1; j < csvData.size(); j++) {
            String[] row = csvData.get(j);
            String id = row[0];
            String expectedTitle = row[1];
            String expectedBody = row[2];
            String ExpectedUserID = row[3];

            Response response = apiClient.fetchData(id);
            Assert.assertEquals(200, response.getStatusCode());

            String actualId = response.jsonPath().getString("id");
            String actualTitle = response.jsonPath().getString("title");
            String actualBody = response.jsonPath().getString("body").replace("\n", " ").trim(); // Normalize actual body
            String actualUserID = response.jsonPath().getString("userId");

            Assert.assertEquals(id, actualId);
            Assert.assertEquals(expectedTitle, actualTitle);
            Assert.assertEquals(expectedBody, actualBody);
            Assert.assertEquals(ExpectedUserID, actualUserID);
        }

    }
}
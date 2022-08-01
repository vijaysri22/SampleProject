import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.browserstack.local.Local;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class restassuredtest {
    public static String  endPoint = "/local/v1/list";
    public static String  domainURL = "https://www.browserstack.com";

    static Local bsLocal = new Local();
    @BeforeMethod
    public void setup(){
        try {
            setupLocalConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void api(){
        try {
            String url = domainURL+endPoint;

            HashMap<String , String> params = new HashMap<String , String>();
            params.put("auth_token","9yRpLEn7y4XChRk99RPM");
            Response restResponse = RestAssured.given().log().all().
                    queryParams(params)
                    .get(url).then().extract().response();

            System.out.println(restResponse.getBody().asString().toString());

            Assert.assertTrue(restResponse.getStatusCode() == 200);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void cleanup() throws Exception {
        bsLocal.stop();
    }
    // connect the browserstack local app
    public static void setupLocalConnection() throws Exception {
        //Local bsLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
        bsLocalArgs.put("key", "9yRpLEn7y4XChRk99RPM");
        System.out.println("test setup1");
        bsLocal.start(bsLocalArgs);
        System.out.println("test setup");
        System.out.println(bsLocal.isRunning());
    }



}

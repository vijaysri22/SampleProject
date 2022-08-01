import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.browserstack.local.Local;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class seleniumtest {
    public static final String USERNAME = "vijaysrivastava_QG4Z8P";
    public static final String AUTOMATE_KEY = "9yRpLEn7y4XChRk99RPM";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static final String domainURL = "http://testjs-13.infoedge.com/";
    public static final String loginEmail = "ejqmuxjdwkueufm72316@jsaut0mati0n.js";
    public static final String loginPassword = "js67812345";
    WebDriver driver;
    static Local bsLocal = new Local();
    @BeforeMethod
    public void setup(){
        try {
           setupLocalConnection();
           driver = invokeDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void login(){
         try {
            driver.navigate().to(domainURL);
            driver.findElement(By.id("loginTopNavBar")).click();
             WebDriverWait wait = new WebDriverWait(driver, 2);
             WebElement email = driver.findElement(By.cssSelector("#EmailContainer input[name='email']"));
             wait.until(ExpectedConditions.elementToBeClickable(email));
             email.sendKeys(loginEmail);
            driver.findElement(By.cssSelector("#PasswordContainer input[name='password']")).sendKeys(loginPassword);
            driver.findElement(By.cssSelector("#jspcLoginLayerButton")).click();
            Thread.sleep(5000);
            WebElement myjs = driver.findElement(By.xpath("//button[@class='cursp free-chat-cta']"));

            Assert.assertTrue(myjs.getText().equalsIgnoreCase("Start chat for free"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void cleanup() throws Exception {
        driver.quit();
        bsLocal.stop();
    }
    //Invokes the webdriver
    public static WebDriver invokeDriver() throws MalformedURLException {
         Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
        capsHashtable.put("browser", "Chrome");
        capsHashtable.put("browser_version", "102");
        capsHashtable.put("os", "Windows");
        capsHashtable.put("os_version", "10");
        capsHashtable.put("build", "browserstack-build-1");
        capsHashtable.put("name", "testjs"+getCurrentTime());
        capsHashtable.put("browserstack.local", "true");
        capsHashtable.put("browserstack.debug", "true");
        capsHashtable.put("browserstack.networkLogs", "true");

        String key;
        DesiredCapabilities caps = new DesiredCapabilities();
        Set<String> keys = capsHashtable.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()) {
            key = itr.next();
            caps.setCapability(key, capsHashtable.get(key));
        }

        WebDriver driver  = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        return driver;




    }

    // connect the browserstack local app
    public static void setupLocalConnection() throws Exception {
        //Local bsLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
        bsLocalArgs.put("key", "9yRpLEn7y4XChRk99RPM");
        bsLocal.start(bsLocalArgs);
    }

    public static String getCurrentTime(){
        return LocalDate.now().toString();
    }




}

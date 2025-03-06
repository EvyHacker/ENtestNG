package com.fnoor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.fnoor.PageFields.*;
import static com.fnoor.SFpageFields.*;

public class FundraisingPageDriver {

    static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public static void ensAuthTest() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            // Test Account 09
            String body = "6c9a345f-f836-4981-b6c0-08ffed29d491";

            InputStream is = new ByteArrayInputStream(body.getBytes());
            InputStreamEntity inputStreamEntity;
            inputStreamEntity = new InputStreamEntity(is, body.getBytes().length);
            post.setEntity(inputStreamEntity);

            HttpResponse response = HttpClientBuilder.create().build().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status != HTTP_STATUS_OK) {
                throw new IOException("Unable to authenticate. Received invalid http status=" + status);
            }

            String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            System.out.println("RESPONSE as String: " + jsonResponse);

            // use jackson library to pull the string into json objects
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            ens_auth_token = node.get("ens-auth-token").asText();
            System.out.println("ens-auth-token: " + ens_auth_token);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public static void ensAuthTestVal() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            String body = "0490bc76-8649-4494-babf-e3f285880a7e";
            InputStream is = new ByteArrayInputStream(body.getBytes());
            InputStreamEntity inputStreamEntity;
            inputStreamEntity = new InputStreamEntity(is, body.getBytes().length);
            post.setEntity(inputStreamEntity);

            HttpResponse response = HttpClientBuilder.create().build().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status != HTTP_STATUS_OK) {
                throw new IOException("Unable to authenticate. Received invalid http status=" + status);
            }

            String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            System.out.println("RESPONSE as String: " + jsonResponse);

            // use jackson library to pull the string into json objects
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            ens_auth_token = node.get("ens-auth-token").asText();
            System.out.println("ens-auth-token: " + ens_auth_token);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Test_acc10
    @BeforeClass(alwaysRun = true)
    public static void ensAuthTestEvent() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            String body = "53c64d56-aacf-4661-9fe1-5c774c343579";
            InputStream is = new ByteArrayInputStream(body.getBytes());
            InputStreamEntity inputStreamEntity;
            inputStreamEntity = new InputStreamEntity(is, body.getBytes().length);
            post.setEntity(inputStreamEntity);

            HttpResponse response = HttpClientBuilder.create().build().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status != HTTP_STATUS_OK) {
                throw new IOException("Unable to authenticate. Received invalid http status=" + status);
            }

            String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            System.out.println("RESPONSE as String: " + jsonResponse);

            // use jackson library to pull the string into json objects
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            ens_auth_token = node.get("ens-auth-token").asText();
            System.out.println("ens-auth-token: " + ens_auth_token);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmail(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailOther(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmailOther(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailSeconds(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmailSeconds(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailIATSChecking(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        Assert.assertTrue("Bank Type is incorrect, not present", jsonResponse.contains("CHECKING"));
        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailIATSSaving(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        Assert.assertTrue("Bank Type is incorrect, not present", jsonResponse.contains("SAVING"));
        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailETT(String testId, PageFields fields) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createETTEmail(testId);
        HttpGet get = new HttpGet(SERVICE_URL_ETT + supporterEmail);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmailETT): " + jsonResponse);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailRSM(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createRSMemail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmailRSM): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("status: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);

    }

    @AfterTest(alwaysRun = true)
    public static void getQuestionResponseByID(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getQuetionResponseByID:");
        HttpClient client = HttpClientBuilder.create().build();
       // supporterEmail = fields.createEmail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter/" + supporterId + "?includeQuestions=true");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        ArrayNode JSONResponseBody = mapper.createArrayNode();
                //new JSONArray(response.body().asString());
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        //questions = JSONResponseBody.get("questions").toString();
        System.out.println("question : " + questions);
       // questions = node.get("Email Address").asText();
        how_did_you_hear_about_us = node.get("id:1076").asText();
        System.out.println("question response: " + how_did_you_hear_about_us);
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }


    @AfterTest(alwaysRun = true)
    public static void getSupporterById(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterById:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);
        supporterTaxId = fields.getSupporterTaxID();

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter/" + supporterId + "/transactions/" + supporterTaxId);

        System.out.println("url: " + get);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterById): " + jsonResponse);

        System.out.println("Status getSupporterById: " + status);

    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByIdOther(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterById:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmailOther(testId);
        supporterTaxId = fields.getSupporterTaxID();

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter/" + supporterId + "/transactions/" + supporterTaxId);

        System.out.println("url: " + get);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterById): " + jsonResponse);

        System.out.println("Status getSupporterById: " + status);

    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByIdSeconds(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterById:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmailSeconds(testId);
        supporterTaxId = fields.getSupporterTaxID();

        HttpGet get = new HttpGet(SERVICE_BASEURL + "/supporter/" + supporterId + "/transactions/" + supporterTaxId);

        System.out.println("url: " + get);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterById): " + jsonResponse);

        System.out.println("Status getSupporterById: " + status);

    }

    @Parameters({"browser"})
    @BeforeClass
    public static WebDriver createInstance(String browser) {

        //WebDriver driver;

            if (browser.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
                ChromeOptions options = new ChromeOptions();
                //options.setBinary("/Users/evyens/ENS/ENSelenium/webdrivers/linux/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing");
               // options.addArguments("start-maximized");
                options.addArguments("disable-infobars");
                options.addArguments("--disable-web-security");
                options.addArguments("--disable-cache");
                //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
               // options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("autofill.profile_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                //return driver;
            }
            if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "webdrivers/win/geckodriver");
                driver = new FirefoxDriver();
                //return driver;
            }
            if (browser.equalsIgnoreCase("internet")) {
                driver = new SafariDriver();
               // return driver;
            }
            return driver;
        }

}

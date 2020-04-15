package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public class IATS {

    // static WebDriver driver;
    static FundraisingPageDriver page = new FundraisingPageDriver();
    static WebDriver driver = page.driverSettings();
    private static String FUNDRAISING_TEST;
    //static PageFields fields;
    static PageFields fields = PageFactory.initElements(driver, PageFields.class);

//
//    @Inject
//    static String testId;

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    @Test(groups = {"donations"})
    public static void iatsSingle() throws InterruptedException, IOException {


        driver.get("https://politicalnetworks.com/page/13152/donate/1");
        fields.waitForPageLoad();

        List<WebElement> fieldsPage = driver.findElements(By.tagName("select"));
        for (WebElement iframeT : fieldsPage) {
            System.out.println("Id " + iframeT);
            System.out.println("Id1 " + iframeT.getAttribute("id"));
            System.out.println("Id2 " + iframeT.getAttribute("name"));}
        Thread.sleep(4000);
        System.out.println("Elelments: ");
        List<WebElement> labelsPage = driver.findElements(By.tagName("div"));
        for (WebElement iframeT : labelsPage) {
            System.out.println("labelsPage " + iframeT);
            System.out.println("labelsPage2 " + iframeT.getAttribute("value"));
            System.out.println("labelsPage3 " + iframeT.getAttribute("text"));}
        //fields.selectDonationAmt("15");
        //fields.selectTitle("Ms");
        Assert.assertTrue("Urls are not the 1st page", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13152/donate/1"));
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail("iatsSingle");
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.waitForPageLoad();
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13152/donate/2"));
        List<WebElement> fieldsPage2 = driver.findElements(By.tagName("select"));
        for (WebElement iframeT : fieldsPage2) {
            System.out.println("Id01 " + iframeT);
            System.out.println("Id02 " + iframeT.getAttribute("id"));
            System.out.println("Id03 " + iframeT.getAttribute("name"));}
        System.out.println("Elelments01: ");
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

//		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13152/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3509"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));

        page.ensAuthTest();
        page.getSupporterByEmail(FUNDRAISING_TEST = "iatsSingle", fields);
    }
//
//    @Test(groups = {"donations"})
//    public static void IATSRecurring() throws InterruptedException, IOException {
//        page.ensAuthTest();
//        driver.get("https://politicalnetworks.com/page/861/donate/1?mode=DEMO");
//        fields.waitForPageLoad();
//        Thread.sleep(4000);
//        fields.selectDonationAmt("15");
//        fields.selectTitle("Ms");
//        fields.setFirstname("Unit");
//        fields.setLastname("Tester");
////		Call the createEmail function
//        String new_email = fields.createEmail("IATSRecurring");
//        fields.setEmailAddress(new_email);
//
//        fields.submit();
//
//        fields.setAddress1("1 Hilltop");
//        fields.setCity("Baltimore");
//        fields.selectRegion("MD");
//        fields.setPostCode("20001");
//        fields.selectCountry("US");
//
//        fields.setRecurDay("23");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate startDate = LocalDate.now().plusDays(1);
//        fields.setRecurStartDate(dtf.format(startDate).toString());
//        LocalDate endDate = LocalDate.now().plusYears(1);
//        fields.setRecurEndDate(dtf.format(endDate).toString());
//        fields.setRecurFreq("MONTHLY");
//
//        fields.setCCName("Unit Tester");
//        fields.setCCNUmber("4222222222222220");
//        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
//        fields.setCCV("123");
//
//        fields.submit();
//
//        //		Assert that the payment was successful and the third page was reached
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/861/donate/3"));
//
////		Get the details from the third page and Verify the fields
//        String bodytext = driver.findElement(By.tagName("body")).getText();
//        Assert.assertTrue("Campaign ID not present", bodytext.contains("3510"));
//        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
//        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
//        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
//        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
//        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));
//
//        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSRecurring", fields);
//    }
//
//    @Test(groups = {"donations"})
//    public static void IATSACHRecurring() throws InterruptedException, IOException {
//        page.ensAuthTest();
//        driver.get("https://politicalnetworks.com/page/5724/donate/1?mode=DEMO");
//        fields.waitForPageLoad();
//        Thread.sleep(4000);
//        fields.selectTitle("Ms");
//        fields.setFirstname("Unit");
//        fields.setLastname("Tester");
////		Call the createEmail function
//        String new_email = fields.createEmail("IATSACHRecurring");
//        fields.setEmailAddress(new_email);
//
//        fields.setAddress1("1 Hilltop");
//        fields.setCity("Baltimore");
//        fields.setRegion("MD");
//        fields.setPostCode("20001");
//        fields.setCountry("US");
//
//        fields.submit();
//
//        fields.setRecurDay("23");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate startDate = LocalDate.now().plusDays(1);
//        fields.setRecurStartDate(dtf.format(startDate).toString());
//        LocalDate endDate = LocalDate.now().plusYears(1);
//        fields.setRecurEndDate(dtf.format(endDate).toString());
//        fields.setRecurFreq("MONTHLY");
//
//        fields.selectDonationAmt("15");
//        fields.selectPaymentType("ACHEFT");
//        fields.selectPayCurrency("GBP");
//        fields.setCCName("Unit Tester");
//        fields.selectBankAccType("Checking");
//        fields.setBankAccNumber("1234567");
//        fields.setBankRoutingNumber("000000000");
//
//        fields.submit();
//
//
//        //		Assert that the payment was successful and the third page was reached
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/5724/donate/3"));
//
////		Get the details from the third page and Verify the fields
//        String bodytext = driver.findElement(By.tagName("body")).getText();
//        Assert.assertTrue("Campaign ID not present", bodytext.contains("4542"));
//        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
//        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
//        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
//        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
//        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("ACHEFT"));
//
//        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSACHRecurring", fields);
//    }
//
//    @Test(groups = {"donations"})
//    // @Parameters({"testId"})
//    public static void IATSACHRecurPaymenttypelogic() throws InterruptedException, IOException {
//        page.ensAuthTest();
//        driver.get("https://politicalnetworks.com/page/5725/donate/1?mode=DEMO");
//        fields.waitForPageLoad();
//        Thread.sleep(4000);
//        fields.selectTitle("Ms");
//        fields.setFirstname("Unit");
//        fields.setLastname("Tester");
////		Call the createEmail function
//        String new_email = fields.createEmail("IATSACHRecurPaymenttypelogic");
//        fields.setEmailAddress(new_email);
//
//        fields.submit();
//
//        fields.setAddress1("1 Hilltop");
//        fields.setCity("Baltimore");
//        fields.selectRegion("MD");
//        fields.setPostCode("20001");
//        fields.selectCountry("US");
//
//        fields.selectPaymentType("Visa");
//        fields.selectPayCurrency("USD");
//        fields.setCCName("Unit Tester");
//        fields.setCCNUmber("4222222222222220");
//        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
//        fields.setCCV("123");
//
//        fields.submit();
//
//        // verify that transaction fails
//
//        Assert.assertTrue(driver.getCurrentUrl().equals("https://politicalnetworks.com/page/5725/donate/2?val"));
//
//        fields.selectPaymentType("ACHEFT");
//        fields.selectPayCurrency("USD");
//        fields.setCCName("Unit Tester");
//        fields.selectBankAccType("Checking");
//        fields.setBankAccNumber("1234567");
//        fields.setBankRoutingNumber("000000000");
//
//
//        fields.clickRecurringSinglePaymentchkbox();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate startDate = LocalDate.now().plusDays(1);
//        fields.setRecurStartDate(dtf.format(startDate).toString());
//        fields.setRecurFreq("MONTHLY");
//
//        fields.submit();
//
//        //		Assert that the payment was successful and the third page was reached
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/5725/donate/3"));
//
////		Get the details from the third page and Verify the fields
//        String bodytext = driver.findElement(By.tagName("body")).getText();
//        Assert.assertTrue("Campaign ID not present", bodytext.contains("4543"));
//        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
//        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$5.00"));
//        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
//        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
//        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("ACHEFT"));
//        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSACHRecurPaymenttypelogic", fields);
//    }

//    @AfterMethod
//    public static void getScreenshot(ITestResult result) throws Exception {
//        //below line is just to append the date format with the screenshot name to avoid duplicate names
//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        if (ITestResult.FAILURE == result.getStatus()) {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            //after execution, you could see a folder "FailedTestsScreenshots" under src folder
//            String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + dateName + ".png";
//            File finalDestination = new File(destination);
//            FileUtils.copyFile(source, finalDestination);
//            //Returns the captured file path
//
//        }
//    }
}

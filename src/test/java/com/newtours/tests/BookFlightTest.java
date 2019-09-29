package com.newtours.tests;

import com.newtours.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BookFlightTest {

    private WebDriver driver;
    private int countOfPassengers;
    private String expectedPrice;

    @BeforeClass
    @Parameters(value = {"countOfPassengers", "expectedPrice"})
    public void setUpDriver(int countOfPassengers, String expectedPrice) {
        this.countOfPassengers = countOfPassengers;
        this.expectedPrice = expectedPrice;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void registrationPageTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("Mikhail", "Tester");
        registrationPage.enterUserCredentials("mike_tester", "12345");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightsDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightDetailsPageTest() {
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectCountOfPassengers(countOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPageTest")
    public void findFlightPageTest() {
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.proceedToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPageTest")
    public void flightConfirmationPageTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }


    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

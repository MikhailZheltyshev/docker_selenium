package com.newtours.tests;

import com.newtours.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightTest extends BaseTest {

    private int countOfPassengers;
    private String expectedPrice;

    @BeforeClass
    @Parameters(value = {"countOfPassengers", "expectedPrice"})
    public void setParameters(int countOfPassengers, String expectedPrice) {
        this.countOfPassengers = countOfPassengers;
        this.expectedPrice = expectedPrice;
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
}

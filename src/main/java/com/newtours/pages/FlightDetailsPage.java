package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "passCount")
    private WebElement passCountSelect;

    @FindBy(name = "findFlights")
    private WebElement continueBtn;

    public FlightDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void selectCountOfPassengers(int count) {
        wait.until(ExpectedConditions.visibilityOf(passCountSelect));
        Select passengersSelect = new Select(passCountSelect);
        passengersSelect.selectByValue(String.valueOf(count));
    }

    public void goToFindFlightsPage() {
        continueBtn.click();
    }

}

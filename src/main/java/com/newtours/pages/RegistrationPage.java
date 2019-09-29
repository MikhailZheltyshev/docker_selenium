package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(name = "email")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(name = "register")
    private WebElement submitBtn;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get("http://newtours.demoaut.com/mercuryregister.php");
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
    }

    public void enterUserDetails(String firstName, String lastName) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }

    public void enterUserCredentials(String userName, String password) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
    }

    public void submit() {
        submitBtn.click();
    }
}

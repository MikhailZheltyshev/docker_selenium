package com.searchmodule.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "search_form_input_homepage")
    private WebElement searchBar;

    @FindBy(id = "search_button_homepage")
    private WebElement searchButton;

    @FindBy(linkText = "Videos")
    private WebElement videosTab;

    @FindBy(className = "tile--vid")
    private List<WebElement> allVideos;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get("https://duckduckgo.com/");
    }

    public void searchFor(String term) {
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(term);
        searchButton.click();
    }

    public void goToVideos() {
        wait.until(ExpectedConditions.visibilityOf(videosTab));
        videosTab.click();
    }

    public int getResult() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allVideos));
        System.out.println(allVideos.size());
        return allVideos.size();
    }
}

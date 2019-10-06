package com.searchmodule.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.searchmodule.pages.SearchPage;

public class SearchTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"term"})
    public void searchTest(String term) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.searchFor(term);
        searchPage.goToVideos();
        Assert.assertTrue(searchPage.getResult() > 0);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

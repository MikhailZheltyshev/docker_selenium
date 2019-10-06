package com.searchmodule.tests;

import com.searchmodule.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SearchTest extends BaseTest {

    @Test
    @Parameters("term")
    public void searchTest(String term) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.searchFor(term);
        searchPage.goToVideos();
        Assert.assertTrue(searchPage.getResult() > 0);
    }
}

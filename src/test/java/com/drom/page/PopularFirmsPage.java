package com.drom.page;

import com.drom.core.BaseSeleniumPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PopularFirmsPage extends BaseSeleniumPage {
    public PopularFirmsPage(String url) {
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@data-ftid='sales_search-location-picker_geoOverCity']")
    private WebElement searchLocation;

    @FindBy(xpath = "//div[text()='Приморский край']")
    private WebElement region;
    @FindBy(xpath = "//div[text()='Все города региона']")
    private WebElement allCitiesInRegion;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fid']//input[@placeholder='Марка']")
    private WebElement companyInput;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fid']//div[@data-ftid='component_select_dropdown']")
    private List<WebElement> companyList;

    public List<String> getPopularFirm() throws InterruptedException {
        searchLocation.click();
        region.click();
        allCitiesInRegion.click();
        companyInput.click();

        return readList(companyList);
    }

    public List<String> readList(List<WebElement> list) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<String> result = new ArrayList<>(100);
        boolean isEnd = false;

        while (true) {
            Thread.sleep(300);
            for (var e : list) {
                result.add(e.getText());

                if (e.getText().contains("УАЗ")) {
                    isEnd = true;
                }
            }

            if (isEnd) {
                break;
            }

            js.executeScript("document.querySelector('.css-u25ii9.e154wmfa0').scrollBy(0, 600)");
        }

        return result;
    }
}

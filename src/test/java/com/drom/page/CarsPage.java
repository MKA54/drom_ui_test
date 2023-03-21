package com.drom.page;

import com.drom.core.BaseSeleniumPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarsPage extends BaseSeleniumPage {
    public CarsPage(String url) {
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-ftid='sales__filter_advanced-button']")
    private WebElement advancedSearch;

    @FindBy(xpath = "//div[@data-ftid='sales__filter_fid']//input[@placeholder='Марка']")
    private WebElement companyInput;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fid']//div[@data-ftid='component_select_dropdown']")
    private List<WebElement> companyList;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fid']//div[@data-ftid='component_select_dropdown']//div[starts-with(text(), 'Toyota')]")
    private WebElement companyValue;

    @FindBy(xpath = "//div[@data-ftid='sales__filter_mid']//div[@class='css-75hx9m e1a8pcii0']")
    private WebElement modelInput;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_mid']//div[@data-ftid='component_select_dropdown']")
    private List<WebElement> modelList;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_mid']//div[@data-ftid='component_select_dropdown']//div[starts-with(text(), 'Harrier')]")
    private WebElement modelValue;

    @FindBy(xpath = "//div[@data-ftid='sales__filter_fuel-type']//button[text()='Топливо']")
    private WebElement fuelButton;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fuel-type']//div[@data-ftid='component_select_dropdown']")
    private List<WebElement> fuelTypeList;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_fuel-type']//div[@data-ftid='component_select_dropdown']//div[starts-with(text(), 'Гибрид')]")
    private WebElement fuelTypeValue;

    @FindBy(xpath = "//label[@for='sales__filter_unsold']")
    private WebElement isSoldLabel;

    @FindBy(xpath = "//input[@placeholder='от, км']")
    private WebElement mileageFromInput;

    @FindBy(xpath = "//div[@data-ftid='sales__filter_year-from']//button[@data-ftid='component_select_button']")
    private WebElement yearsFromButton;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_year-from']//div[@data-ftid='component_select_dropdown']")
    private List<WebElement> yearsList;
    @FindBy(xpath = "//div[@data-ftid='sales__filter_year-from']//div[@data-ftid='component_select_dropdown']//div[starts-with(text(), '2007')]")
    private WebElement yearsValue;

    @FindBy(xpath = "//button[@data-ftid='sales__filter_submit-button']")
    private WebElement show;

    public void getCars() throws InterruptedException {
        advancedSearch.click();

        companyInput.click();
        clickElement("Toyota", companyList, companyValue);

        modelInput.click();
        clickElement("Harrier", modelList, modelValue);

        fuelButton.click();
        clickElement("Гибрид", fuelTypeList, fuelTypeValue);

        isSoldLabel.click();

        mileageFromInput.click();
        mileageFromInput.sendKeys("1000");

        yearsFromButton.click();
        clickElement("2007", yearsList, yearsValue);

        show.click();
    }

    private void clickElement(String param, List<WebElement> elementsList, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isSearch = true;

        while (true) {
            Thread.sleep(300);
            for (var e : elementsList) {
                if (e.getText().contains(param)) {
                    element.click();
                    isSearch = false;
                }
            }

            if (!isSearch) {
                return;
            }

            js.executeScript("document.querySelector('.css-u25ii9.e154wmfa0').scrollBy(0, 600)");
        }
    }
}

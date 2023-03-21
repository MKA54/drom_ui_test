package com.drom.page;

import com.drom.core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {
    public LoginPage(String url) {
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@data-ftid='component_header_login']")
    private WebElement headerLogin;

    @FindBy(id = "sign")
    private WebElement setLogin;
    @FindBy(id = "password")
    private WebElement setPassword;
    @FindBy(id = "signbutton")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//a[@data-ftid='bulls-list_bull']//div[@class='css-1rozdag']")
    private WebElement addToFavorite;

    public void getLoginPage() {
        headerLogin.click();
    }

    public void login() {
        setLogin.sendKeys("login");
        setPassword.sendKeys("password");
        buttonSubmit.click();
    }

    public void addСarToFavorites() {
        addToFavorite.click();
    }
}

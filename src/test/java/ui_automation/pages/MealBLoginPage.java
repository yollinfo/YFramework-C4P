package ui_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class MealBLoginPage {

    MealBLandingPage mealBLandingPage= new MealBLandingPage();

    public MealBLoginPage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(name = "usernameOrEmailAddress")
    public WebElement userNameInput;

    @FindBy(name = "Password")
    public WebElement passwordInput;

    @FindBy(id = "LoginButton")
    public WebElement loginButton;

    @FindBy(css = ".sweet-alert p")
    public List<WebElement> invalidMsgs;

    public void login(String userName, String password){
        mealBLandingPage.signInButton.click();
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginButton.click();
    }


}

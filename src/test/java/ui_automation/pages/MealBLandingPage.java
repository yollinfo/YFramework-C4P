package ui_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MealBLandingPage {

    public MealBLandingPage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(linkText = "SIGN IN")
    public WebElement signInButton;



}

package ui_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MealBDashboardPage {

    public MealBDashboardPage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(className = "brighttheme-icon-closer")
    public WebElement bannerXbutton;

    @FindBy(id = "HeaderCurrentUserName")
    public WebElement currentUserFullName;

}

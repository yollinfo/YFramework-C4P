package ui_automation.utilities;

import com.google.common.base.Function;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.util.LinkedList;

public class BrowserHelper {
    private static final Logger oLog = LogManager.getLogger(BrowserHelper.class);
    public BrowserHelper() {
        oLog.debug("BrowserHelper : " + Driver.getInstance().getDriver().hashCode());
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getInstance().getDriver().getWindowHandle();
        for (String handle : Driver.getInstance().getDriver().getWindowHandles()) {
            Driver.getInstance().getDriver().switchTo().window(handle);
            if (Driver.getInstance().getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getInstance().getDriver().switchTo().window(origin);
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<String>(
                Driver.getInstance().getDriver().getWindowHandles());
        Driver.getInstance().getDriver().switchTo().window(windowsId.get(0));
        oLog.info("");
    }

    public void switchToParentWithChildClose() {
        switchToParentWindow();

        LinkedList<String> windowsId = new LinkedList<String>(
                Driver.getInstance().getDriver().getWindowHandles());

        for (int i = 1; i < windowsId.size(); i++) {
            oLog.info(windowsId.get(i));
            Driver.getInstance().getDriver().switchTo().window(windowsId.get(i));
            Driver.getInstance().getDriver().close();
        }

        switchToParentWindow();
    }

    public void switchToFrame(By locator) {
        Driver.getInstance().getDriver().switchTo().frame(Driver.getInstance().getDriver().findElement(locator));
        oLog.info(locator);
    }

    public void switchToFrame(String nameOrId) {
        Driver.getInstance().getDriver().switchTo().frame(nameOrId);
        oLog.info(nameOrId);
    }

}

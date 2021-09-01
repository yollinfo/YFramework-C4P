package api_automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public Properties property;
	public TestBase() {
		try {
			property = new Properties();
			String path = "src/test/resources/api-config.properties";
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);			
			property.load(fis);
		} catch (IOException e) {				
			e.printStackTrace();
		}
	}
}
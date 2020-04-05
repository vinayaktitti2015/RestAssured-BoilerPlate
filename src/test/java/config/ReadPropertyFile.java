package config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	public static Properties prop = null;
	public static void loadData() throws IOException {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/src/test/java/config/config.properties");
		FileReader obj = new FileReader(file);
		prop.load(obj);
	}

	public String getKey(String data) throws IOException {
		loadData();
		data = prop.getProperty(data);
		return data;
	}
}

package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;

import config.ReadPropertyFile;
import io.restassured.RestAssured;

public class BaseClass {

	static ReadPropertyFile prop;
	public BaseClass() {
		prop = new ReadPropertyFile();
	}


	@BeforeClass
	public static void setUp() throws IOException {

		String basepath = System.getProperty("server.base");
		if (basepath == null) {
			basepath = "/restapi/";
		}
		RestAssured.basePath = basepath;

		String basehost = System.getProperty("server.host");
		if (basehost == null) {
			basehost = "";
		}
		RestAssured.baseURI = basehost;

	}

}

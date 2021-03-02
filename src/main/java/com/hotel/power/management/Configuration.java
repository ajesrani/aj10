package com.hotel.power.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	private static Configuration _instance = null;
	public static int DEFAULT_UNITS_LIGHTS = 5;
	public static int DEFAULT_UNITS_AC = 10;

	private Configuration() throws IOException {
		InputStream file;
		
		file = new FileInputStream(new File("config.properties"));
		//FileReader file = new FileReader("db.properties"); 
		Properties props = new Properties();
		props.load(file);
		DEFAULT_UNITS_LIGHTS = Integer.parseInt(props.getProperty("DEFAULT_UNITS_LIGHTS"));
		DEFAULT_UNITS_AC = Integer.parseInt(props.getProperty("DEFAULT_UNITS_AC"));
	}

	public static Configuration getInstance() throws IOException {
		if (_instance == null)
			_instance = new Configuration();

		return _instance;
	}
}
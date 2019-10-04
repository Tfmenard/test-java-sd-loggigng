package com.example.helloworld;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

@SpringBootApplication
public class HelloworldApplication {
	
	@Value("${TARGET:World}")
	String target;

	@RestController
	class HelloworldController {
		@GetMapping("/")
		String hello() {
			return "Hello " + target + "! :) ";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);

		Logger logger = Logger.getLogger(HelloworldApplication.class.getName());

		logger.info("Logging new INFO with java.util.logging");
		logger.severe("Logging ERROR with java.util.logging");
		logger.warning("Logging WARNING with java.util.logging");

		//Print property to make properties file is used.
		 try (InputStream input = HelloworldApplication.class.getClassLoader().getResourceAsStream("logging.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                logger.severe("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            logger.severe(prop.getProperty("test.prop"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

	}
}
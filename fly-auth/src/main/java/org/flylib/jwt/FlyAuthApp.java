package org.flylib.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 
 * @author Frank Liu(Liu Shaoming)(liushaomingdev@163.com)
 *
 */
@ServletComponentScan
@SpringBootApplication
public class FlyAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(FlyAuthApp.class, args);
	}
}

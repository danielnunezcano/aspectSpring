package com.bitsmuggler.learning.springbootaspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringbootaspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootaspectApplication.class, args);
	}
}

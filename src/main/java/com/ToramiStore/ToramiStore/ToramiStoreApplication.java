package com.ToramiStore.ToramiStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToramiStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToramiStoreApplication.class, args);
	}

}

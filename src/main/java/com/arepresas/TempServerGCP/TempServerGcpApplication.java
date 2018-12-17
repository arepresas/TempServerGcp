package com.arepresas.TempServerGCP;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TempServerGcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempServerGcpApplication.class, args);
	}

	@Bean
	public Datastore cloudDatastoreService() {
		return DatastoreOptions.getDefaultInstance().getService();
	}

}


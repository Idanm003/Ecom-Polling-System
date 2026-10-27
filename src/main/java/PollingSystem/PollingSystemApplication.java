package PollingSystem;

import PollingSystem.config.PollTablesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = PollTablesConfig.class)
public class PollingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollingSystemApplication.class, args);
	}

}

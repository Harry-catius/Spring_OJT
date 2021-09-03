package catius.ojt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjtApplication.class, args);
	}

}

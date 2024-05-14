package sasikala.explore.service.cardapiartifact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:myapp.properties")
public class CardApiArtifactApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApiArtifactApplication.class, args);
	}

}

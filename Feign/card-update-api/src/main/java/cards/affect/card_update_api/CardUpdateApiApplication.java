package cards.affect.card_update_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CardUpdateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardUpdateApiApplication.class, args);
	}

}

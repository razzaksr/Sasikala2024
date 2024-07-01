package cards.affect.card_update_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(url = "http://localhost:8078",name = "Feign-Interceptor")
@FeignClient(name="CARD-RETRIEVAL-API")
public interface FeignInterceptor {
    @GetMapping("/all")
    List<CreditCard> getFromService();
}

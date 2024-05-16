package sasikala.explore.service.cardapiartifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sasikala.explore.security.entities.CreditCard;
import sasikala.explore.security.remotes.CardRepository;

import java.util.List;

@RestController
@ComponentScan("sasikala.explore.security")
public class CardRestController {
    @Autowired
    CardRepository cardRepository;

    @PostMapping("/")
    public ResponseEntity requestToApprove(@RequestBody CreditCard creditCard){
        CreditCard card = cardRepository.approve(creditCard);
        return ResponseEntity.ok().body(card);
    }

    @GetMapping("/")
    public ResponseEntity requestToView(){
        List<CreditCard> customers = cardRepository.viewCustomers();
        return ResponseEntity.ok().body(customers);
    }
}

package cards.affect.card_update_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    private UpdateRepo updateRepo;

    @GetMapping(value = "/{cardNumber}",produces = "application/xml")
    public CreditCard selectOne(@PathVariable("cardNumber") long cardNumber){
        return updateRepo.fetchByCardNumber(cardNumber);
    }

    @PostMapping("/")
    public CreditCard newOne(@RequestBody CreditCard card){
        return updateRepo.approve(card);
    }

    @DeleteMapping("/")
    public String removeOne(@RequestParam("cardNumber") long cardNumber,@RequestParam("pinNumber") int pinNumber){
        return updateRepo.blockCard(cardNumber,pinNumber);
    }

}

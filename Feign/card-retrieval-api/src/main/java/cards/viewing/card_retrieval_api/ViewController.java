package cards.viewing.card_retrieval_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController {
    @Autowired
    private ViewRepo repo;

    @GetMapping("/all")
    public List<CreditCard> gets(){
        return repo.viewCustomers();
    }
}

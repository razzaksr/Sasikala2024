package sasikala.explore.security.remotes;

import org.springframework.stereotype.Repository;
import sasikala.explore.security.entities.CreditCard;

import java.util.List;

@Repository
public interface CardRepository {
    CreditCard approve(CreditCard creditCard);
    List<CreditCard> viewCustomers();
    String blockCard(long cardNumber, int pinNumber);
    CreditCard swipe(CreditCard creditCard,int purchase);
}

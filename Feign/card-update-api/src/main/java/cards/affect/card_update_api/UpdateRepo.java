package cards.affect.card_update_api;

import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRepo {
    CreditCard fetchByCardNumber(long cardNumber);
    CreditCard approve(CreditCard creditCard);
    String blockCard(long cardNumber, int pinNumber);
    CreditCard swipe(CreditCard creditCard,int purchase);
}

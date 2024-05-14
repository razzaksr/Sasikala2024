package sasikala.explore.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sasikala.explore.security.entities.CreditCard;
import sasikala.explore.security.remotes.CardRepository;

import java.util.List;

@Service
public class CardImplementation implements CardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CreditCard approve(CreditCard creditCard) {
        jdbcTemplate.update("insert into mybank_creditcard values(?,?,?,?,?,?,?,?,?)",
                new Object[]{
                        creditCard.getCardNumber(),
                        creditCard.getCardCvv(),
                        creditCard.getCardExpiry(),
                        creditCard.getCardPin(),
                        creditCard.getCardLimit(),
                        creditCard.getCardUsage(),
                        creditCard.getCardAvailable(),
                        creditCard.isCardStatus(),
                        creditCard.getCardHolder()
                });
        return creditCard;
    }

    @Override
    public List<CreditCard> viewCustomers() {
        return null;
    }

    @Override
    public String blockCard(long cardNumber, int pinNumber) {
        return null;
    }

    @Override
    public CreditCard swipe(CreditCard creditCard, int purchase) {
        return null;
    }
}

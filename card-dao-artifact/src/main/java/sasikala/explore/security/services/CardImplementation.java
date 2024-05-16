package sasikala.explore.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import sasikala.explore.security.entities.CreditCard;
import sasikala.explore.security.remotes.CardRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.query("select * from mybank_creditcard",new CreditCardMapper());
    }

    protected class CreditCardMapper implements RowMapper<CreditCard>{

        @Override
        public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            CreditCard creditCard=new CreditCard();
            creditCard.setCardNumber(rs.getLong(1));
            creditCard.setCardCvv(rs.getInt(2));
            creditCard.setCardExpiry(rs.getDate(3));
            creditCard.setCardPin(rs.getInt(4));
            creditCard.setCardLimit(rs.getInt(5));
            creditCard.setCardUsage(rs.getInt(6));
            creditCard.setCardAvailable(rs.getInt(7));
            creditCard.setCardStatus(rs.getBoolean(8));
            creditCard.setCardHolder(rs.getString(9));
            return creditCard;
        }
    }

    @Override
    public String blockCard(long cardNumber, int pinNumber) {
        jdbcTemplate.update("delete from mybank_creditcard where creditcard_number=? and creditcard_pin=?",
                new Object[]{
                        cardNumber,pinNumber
                });
        return cardNumber+" has blocked permanently";
    }

    @Override
    public CreditCard swipe(CreditCard creditCard, int purchase) {
        if(purchase+creditCard.getCardUsage()<= creditCard.getCardLimit()*0.800){
            jdbcTemplate.update("update mybank_creditcard set creditcard_available=?, creditcard_usage=? where creditcard_number=?",
                    new Object[]{
                            creditCard.getCardAvailable()-purchase,
                            creditCard.getCardUsage()+purchase,
                            creditCard.getCardNumber()
                    });
            return creditCard;
        }
        else{
            throw new IllegalArgumentException("No sufficient available balance");
        }
    }
}

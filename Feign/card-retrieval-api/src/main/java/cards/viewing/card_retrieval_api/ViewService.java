package cards.viewing.card_retrieval_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ViewService implements ViewRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CreditCard> viewCustomers() {
        return jdbcTemplate.query("select * from mybank_creditcard",new CreditCardMapper());
    }

    protected class CreditCardMapper implements RowMapper<CreditCard> {

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
}

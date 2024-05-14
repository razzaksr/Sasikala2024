package sasikala.explore.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private long cardNumber;
    private int cardCvv;
    private int cardPin;
    private Date cardExpiry;
    private String cardHolder;
    private int cardLimit;
    private int cardAvailable;
    private int cardUsage;
    private boolean cardStatus;
}

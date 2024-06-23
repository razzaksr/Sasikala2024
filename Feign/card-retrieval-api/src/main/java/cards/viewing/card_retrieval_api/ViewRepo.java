package cards.viewing.card_retrieval_api;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepo {
    List<CreditCard> viewCustomers();
}

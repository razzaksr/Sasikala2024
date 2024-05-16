package sasikala.explore.service.cardapiartifact;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sasikala.explore.security.entities.CreditCard;
import sasikala.explore.security.remotes.CardRepository;
import soap.card.BlockCardRequest;
import soap.card.BlockCardResponse;
import soap.card.SwipeCardRequest;
import soap.card.SwipeCardResponse;

@Endpoint
@ComponentScan("sasikala.explore.security")
public class CardSoapEndpoint {
    private final String url="http://card.soap";
    @Autowired
    CardRepository cardRepository;

    @PayloadRoot(namespace = url,localPart = "swipeCardRequest")
    @ResponsePayload
    public SwipeCardResponse swipe(@RequestPayload SwipeCardRequest swipeCardRequest){
        CreditCard creditCard=new CreditCard();
        BeanUtils.copyProperties(swipeCardRequest.getCreditCard(),creditCard);
        CreditCard daoCard = cardRepository.swipe(creditCard, swipeCardRequest.getPurchase());
        soap.card.CreditCard serviceCard=new soap.card.CreditCard();
        BeanUtils.copyProperties(daoCard,serviceCard);
        SwipeCardResponse response=new SwipeCardResponse();
        response.setCreditCard(serviceCard);
        return response;
    }

    @PayloadRoot(namespace = url,localPart = "blockCardRequest")
    @ResponsePayload
    public BlockCardResponse blockingCard(@RequestPayload BlockCardRequest blockCardRequest){
        String msg = cardRepository.blockCard(blockCardRequest.getCardNumber(), blockCardRequest.getCardPin());
        BlockCardResponse cardResponse = new BlockCardResponse();
        cardResponse.setMessage(msg);
        return cardResponse;
    }
}

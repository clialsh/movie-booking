package movie;

import movie.config.kafka.KafkaProcessor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    GiftRepository giftRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPrinted_(@Payload Printed printed){

        if(printed.isMe()){
            System.out.println("======================================");
            System.out.println("##### listener  : " + printed.toJson());
            System.out.println("======================================");

            Gift gift = new Gift();
            gift.setBookingId(printed.getId());
            gift.setStatus("Gift Applied");

            giftRepository.save(gift);
        }
    }

}

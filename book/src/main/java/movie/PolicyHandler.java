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
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_(@Payload Paid paid){

        if(paid.isMe()){

            System.out.println("======================================");
            System.out.println("**** listener  : " + paid.toJson());
            System.out.println("======================================");
            bookRepository.findById(paid.getBookingId()).ifPresent((book)->{
                book.setStatus("PaidComplete");
                bookRepository.save(book);
            });

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPrinted_(@Payload Printed printed){

        if(printed.isMe()){
            System.out.println("##### listener  : " + printed.toJson());
        }
        if(printed.isMe()){

            System.out.println("======================================");
            System.out.println("**** listener  : " + printed.toJson());
            System.out.println("======================================");
            bookRepository.findById(printed.getBookingId()).ifPresent((book)->{
                book.setStatus("PrintComplete");
                bookRepository.save(book);
            });

        }
    };


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaken_(@Payload Taken taken){

        
        if(taken.isMe()){

            System.out.println("======================================");
            System.out.println("**** listener  : " + taken.toJson());
            System.out.println("======================================");
            bookRepository.findById(taken.getBookingId()).ifPresent((book)->{
                book.setStatus("GiftTakingComplete");
                bookRepository.save(book);
            });

        }
    };



    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverApplied_(@Payload Applied applied){

        
        if(applied.isMe()){

            System.out.println("======================================");
            System.out.println("**** listener  : " + applied.toJson());
            System.out.println("======================================");
            bookRepository.findById(applied.getBookingId()).ifPresent((book)->{
                book.setStatus("GiftApplied");
                bookRepository.save(book);
            });

        }
    };


}

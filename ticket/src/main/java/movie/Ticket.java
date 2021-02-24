package movie;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="Ticket_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long bookingId;
    private String seat;
    private String movieName;
    private Integer qty;
    private String status;

    @PostPersist
    public void onPostPersist(){

        Created created = new Created();
        BeanUtils.copyProperties(this, created);
        created.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){

        if("Printed".equals(status)){
             Printed printed = new Printed();
             BeanUtils.copyProperties(this, printed);
             printed.setStatus("Printed");
             printed.publishAfterCommit();
            

            movie.external.Gift gift = new movie.external.Gift();
            System.out.println("*********************");
            System.out.println("프린트 이벤트 발생");
            System.out.println("*********************");

            

            // mappings goes here

            gift.setBookingId(printed.getBookingId());
            Random random = new Random();
            Integer randomValue = random.nextInt(3);
            switch (randomValue) {  
                case 0: 
                    
                    gift.setName("Americano");
                    gift.setGiftCode("G000");
                    break;
                case 1:     
                    gift.setName("CafeLatte");
                    gift.setGiftCode("G001");
                    break; 
                case 2:
                    gift.setName("CafeMocha");
                    gift.setGiftCode("G002");
                    break;
                case 3:
                    gift.setName("Cappuccino");
                    gift.setGiftCode("G003");
                    break;    
                default:
                    gift.setName("Americano");
                    gift.setGiftCode("G000");
            };
            gift.setStatus("PrintedAndGiftApplied");
            TicketApplication.applicationContext.getBean(movie.external.GiftService.class)
            .apply(gift);
            
        }


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}

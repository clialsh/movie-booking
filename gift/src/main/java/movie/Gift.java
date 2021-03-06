package movie;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Gift_table")
public class Gift {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long bookingId;
    private String name;
    private String giftCode;
    private String status;
    private String msg = System.getenv("NAME");
    // @PostPersist
    // public void onPostPersist(){
    //     Applied applied = new Applied();
    //     BeanUtils.copyProperties(this, applied);
    //     applied.publishAfterCommit();


    // }
    
    @PrePersist
    public void onPrePersist(){
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
            System.out.println("####### Gift #######");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if("PrintedAndGiftApplied".equals(status)){
            Applied applied = new Applied();
            BeanUtils.copyProperties(this, applied);
            applied.publishAfterCommit();

        }
        


    }


    @PostUpdate
    public void onPostUpdate(){
        if("Taken".equals(status)){
            Taken taken = new Taken();
            BeanUtils.copyProperties(this, taken);
            taken.setStatus("PritedAndTakenGift");
            taken.publishAfterCommit();
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

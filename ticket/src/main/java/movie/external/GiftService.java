package movie.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="gift", url="http://localhost:8085")
public interface PaymentService {

    @RequestMapping(method= RequestMethod.POST, path="/apply")
    public void apply(@RequestBody Payment payment);
    
    

}
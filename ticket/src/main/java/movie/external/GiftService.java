package movie.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import java.util.Date;

@FeignClient(name="gift", url="http://localhost:8085")
public interface GiftService {

    @RequestMapping(method= RequestMethod.POST, path="/gifts")
    public void apply(@RequestBody Gift gift);
    
    

}
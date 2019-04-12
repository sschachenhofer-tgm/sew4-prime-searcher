package primesearcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrimeSearcherController {

    @GetMapping("/primes/searcher")
    public String getPrimes(Model model) {
        model.addAttribute("startTime", )
    }

}

package primesearcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableAutoConfiguration
public class PrimeSearcherController {

    @Autowired
    private Searcher searcher;

    @GetMapping("/primes/searcher")
    public String getPrimes(Model model) {

        model.addAttribute("startup", this.searcher.getStartupString());
        model.addAttribute("latestPrime", this.searcher.getLatestPrime());
        model.addAttribute("latestPrimeDiscovered", this.searcher.getLatestPrimeDiscoveredString());
        model.addAttribute("allprimes", this.searcher.getPrimesString());

        return "searcher";
    }

    @Bean
    public Searcher getSearcher() {
        Searcher s = new Searcher();
        s.start();

        return s;
    }

}

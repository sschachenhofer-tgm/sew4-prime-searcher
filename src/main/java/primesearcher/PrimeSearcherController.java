package primesearcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@EnableAutoConfiguration
@RequestMapping("/primes")
public class PrimeSearcherController {

    @Autowired
    private Searcher searcher;

    @RequestMapping("/searcher")
    public String getPrimes(Model model) {

        model.addAttribute("startup", this.searcher.getStartupString());
        model.addAttribute("latestPrime", this.searcher.getLatestPrime());
        model.addAttribute("latestPrimeDiscovered", this.searcher.getLatestPrimeDiscoveredString());
        model.addAttribute("allprimes", this.searcher.getPrimesString());

        return "searcher";
    }

    /**
     * After five seconds, automatically redirect to /primes/searcher when a user goes to /primes
     */
    @RequestMapping({"", "/"})
    public void redirect(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/primes/searcher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Searcher getSearcher() {
        return new Searcher();
    }

}

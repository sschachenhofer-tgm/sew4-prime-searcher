package primesearcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        addAttributesToModel(model);
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

    /**
     * Stops the Searcher thread and redirects back to /primes/searcher.
     */
    @RequestMapping("/stop")
    public void stopSearcher(HttpServletRequest req, HttpServletResponse resp) {

        this.searcher.stopCalculation();

        try {
            resp.sendRedirect("/primes/searcher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private utility method to add the four data fields (startup time, latest discovered prime, time the latest prime
     * was discovered, all primes discovered) to the model. Avoids duplicate code in the getPrimes() and stopSearcher()
     * methods.
     *
     * @param m The model object the attributes should be added to.
     */
    private void addAttributesToModel(Model m) {

        m.addAttribute("isRunning", this.searcher.isCalculating());
        m.addAttribute("startup", this.searcher.getStartupString());
        m.addAttribute("latestPrime", this.searcher.getLatestPrime());
        m.addAttribute("latestPrimeDiscovered", this.searcher.getLatestPrimeDiscoveredString());
        m.addAttribute("allprimes", this.searcher.getPrimesString());
    }
}

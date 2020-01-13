package primesearcher;

import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPrimeSearcherController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate trt;
    @Autowired
    private PrimeSearcherController cont;

    /**
     * Assert that the PrimeSearcherController is auto-wired.
     */
    @Test
    public void testControllerIsWired() {
        assertNotNull(cont);
    }

    /**
     * Loading just / should display a message redirecting the user to /primes/searcher
     */
    @Test
    public void testDefaultResponse() {
        assertThat(this.trt.getForObject("http://localhost:" + port + "/", String.class)).contains("The prime runner is available");
    }

    /**
     * Loading /primes/searcher should display the prime searcher page
     */
    @Test
    public void testPrimeSearcherPageResponse() {
        assertThat(this.trt.getForObject("http://localhost:" + port + "/primes/searcher", String.class))
                .contains("All primes discovered so far:");
    }
}

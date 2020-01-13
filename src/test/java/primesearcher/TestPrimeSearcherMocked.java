package primesearcher;

import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimeSearcherController.class)
public class TestPrimeSearcherMocked {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Searcher searcher;

    /**
     * Initialisieren des gemockten Objekts
     */
    @Before
    public void initMockedObject() {
        ArrayList<Long> primes = new ArrayList<>();
        primes.add(2L);
        primes.add(3L);
        primes.add(5L);
        primes.add(7L);
        primes.add(11L);
        primes.add(13L);
        primes.add(17L);
        primes.add(19L);

        when(searcher.getLatestPrime()).thenReturn(19L);
        when(searcher.getLatestPrimeDiscoveredString())
                .thenReturn(DateTimeFormatter.ofPattern("EEE LLL dd HH:mm:ss 'CEST' yyyy").format(LocalDateTime.now()));
        when(searcher.getLatestPrimeDiscoveredTime()).thenReturn(LocalDateTime.now());
        when(searcher.getPrimes()).thenReturn(primes);
        when(searcher.getPrimesString()).thenReturn("2, 3, 5, 7, 11, 13, 17, 19");
        when(searcher.getStartupString())
                .thenReturn(DateTimeFormatter.ofPattern("EEE LLL dd HH:mm:ss 'CEST' yyyy").format(LocalDateTime.now()));
        when(searcher.getStartupTime()).thenReturn(LocalDateTime.now());
        when(searcher.isCalculating()).thenReturn(true);
    }

    @Test
    public void test1ThisIsNotAGoodNameForATestCase() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/primes/searcher"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("2, 3, 5, 7, 11, 13, 17, 19")));
    }
}

package primesearcher;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class Searcher extends Thread {

    private ConcurrentSkipListSet<Long> primes;
    private boolean calculating; //Set this to false to stop the calculation
    private LocalDateTime startup;
    private LocalDateTime lastPrimeDiscovered;

    /*
     * DateTimeFormatter zum richtigen Formatieren von Datum und Uhrzeit. Das Ausgabeformat ist:
     * Fri Apr 12 15:29:04 CEST 2019
     * (entsprechend der Angabe auf Github)
     */
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE LLL dd HH:mm:ss 'CEST' yyyy");

    public Searcher() {
        this.primes = new ConcurrentSkipListSet<>();
        this.calculating = true;
        this.startup = LocalDateTime.now();

        this.start();
    }

    /**
     * Calculates primes until the method stop() is called. All calculated primes are added to a collection that can be
     * obtained through the method getPrimes().
     *
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    @Override
    public void run() {
        this.startup = LocalDateTime.now();

        long current = 2; //Start calculating at 2, the first prime number

        //Loop to calculate primes
        while (this.calculating) {

            /*
             * Set to false as soon as a another natural number is found that divides the current number in whole
             * numbers.
             */
            boolean isPrime = true;

            /*
             * Inner loop to check whether the current number (l) is dividable by and other natural number (m) > 1 in
             * whole numbers.
             * The loop starts at l = 2 (the lowest possible proper divisor > 1) and ends at current - 1 (the highest
             * possible proper divisor).
             */
            for (long l = 2; l < current; l++) {
                if (current % l == 0) {
                    isPrime = false;
                    break;
                }
            }

            /*
             * Add current number to the list if it is a prime and sleep for 1 second - this ensures the CPU is not used
             * too heavily
             */
            if (isPrime) {
                this.primes.add(current);
                this.lastPrimeDiscovered = LocalDateTime.now();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    this.calculating = false;
                }
            }

            current++;
        }
    }

    /**
     * Sets the private boolean parameter to false to stop the calculation of new primes and thereby end the thread.
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    public void stopCalculation() {
        this.calculating = false;
    }

    /**
     * Returns the set of calculated primes
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    public Collection<Long> getPrimes() {
        return this.primes;
    }

    /**
     * Returns the list of all calculated primes as a string
     *
     * @param separator a String value that is placed inbetween the primes
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    public String getPrimesString(String separator) {
        StringBuilder sb = new StringBuilder();

        for (Long l : this.primes) {
            sb.append(l).append(separator);
        }

        String s = sb.toString();
        return sb.substring(0, sb.length() - 2); //Cuts away the last comma
    }

    /**
     * Returns the list of all calculated primes as a string, separated by commas.
     *
     * @return A String in the format "2, 3, 5, 7, 11, " and so on
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    public String getPrimesString() {
        return this.getPrimesString(", ");
    }

    /**
     * Returns the most recently calculated prime
     *
     * @author Simon Schachenhofer
     * @since 2019-04-11
     */
    public long getLatestPrime() {
        return this.primes.last();
    }

    /**
     * Returns the startup time
     *
     * @return The LocalTime object representing the time where PrimeCalculator run() method was started
     * @author Simon Schachenhofer
     * @since 2019-04-12
     */
    public LocalDateTime getStartupTime() {
        return this.startup;
    }

    /**
     * Returns the startup time as a String
     *
     * @return A string, representing the time where PrimeCalculator run() method was started, in the following format:
     *         Fri Apr 12 09:34:27 CEST 2019
     * @author Simon Schachenhofer
     * @since 2019-04-12
     *
     */
    public String getStartupString() {
        return this.startup.format(this.dtf);
    }

    /**
     * Returns the time when the last prime was discovered
     *
     * @return The LocalTime object representing the time where the last prime was discovered
     * @author Simon Schachenhofer
     * @since 2019-04-12
     */
    public LocalDateTime getLatestPrimeDiscoveredTime() {
        return this.lastPrimeDiscovered;
    }

    /**
     * Returns the time when the last prime was discovered as a String
     *
     * @return A String, representing the time where the last prime was discovered, in the following format:
     *         Fri Apr 12 09:34:27 CEST 2019
     * @author Simon Schachenhofer
     * @since 2019-04-12
     */
    public String getLatestPrimeDiscoveredString() {
        return this.lastPrimeDiscovered.format(this.dtf);
    }
}

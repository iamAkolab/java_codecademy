import java.math.BigInteger;
import java.time.Clock;

/**
 * https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test Applying
 * the Miller-Rabin test. Accuracy: the Miller-Rabin test can declare a
 * composite n probably prime with a probability of at most 4^-k. A k of 160 will
 * be chosen for this project. However, for large n numbers, this probability is
 * closer to the bound of 8^-k. This means that it can declare a number prime
 * with a 8^-k chance of it being inaccurate. Input #1: n > 3, an odd integer to
 * be tested for primality Input #2: k, the number of rounds of testing to
 * perform Output: “composite” if n is found to be composite, “probably prime”
 * otherwise
 */
public class LargestPrimeForkJoin {
  // Stopping time limit in milliseconds
  static int timeLimit = 5 * 1000;

  
}

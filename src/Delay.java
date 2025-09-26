
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ruzteo
 */
//this class is used to calculate and add human-like randomness to the "human" clicking mode

public class Delay {
    public static long gaussianDelay(long muNs, long sigmaNs, long minNs, long maxNs){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while(true){
          double u1 = random.nextDouble();  
          double u2 = random.nextDouble();
          double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
          
          long candidate = muNs + (long) Math.round(sigmaNs * z); 
          if(candidate  >= minNs && candidate <= maxNs) return candidate;
        }
        
    }
}

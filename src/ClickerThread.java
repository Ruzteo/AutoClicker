
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * @author ruzteo
 */
//this thread checks if it should click and then does the required math for both clicking modes.

public class ClickerThread implements Runnable{
    private Listener listener;
    private frmClicker frame; 
    private Clicker clicker;
    private int cps = 10;
    private long currentTime = System.nanoTime();
    public ClickerThread(Listener listener, frmClicker frame , Clicker clicker ){
        this.listener = listener;
        this.clicker = clicker;
        this.frame = frame;
    }
    
    long dynamicRange;
    long cpsInterval = (long) 1_000_000_000 / cps;
    long nextClickTime = System.nanoTime() + cpsInterval;
 
    
    public void setCps(int newCps){
        cps = newCps;
        cpsInterval = (long )1_000_000_000 / cps;
    }
    
    
    @Override
    public void run() {
        while(true){
            long currentTime = System.nanoTime();
            
            if(!listener.isRunning()){
                    nextClickTime = currentTime + cpsInterval;
                    continue;
            }
            if(listener.isRunning() && currentTime >= nextClickTime){
                if(frame.human){//human mode
                    clicker.click();
                    long dynamicMean = cpsInterval + ThreadLocalRandom.current().nextLong(- dynamicRange, dynamicRange +1);
                    long delay = 
                    Delay.gaussianDelay(dynamicMean, cpsInterval / 2, cpsInterval / 2 , cpsInterval *2);
                    nextClickTime += delay;
                }
                else{//super mode
                    clicker.click();
                    nextClickTime += cpsInterval;
                }
               
            }
            Long sleepTime = nextClickTime - System.nanoTime();  
            if(sleepTime > 0){
                LockSupport.parkNanos(sleepTime);
            }

        }
            
            
    }
            
}
    
    


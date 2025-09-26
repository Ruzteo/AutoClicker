
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

//This class handles "clicking" with the help of java.robot

/**
 *
 * @author ruzteo
 */
public class Clicker {
    private Robot robot; 
    
    public void click(){ //clicks once
        try{
            robot = new Robot();
        } 
        catch (AWTException ex) {
             System.getLogger(frmClicker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
    }
    
        
    }


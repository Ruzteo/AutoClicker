
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

//this class is used to map and get the names of mouse buttons.

public class MouseUtilities {
    public static int getMouseID(int modifiers){
       if ((modifiers & NativeKeyEvent.BUTTON1_MASK) != 0 ) return 1;
       else if ((modifiers & NativeKeyEvent.BUTTON2_MASK) != 0 ) return 2;
       else if ((modifiers & NativeKeyEvent.BUTTON3_MASK) != 0 ) return 3;
       else if ((modifiers & NativeKeyEvent.BUTTON4_MASK) != 0 ) return 4;
       else if ((modifiers & NativeKeyEvent.BUTTON5_MASK) != 0 ) return 5;
       return 0;
    }
    public static String getMouseName(int mouseID){
        switch (mouseID) {
            case 1:
                return "Mouse 1";
            case 2:
                return "Mouse 2"; 
            case 3:
                return "Mouse 3";
            case 4:
                return "Mouse 4";
            case 5:
                return "Mouse 5";
            default:
                break;
        }
       return "unknown";
    }
    
}

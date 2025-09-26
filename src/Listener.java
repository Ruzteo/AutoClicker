
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

//this class implements JnativeHook and checks for universal key presses.

public class Listener implements NativeKeyListener, NativeMouseListener {
    private final frmClicker frame;
    public volatile boolean running = false;
    private int toggleKey = NativeKeyEvent.VC_F;
    private boolean keyIsMouse;

    public Listener(frmClicker frame) {
        this.frame = frame;
    }
    
    public int getKeyID(NativeKeyEvent event){
        int keyCode = event.getID(); 
        return keyCode; 
    }

    public String getKeyName(int keyID){
        String keyName = NativeKeyEvent.getKeyText(keyID);
        return keyName;
    }
    public void setkey(int keyCode){
        toggleKey = keyCode;
    }
    
    @Override
    public void nativeKeyPressed(NativeKeyEvent e){
        if(frmClicker.waitingForKey == true){
            keyIsMouse = false;
            setkey(e.getKeyCode());
            frmClicker.waitingForKey = false;
            frame.setStatusText(getKeyName(toggleKey));
            
        } 

        else if(e.getKeyCode() == toggleKey && !keyIsMouse){
            running =  true;
            
        }
        else if (e.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE){
            System.exit(0);
        }
        
    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent e){
        if(e.getKeyCode() == toggleKey && !keyIsMouse){
            running =  false;
            
        }
    }
    public boolean isRunning(){
        return running;
    }
    @Override
    public void nativeKeyTyped(NativeKeyEvent e){}
    
    @Override
    public void nativeMousePressed(NativeMouseEvent e){
        int mouseID;
        int modifiers =  e.getModifiers();
        if(frmClicker.waitingForKey == true){
            keyIsMouse = true;
            mouseID = MouseUtilities.getMouseID(modifiers);
            setkey(mouseID);
            frmClicker.waitingForKey = false;
            frame.setStatusText(MouseUtilities.getMouseName(mouseID));
            
        } 
        
            
        
        else if(MouseUtilities.getMouseID(modifiers) == toggleKey && keyIsMouse){
            running =  true;
            
        }
        
    }
    @Override
    public void nativeMouseReleased(NativeMouseEvent e){
        int modifiers = e.getModifiers();
        if(MouseUtilities.getMouseID(modifiers) != toggleKey && keyIsMouse){
            running =  false;
            
        }
    }
   
}
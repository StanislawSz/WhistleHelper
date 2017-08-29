
package mouthpiecemaker;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author Stas
 */
public class Main implements NativeKeyListener
{
    private StartedClass started;
    private TrayClass tray = new TrayClass();
    
    public Main()
    {
        started = new StartedClass();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        Main main = new Main();
        GlobalScreen.addNativeKeyListener(main);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e)
    {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE)
        {
            started.resetCounter();
            System.exit(0);
        }
        
        if (e.getKeyCode() == NativeKeyEvent.VC_ENTER)
        {
            Runnable runner = started;
            Thread thread = new Thread(runner);
            thread.start();
            
        }
    }
    
}

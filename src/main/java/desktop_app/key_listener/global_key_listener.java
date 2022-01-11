package desktop_app.key_listener;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;

public class global_key_listener implements NativeKeyListener {
    private boolean ctrlPressed = false;
    private ListView<String> clipboard_list = new ListView<>();

    public global_key_listener(boolean ctrlPressed, ListView<String> clipboard_list) {
        try {
            this.ctrlPressed = ctrlPressed;
            this.clipboard_list = clipboard_list;
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent ev) {
        // check your specific key press here
        // example:
        if (ev.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = true; // ctrl key is pressed
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent ev) {
        Platform.runLater(() -> {

            if (ev.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
                ctrlPressed = false; // ctrl key is released
            }
            if (ev.getKeyCode() == NativeKeyEvent.VC_C && ctrlPressed) { // check if ctrl + c is used
                if (Clipboard.getSystemClipboard().hasString()) {
                    clipboard_list.getItems().add(Clipboard.getSystemClipboard().getString());
                }
            }
            if (ev.getKeyCode() == NativeKeyEvent.VC_X && ctrlPressed) { // check if ctrl + x is used

                if (Clipboard.getSystemClipboard().hasString()) {
                    clipboard_list.getItems().add(Clipboard.getSystemClipboard().getString());
                }
            }

        });
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent arg0) {
    }
}


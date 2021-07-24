package com.github.ltgr.turbo;

import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class EventListener {

    public static int HOTKEY_1;
    public static int HOTKEY_2;
    public static boolean HK_1_PRESSED;
    public static boolean HK_2_PRESSED;

    public static void listenerInit(String hotkey) {
        // disable logging of mouse drag events
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // TODO: convert string to Native Key ID
        HOTKEY_1 = NativeKeyEvent.VC_ALT;
        HOTKEY_2 = NativeKeyEvent.VC_W;
        HK_1_PRESSED = false;
        HK_2_PRESSED = false;

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println("Issue with registering jnativehook");
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new NativeListener());
    }

    public static boolean hotkeyPressed() {
        return EventListener.HK_1_PRESSED && EventListener.HK_2_PRESSED;
    }

}

class NativeListener implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // nothing to do
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if(e.getKeyCode() == EventListener.HOTKEY_1) {
            EventListener.HK_1_PRESSED = true;
        } else if(e.getKeyCode() == EventListener.HOTKEY_2) {
            EventListener.HK_2_PRESSED = true;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // nothing to do
    }

}
package test.jWinAutoIt.src.test;

import test.jWinAutoIt.src.main.myautoit.AutoIt3;


public class TestNotepad {

    public static void main(String[] arg) throws Exception {
        
        String TITLE = "Notepad";
        String TEXT = "";
        
        try {
            AutoIt3 autoIt = new AutoIt3();
            autoIt.autoItSetOption("WinTitleMatchMode", 2); // Partial Title Match
            
            autoIt.run("notepad");
            if (autoIt.winWaitActive(TITLE, TEXT, 20) > 0) {
                System.out.println("Window open");
                autoIt.controlSend(TITLE, TEXT, "Edit1", "Text to go into notepad");
            } else {
                System.out.println("Failed to open Notepad");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}

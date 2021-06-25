package test.jWinAutoIt.src.test;

import test.jWinAutoIt.src.main.myautoit.AutoIt3;



public class TestCalc {

    public static void main(String[] arg) throws Exception {
        
        String TITLE = "Calculator";
        String TEXT = "";
        
        try {
            AutoIt3 autoIt = new AutoIt3();
            autoIt.run("calc");
            if (autoIt.winWaitActive(TITLE, TEXT, 20) > 0) {
                System.out.println("Window open");
                autoIt.controlClick(TITLE, TEXT, "Button11"); // 2
                autoIt.controlClick(TITLE, TEXT, "Button21"); // *
                autoIt.controlClick(TITLE, TEXT, "Button9");  // 8
                autoIt.controlClick(TITLE, TEXT, "Button28"); // equals
                Thread.sleep(1000);
                String result = autoIt.winGetText(TITLE, TEXT); // This doesn't work for me either. I don't know how to get the display text from calc
                System.out.println("Result (2 * 8) = "+ result);
                autoIt.winClose(TITLE, TEXT);
            } else {
                System.out.println("Calculator failed to open");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    

}

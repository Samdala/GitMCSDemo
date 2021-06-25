package test.framework;

public class Timer {
    
    long start; 
    long stop; 
    
    public Timer start() {
        start = System.currentTimeMillis();
        return this;
    }
    
    public Timer stop() {
        stop = System.currentTimeMillis();
        return this;
    }
    
    public String toString() {
    	return "" + (stop - start);
    }

}

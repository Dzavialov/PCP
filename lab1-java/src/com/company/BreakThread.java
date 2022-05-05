package com.company;

public class BreakThread implements Runnable{
    private boolean canBreak = false;
    @Override
    public void run() {
        try{
            Thread.sleep(15*1000);
        }   catch (InterruptedException e) {
            e.printStackTrace();
        }
        canBreak = true;
    }

    synchronized public boolean isCanBreak(){
        return canBreak;
    }
}

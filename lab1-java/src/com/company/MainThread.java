package com.company;

public class MainThread extends Thread{
    private final int id;
    private final int step;
    private final BreakThread breakThread;

    public MainThread(int id, int step, BreakThread breakThread){
        this.id = id;
        this.step = step;
        this.breakThread = breakThread;
    }

    @Override
    public void run(){
        boolean isStop = false;
        long sum = 0;
        long count = 0;
        do{
            sum+=step;
            count++;
            isStop = breakThread.isCanBreak();
        } while(!isStop);
            System.out.println("id = " + id + ", sum = " + sum + ",count = " + count);
    }
}

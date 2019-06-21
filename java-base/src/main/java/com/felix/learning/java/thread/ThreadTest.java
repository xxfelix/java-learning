package com.felix.learning.java.thread;

public class ThreadTest {

    static class PrimeThread extends Thread{

        public static void main(String[] args){
            PrimeThread thread = new PrimeThread();
            thread.setName("runnable");
            thread.start();
            Runnable runnable = () ->System.out.println(Thread.currentThread().getName()+" runnable run");
            Thread t = new Thread(runnable);
            t.start();
            System.out.println(Thread.currentThread().getName());
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+" Thread run");
        }

    }
}

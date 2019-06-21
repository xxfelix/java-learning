package com.felix.learning.java.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程协作
 * 以售票为例
 * 关键字Synchronized
 */
public class MultiThread {
    int tickets = 10;

    /**
     * 重复卖票
     */
//    void sell(){
//        while(tickets >0 ){
//            System.out.println(Thread.currentThread().getName()+" sell ticket:"+ tickets);
//            tickets--;
//        }
//        System.out.println(Thread.currentThread().getName()+" sell out.");
//    }

    /**
     * Sync之后，导致资源独占
     */
//    synchronized void sell(){
//        while(tickets >0 ){
//            System.out.println(Thread.currentThread().getName()+" sell ticket:"+ tickets);
//            tickets--;
//        }
//        System.out.println(Thread.currentThread().getName()+" sell out.");
//    }

    /**
     *改善后，资源没有独占,售出无效票
     */

//    void sell(){
//        while(tickets >0 ){
//            synchronized (this){
//                System.out.println(Thread.currentThread().getName()+" sell ticket:"+ tickets);
//                tickets--;
//            }
//            //do something
//            try{
//                TimeUnit.MILLISECONDS.sleep(50L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//        System.out.println(Thread.currentThread().getName()+" sell out.");
//    }


    /**
     * 正确1
     * 改善资源独占，解决售出无效票
     */
//    void sell(){
//        while(tickets >0 ){
//            synchronized (this){
//                if(tickets>0){
//                    System.out.println(Thread.currentThread().getName()+" sell ticket:"+ tickets);
//                    tickets--;
//                }
//            }
//            //do something
//            try{
//                TimeUnit.MILLISECONDS.sleep(50L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//        System.out.println(Thread.currentThread().getName()+" sell out.");
//    }

    /**
     * 正确2
     * 使用锁
     */
    private Lock lock = new ReentrantLock();
    void sell(){
        while(tickets >0 ){
            lock.lock();
            try{
                if(tickets>0){
                    System.out.println(Thread.currentThread().getName()+" sell ticket:"+ tickets);
                    tickets--;
                }
            }finally {
                lock.unlock();//锁必须在finally解锁
            }
            //do something
            try{
                TimeUnit.MILLISECONDS.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName()+" sell out.");
    }

    public static void main(String[] args){
        MultiThread tickets = new MultiThread();
        Thread A = new Thread(tickets::sell);
        A.setName("Seller A");
        Thread B = new Thread(tickets::sell);
        B.setName("Seller B");
        Thread C = new Thread(tickets::sell);
        C.setName("Seller C");

        A.start();
        B.start();
        C.start();

    }
}

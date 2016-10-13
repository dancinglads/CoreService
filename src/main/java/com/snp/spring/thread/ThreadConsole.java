package com.snp.spring.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ThreadConsole implements Runnable{

    String name;

    public ThreadConsole(String name){
        this.name = name;
    }

    @Override
    public void run() {

//        System.out.println(name + " is running");
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(name + " is running");
    }

}
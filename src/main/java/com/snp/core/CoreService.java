package com.snp.core;

import com.snp.threadpool.WorkerThread;

import java.util.concurrent.*;

public class CoreService {
    public static void main(String[] args) {
        int  corePoolSize  =    5;

        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;
        System.out.println("Neeraj");
        System.out.println("Neeraj---");
        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()
                );

        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            threadPoolExecutor.execute(worker);
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }



}
package com.snp.spring.core;


import com.snp.spring.thread.ThreadConsole;
import com.snp.spring.utils.LoadConfiguration;
import com.snp.threadpool.ChildThread;
import com.sun.xml.internal.ws.api.PropertySet;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    static int port;
    static String host;
    static Map map;
    public static void main(String argv[])
    {
        logger.info("Logging begins...");
        logger.entering("Application.class","Main method");
        ApplicationContext context = new FileSystemXmlApplicationContext("spring-config.xml");
        //load configuration
        LoadConfiguration loadConfiguration = (LoadConfiguration) context.getBean("loadConfiguration");

        host = loadConfiguration.getHost();
        port = loadConfiguration.getPort();

        System.out.println("Host:" + host);
        System.out.println("Port:" + port);

        ProcessorMap processorMap = (ProcessorMap) context.getBean("processorMap");

        map = processorMap.getProcessors();

        System.out.println("map="+map);

        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
        Application application = new Application();
        application.startServer( taskExecutor, port );
        logger.exiting("Application.class","Main method");







        //ProcessorMap processorMap = (ProcessorMap) context.getBean("prcessorBean");


//        taskExecutor.execute(new ThreadConsole("Thread 1"));
//        taskExecutor.execute(new ThreadConsole("Thread 2"));
//        taskExecutor.execute(new ThreadConsole("Thread 3"));
//        taskExecutor.execute(new ThreadConsole("Thread 4"));
//        taskExecutor.execute(new ThreadConsole("Thread 5"));
//
//        //check active thread, if zero then shut down the thread pool
//        for (;;) {
//            int count = taskExecutor.getActiveCount();
//            logger.info("Active Threads : " + count);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (count == 0) {
//                taskExecutor.shutdown();
//                break;
//            }
//        }

    }

    private void startServer( ThreadPoolTaskExecutor taskExecutor, int port )
    {
        ServerSocket socket = null;
        try {
            logger.entering("Application.class","startServer method");
            socket = new ServerSocket(port);
            logger.info("Listening for Core on : " + port + "...");
            System.out.println("Listening For Core on : " + port + "...");


            for (; ;) {
                try {
                    ChildThread child= new ChildThread(socket.accept());
                    taskExecutor.execute(child);
                } catch (RejectedExecutionException e) {
                    logger.throwing("ChildThread","startServer",e);

                }

                         }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.exiting("Application.class","startServer method");
        }
    }
}
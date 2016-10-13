package com.snp.threadpool;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

public class ChildThread implements Runnable {
    private Socket socket;
    private static final Logger logger = Logger.getLogger(ChildThread.class.getName());


    public ChildThread(Socket _socket) {
        socket = _socket;
    }

    public void run() {
        logger.entering("ChildThread.class","run method");
        BufferedReader in = null;
        PrintWriter out = null;
        String requestString = null;
        try {
            socket.setKeepAlive(true);


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            while ((requestString = in.readLine()) != null) {
                try {


                    if (requestString != null && !requestString.isEmpty() ) {


                        //TODO
                        // XML validation process for validation xml format with xsd




                        logger.info(requestString);
                        logger.info("Done");

                    } else {
                        if (!requestString.equalsIgnoreCase("")) {
                            logger.info("Invalid Request");
                            out.println("Invalid Request");
                            out.flush();
                        }
                    }
                }
                catch (Exception e) {
                    logger.warning("Exception in child thread class");
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.exiting("ChildThread.class","run method");
            try {
                in.close();
            } catch (Exception e) {
                logger.warning("Couldn't close Input Stream");
            }
            try {
                out.close();
            } catch (Exception e) {
                logger.warning("Couldn't close Output Stream");
            }
            try {
                socket.close();
            } catch (Exception e) {
                logger.warning("Couldn't close Socket");
            }
        }
    }
}



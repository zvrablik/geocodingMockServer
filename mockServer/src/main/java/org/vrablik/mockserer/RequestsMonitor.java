/* --------------------------------------------------------------*\
| Copyright (C) e-Spatial Solutions Limited, All rights reserved. |
\* --------------------------------------------------------------*/
package org.vrablik.mockserer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Monitor number of requests.
 *
 * Created by zvrablik on 10/28/16.
 */
public class RequestsMonitor {

    private long startTime;

    private AtomicInteger requests;

    private AtomicInteger runningRequests;


    public RequestsMonitor(){
        requests = new AtomicInteger(0);
        runningRequests = new AtomicInteger(0);
        startTime = System.currentTimeMillis();

        String threadName = "RequestMonitorReporter" + System.currentTimeMillis();
        Thread thread = new Thread(new MonitorLogger(), threadName);
        thread.setDaemon(true);
        thread.start();
    }

    public void startRequest() {
        requests.incrementAndGet();
        runningRequests.incrementAndGet();
    }

    public void finishRequest() {
        runningRequests.decrementAndGet();
    }

    class MonitorLogger implements Runnable {

        @Override
        public void run() {
            boolean loop = true;
            System.out.println("Monitoring requests:");

            while (loop) {
                long currentTimeSecondsMsg = (System.currentTimeMillis() - startTime) / 1000;
                int noOfRequestSinceLastTime = requests.getAndSet(0);
                if (noOfRequestSinceLastTime > 0) {
                    String lastPeriodRequests = " Last period no. of requests: " + noOfRequestSinceLastTime;
                    String runningRequestsMsg = " no. of running requests: " + runningRequests.get();

                    String msg = currentTimeSecondsMsg + lastPeriodRequests + runningRequestsMsg;
                    System.out.println(msg);
                }

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("Finished.");
                    break;
                }
            }
        }
    }
}

// NOTE: pls refer to start.gfsh to start locator!

//package com.max.cache.server;
//
//import org.apache.geode.distributed.LocatorLauncher;
//
//import java.util.concurrent.TimeUnit;
//
//public class LocatorApp implements Runnable {
//
//    private final LocatorLauncher locatorLauncher;
//
//    public LocatorApp() {
//        locatorLauncher = new LocatorLauncher.Builder()
//                .setMemberName("locator1")
//                .setPort(10344)
//                .build();
//    }
//
//    public void run() {
//        // start the Locator in-process
//        locatorLauncher.start();
//        // wait for Locator to start and be ready to accept member (client) connections
//        locatorLauncher.waitOnStatusResponse(10, 3, TimeUnit.SECONDS);
//        System.out.println("*****started!*****");
//    }
//
//    public static void main(final String... args) {
//        new LocatorApp().run();
//    }
//
//}
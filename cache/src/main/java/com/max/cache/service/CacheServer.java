// NOTE: pls refer to start.gfsh to start server!

//package com.max.cache.server;
//
//import org.apache.geode.distributed.LocatorLauncher;
//import org.apache.geode.distributed.ServerLauncher;
//
//public class ServerApp {
//
//    public static void main(String[] args){
//
//        ServerLauncher serverLauncher  = new ServerLauncher.Builder()
//                .setMemberName("server1")
//                .setServerPort(40405)
//                .set("jmx-manager", "true")
//                .set("jmx-manager-start", "true")
//                .set("locators", "127.0.0.1[13489]")
//                //.set("ack-severe-alert-threshold", "1")
//                .build();
//
//        serverLauncher.start();
//
//        System.out.println("**********************Cache server successfully started**********************");
//
//        //serverLauncher.stop();
//
//    }
//}
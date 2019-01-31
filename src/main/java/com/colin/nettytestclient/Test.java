package com.colin.nettytestclient;

public class Test {

    public static void main(String[] args) {
        EachClient client = new EachClient("192.168.31.197", 8050);
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

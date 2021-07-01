package com.pbe;

import java.io.IOException;

public class TestThrows {
    void a() throws IOException {
        throw new IOException("Error"); //checked exception
    }

    void b() throws IOException {
        a();
    }

    void c() {
        try {
            b();
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }

    public static void main(String args[]) {
        TestThrows obj = new TestThrows();
        obj.c();
        System.out.println("Program continues");
    }
}
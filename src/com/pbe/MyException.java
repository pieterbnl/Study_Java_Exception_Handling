package com.pbe;

// Class used in example to demonstrate declaring a new subclass of Exception
// MyException is defined as subclass of Exception
public class MyException extends Exception {
    private int num;

    // Constructor that accepts a number and saves it as num
    MyException(int a) {
        num = a;
    }

    // Overridden toString() to display the value (num) of the exception
    public String toString() {
        return "MyException[" + num + "]";
    }
}

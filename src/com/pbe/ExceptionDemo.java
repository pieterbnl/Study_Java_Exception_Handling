package com.pbe;

// Class used in example to demonstrate declaring a new subclass of Exception
public class ExceptionDemo {

    // This method throws a (own made) MyException object, when compute()'s integer parameter is >10
    static void compute(int a) throws MyException {
        System.out.println("Called compute with a value of: " + a );
        if(a > 10) { // throw an exception
            throw new MyException(a);
        } else { // no exception
            System.out.println("Normal exit");
        }
    }

    // The main method sets up an exception handler for MyException
    // Then calls computer() with a legal (<10) and illegal (>10) value
    public static void main(String[] args) {
        try {
            compute(1);
            compute(20);
        } catch (MyException e){
            System.out.println("Caught: " + e);
        }
    }
}

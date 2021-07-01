package com.pbe;

// Example of Java exception propagation
// Exceptions are first thrown from the top of the stack and if not caught, dropping down the call stack to the previous method.
// It continues dropping to the previous method until caught or until reaching the 'bottom' of the call stack.
// If there's no catch match, Java run-time system default exception handler will handle the situation, resulting in program termination.
public class ExceptionPropagation {
    void a() {
        int num = 1/0; // causing '/ zero' exception
    }
    void b() {
        a();
    }
    void c() {
        try {
            b();
//        } catch(NullPointerException e) { // will result in program termination as the '/ null' exception is not caught
        } catch(Exception e) { // will catch the '/ null' exception
            System.out.println("Exception caught: " + e);
        }
    }

    public static void main(String args[]){
        ExceptionPropagation obj=new ExceptionPropagation();
        obj.c();
        System.out.println("Continue program");
    }
}
